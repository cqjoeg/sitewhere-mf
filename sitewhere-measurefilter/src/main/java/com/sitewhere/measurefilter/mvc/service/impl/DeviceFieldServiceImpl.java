package com.sitewhere.measurefilter.mvc.service.impl;

import com.google.gson.Gson;
import com.sitewhere.measurefilter.mvc.dao.DeviceFieldRepository;
import com.sitewhere.measurefilter.mvc.domain.DeviceFieldEntity;
import com.sitewhere.rest.model.device.field.DeviceField;
import com.sitewhere.rest.model.device.field.request.DeviceFieldCreateRequest;
import com.sitewhere.spi.device.field.IDeviceField;
import com.sitewhere.spi.device.field.service.IDeviceFieldService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;

/**
 * Created by CQ on 2016/11/20.
 */

@Service(IDeviceFieldService.DEVICE_FIELD_SERVICE_IMPL)
//@Transactional
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
    public IDeviceField insertDeviceField(DeviceFieldCreateRequest request) {
        DeviceFieldEntity tmp = deviceFieldRepository.findByHardwareidAndType(request.getHardwareId(), request.getType());
        if (ObjectUtils.isEmpty(tmp)) {
            //true means there is no this type's field
            return DeviceField.copy(deviceFieldRepository.save(buildDeviceFieldEntity(request)));
        }
        return null;
    }


    /**
     * update device field
     *
     * @param hardwareId
     * @param deviceField
     * @return
     */
    @Override
    public IDeviceField updateDeviceField(String hardwareId, DeviceFieldCreateRequest deviceField) {
        DeviceFieldEntity tmp = deviceFieldRepository.findByHardwareidAndType(hardwareId, deviceField.getType());
        if (ObjectUtils.isEmpty(tmp) ){
            return null;
        } else {
            tmp.setComments(deviceField.getComments());
            tmp.setDefinition(deviceField.getDefinitionToString());
            tmp.setStarted(deviceField.getStarted());
            return DeviceField.copy(deviceFieldRepository.save(tmp));
        }

    }

    @Override
    public void deleteDeviceField(String hardwareId, String type) {
        DeviceFieldEntity tmp = deviceFieldRepository.findByHardwareidAndType(hardwareId, type);
        deviceFieldRepository.delete(tmp);
    }

    @Override
    public IDeviceField listDeviceFieldByHardwareIdAndType(String hardwareId, String type) {
        DeviceFieldEntity deviceFieldEntity = deviceFieldRepository.findByHardwareidAndType(hardwareId, type);
        if (ObjectUtils.isEmpty(deviceFieldEntity) ){
            return null;
        }
        return DeviceField.copy(deviceFieldEntity);
    }

    @Override
    public IDeviceField getDeviceFieldByHardwareId(String hardwareId) {
        DeviceFieldEntity deviceFieldEntity = deviceFieldRepository.findByHardwareid(hardwareId);
        if (ObjectUtils.isEmpty(deviceFieldEntity) ){
            return null;
        }
        return DeviceField.copy(deviceFieldEntity);
    }

    /**
     * 创建 DeviceFieldEntity 对象
     *
     * @param request
     * @return
     */
    public static DeviceFieldEntity buildDeviceFieldEntity(DeviceFieldCreateRequest request) {
        return new DeviceFieldEntity(request.getHardwareId(),
                request.getType(),
                request.getComments(),
                new Date(), false,
                (new Gson()).toJson(request.getDefinition()),
                request.getStarted()
        );
    }

}
