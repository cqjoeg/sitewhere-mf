package com.sitewhere.device.event.processor;

import com.sitewhere.SiteWhere;
import com.sitewhere.SpringUtil;
import com.sitewhere.rest.model.device.event.request.DeviceCommandResponseCreateRequest;
import com.sitewhere.rest.model.device.field.DeviceAlertData;
import com.sitewhere.rest.model.device.field.DeviceFieldDefinition;
import com.sitewhere.rest.model.device.field.DeviceKeyDefinition;
import com.sitewhere.rest.model.device.field.DeviceField;
import com.sitewhere.spi.SiteWhereException;
import com.sitewhere.spi.SiteWhereSystemException;
import com.sitewhere.spi.device.IDevice;
import com.sitewhere.spi.device.IDeviceAssignment;
import com.sitewhere.spi.device.IDeviceManagement;
import com.sitewhere.spi.device.event.IDeviceCommandResponse;
import com.sitewhere.spi.device.event.IDeviceEventManagement;
import com.sitewhere.spi.device.event.request.IDeviceMeasurementsCreateRequest;
import com.sitewhere.spi.device.field.IDeviceField;
import com.sitewhere.spi.device.field.service.IDeviceAlertDataService;
import com.sitewhere.spi.device.field.service.IDeviceFieldService;
import com.sitewhere.spi.error.ErrorCode;
import com.sitewhere.spi.error.ErrorLevel;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created by CQ on 2016/12/6.
 */
public class AlertDataProcessor extends InboundEventProcessor {

    /**
     * Static logger instance
     */
    private static Logger LOGGER = Logger.getLogger(AlertDataProcessor.class);

    /**
     * Cached device management implementation
     */
    private IDeviceManagement deviceManagement;

    /**
     * Cached device event management implementation
     */
    private IDeviceEventManagement deviceEventManagement;

    /**
     * First process request ，Eliminate unwanted data，
     * Then save measurements data
     *
     * @param hardwareId
     * @param originator
     * @param request
     * @throws SiteWhereException
     */
    @Override
    public void onDeviceMeasurementsCreateRequest(String hardwareId, String originator,
                                                  IDeviceMeasurementsCreateRequest request)
            throws SiteWhereException, BeansException {

        IDevice device = getDeviceManagement().getDeviceByHardwareId(hardwareId);
        if(device != null){
        IDeviceFieldService deviceFieldServiceImpl = (IDeviceFieldService) SpringUtil.getBean(IDeviceFieldService.DEVICE_FIELD_SERVICE_IMPL);
        IDeviceField deviceField = deviceFieldServiceImpl.listDeviceFieldByHardwareIdAndType(hardwareId, DeviceField.MEASUREMENTS);
        if (deviceField == null)
            return;

        IDeviceAssignment assignment = getCurrentAssignment(hardwareId);
        List<DeviceKeyDefinition> list = ((DeviceFieldDefinition) deviceField.getDefinition()).getKeys();

        Set<String> requestKeys = request.getMeasurements().keySet();//所有的 measurements 的key 值
        List<DeviceAlertData> deviceALertDataEntities = new ArrayList<DeviceAlertData>();

        for (DeviceKeyDefinition dkd : list) {
            if (requestKeys.contains(dkd.getKey())) {
                List<DeviceKeyDefinition.AlertRange> rangesList = dkd.getRanges();
                for (DeviceKeyDefinition.AlertRange alertRange : rangesList) {
                    Double requestValue = request.getMeasurements().get(dkd.getKey());
                    Double from = alertRange.getFrom();
                    Double to = alertRange.getTo();
                    if (from <= requestValue && requestValue <= to)
                        deviceALertDataEntities.add(
                                new DeviceAlertData(hardwareId, dkd.getKey(), alertRange.getMessage(), request.getEventDate(), requestValue, from, to, assignment.getToken())
                        );
                }

            }

        }
        if (deviceALertDataEntities.size() > 0) {
            IDeviceAlertDataService deviceFieldAPI1 = (IDeviceAlertDataService) SpringUtil.getBean(IDeviceAlertDataService.DEVICE_ALERT_DATA_SERVICE_IMPL);
            deviceFieldAPI1.batchInsert(deviceALertDataEntities);
        }

        }


    }

    /**
     * Get the current assignment or throw errors if it can not be resolved.
     *
     * @param hardwareId
     * @return
     * @throws SiteWhereException
     */
    protected IDeviceAssignment getCurrentAssignment(String hardwareId) throws SiteWhereException {
        IDevice device = getDeviceManagement().getDeviceByHardwareId(hardwareId);
        if (device == null) {
            throw new SiteWhereSystemException(ErrorCode.InvalidHardwareId, ErrorLevel.ERROR);
        }
        if (device.getAssignmentToken() == null) {
            throw new SiteWhereSystemException(ErrorCode.DeviceNotAssigned, ErrorLevel.ERROR);
        }
        return getDeviceManagement().getDeviceAssignmentByToken(device.getAssignmentToken());
    }

    /**
     * If an originator was assocaited with the event, create a
     * {@link IDeviceCommandResponse} that links back to the original invocation.
     *
     * @param originator
     * @param eventId
     * @param assignment
     * @throws SiteWhereException
     */
    protected void handleLinkResponseToInvocation(String originator, String eventId,
                                                  IDeviceAssignment assignment) throws SiteWhereException {
        if ((originator != null) && (!originator.isEmpty())) {
            DeviceCommandResponseCreateRequest response = new DeviceCommandResponseCreateRequest();
            response.setOriginatingEventId(originator);
            response.setResponseEventId(eventId);
            getDeviceEventManagement().addDeviceCommandResponse(assignment.getToken(), response);
        }
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }

    /**
     * Cache the device management implementation rather than looking it up each time.
     *
     * @return
     * @throws SiteWhereException
     */
    protected IDeviceManagement getDeviceManagement() throws SiteWhereException {
        if (deviceManagement == null) {
            deviceManagement = SiteWhere.getServer().getDeviceManagement(getTenant());
        }
        return deviceManagement;
    }

    protected IDeviceEventManagement getDeviceEventManagement() throws SiteWhereException {
        if (deviceEventManagement == null) {
            deviceEventManagement = SiteWhere.getServer().getDeviceEventManagement(getTenant());
        }
        return deviceEventManagement;
    }
}
