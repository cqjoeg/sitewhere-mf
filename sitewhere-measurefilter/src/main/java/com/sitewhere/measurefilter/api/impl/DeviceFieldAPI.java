package com.sitewhere.measurefilter.api.impl;

import com.sitewhere.measurefilter.api.IDeviceFieldAPI;
import com.sitewhere.measurefilter.mvc.domain.DeviceFieldEntity;
import com.sitewhere.measurefilter.mvc.service.IDeviceFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * DeviceFieldAPI use by inner module
 *
 * @author Joeg
 */

@Service("DeviceFieldAPI")
public class DeviceFieldAPI implements IDeviceFieldAPI {

    @Autowired
    IDeviceFieldService deviceFieldService;

    @Override
    public DeviceFieldEntity listDeviceFieldByHardwareIdAndType(String hardwareId, String type) {
        return deviceFieldService.listDeviceFieldByHardwareIdAndType(hardwareId, type);
    }
}
