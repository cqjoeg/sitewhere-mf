package com.sitewhere.device.event.processor;

import com.sitewhere.SiteWhere;
import com.sitewhere.SpringUtil;
import com.sitewhere.rest.model.device.event.request.DeviceCommandResponseCreateRequest;
import com.sitewhere.rest.model.device.field.DeviceField;
import com.sitewhere.rest.model.device.field.DeviceFieldDefinition;
import com.sitewhere.spi.SiteWhereException;
import com.sitewhere.spi.SiteWhereSystemException;
import com.sitewhere.spi.device.IDevice;
import com.sitewhere.spi.device.IDeviceAssignment;
import com.sitewhere.spi.device.IDeviceManagement;
import com.sitewhere.spi.device.event.IDeviceCommandResponse;
import com.sitewhere.spi.device.event.IDeviceEventManagement;
import com.sitewhere.spi.device.event.request.IDeviceMeasurementsCreateRequest;
import com.sitewhere.spi.device.field.IDeviceField;
import com.sitewhere.spi.device.field.service.IDeviceFieldService;
import com.sitewhere.spi.error.ErrorCode;
import com.sitewhere.spi.error.ErrorLevel;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by CQ on 2016/12/6.
 */
public class MeasureFilterProcessor extends InboundEventProcessor {

    /**
     * Static logger instance
     */
    private static Logger LOGGER = Logger.getLogger(MeasureFilterProcessor.class);

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
                                                  IDeviceMeasurementsCreateRequest request) throws SiteWhereException, BeansException {
        IDeviceFieldService deviceFieldServiceImpl = (IDeviceFieldService) SpringUtil.getBean(IDeviceFieldService.DEVICE_FIELD_SERVICE_IMPL);
        IDeviceField deviceField = deviceFieldServiceImpl.listDeviceFieldByHardwareIdAndType(hardwareId, DeviceField.MEASUREMENTS);
        List<String> list = ((DeviceFieldDefinition) deviceField.getDefinition()).getKey();
        //过滤
        Iterator iterator = request.getMeasurements().entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            Object key = entry.getKey();
            if (!list.contains(key)) {//不包含去除
                iterator.remove();
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
