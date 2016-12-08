package com.sitewhere.measurefilter.mvc.service.impl;

import com.sitewhere.measurefilter.mvc.dao.DeviceFieldRepository;
import com.sitewhere.measurefilter.mvc.domain.DeviceFieldEntity;
import com.sitewhere.measurefilter.mvc.service.IDeviceFieldService;
import com.sitewhere.measurefilter.rest.JsonResult;
import com.sitewhere.measurefilter.rest.model.device.request.DeviceFieldCreateRequest;
import com.sitewhere.measurefilter.spi.IResult;
import com.sitewhere.measurefilter.spi.MFSqlException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

/**
 * Created by CQ on 2016/11/20.
 */

@Service
@Transactional
public class DeviceFieldServiceImpl implements IDeviceFieldService {

    /**
     * Static logger instance
     */
    private static Logger LOGGER = Logger.getLogger(DeviceFieldServiceImpl.class);

    @Autowired
    private DeviceFieldRepository deviceFieldRepository;


    /**
     * insert device field
     *
     * @param request
     * @return
     */
    @Override
    public IResult insertDeviceField(DeviceFieldCreateRequest request) {
        try {
            DeviceFieldEntity tmp = deviceFieldRepository.findByHardwareidAndType(request.getHardwareId(), request.getType());
            if (ObjectUtils.isEmpty(tmp)) {
                //true means there is no this type's field
                deviceFieldRepository.save(request.buildDeviceFieldEntity(request));
            }
        } catch (MFSqlException e) {
            LOGGER.error(e.getErrorMsg());
            return JsonResult.failure("save error");
        }
        return JsonResult.success("save success", null);
    }

    /**
     * update device field
     *
     * @param hardwareId
     * @param deviceField
     * @return
     */
    @Override
    public IResult updateDeviceField(String hardwareId, DeviceFieldCreateRequest deviceField) {
        try {
            DeviceFieldEntity tmp = deviceFieldRepository.findByHardwareidAndType(hardwareId, deviceField.getType());
            tmp.setComments(deviceField.getComments());
            tmp.setDefinition(deviceField.getDefinitionToString());
            tmp.setStarted(deviceField.getStarted());
            deviceFieldRepository.save(tmp);
            return JsonResult.success("update success", null);
        } catch (MFSqlException e) {
            LOGGER.error(e.getErrorMsg());
            return JsonResult.failure("Update Error");
        }
    }

    @Override
    public IResult deleteDeviceField(String hardwareId, String type) {
        try {
            DeviceFieldEntity tmp = deviceFieldRepository.findByHardwareidAndType(hardwareId, type);
            deviceFieldRepository.delete(tmp);
            return JsonResult.success("delete success", null);
        } catch (MFSqlException e) {
            LOGGER.error(e.getErrorMsg());
            return JsonResult.failure("update Error");
        }
    }

    @Override
    public DeviceFieldEntity listDeviceFieldByHardwareIdAndType(String hardwareId, String type) {
        DeviceFieldEntity deviceFieldEntity = deviceFieldRepository.findByHardwareidAndType(hardwareId, type);
        return deviceFieldEntity;
    }
}
