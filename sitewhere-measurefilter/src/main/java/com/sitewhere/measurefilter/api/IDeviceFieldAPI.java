package com.sitewhere.measurefilter.api;

import com.sitewhere.measurefilter.mvc.domain.DeviceFieldEntity;

/**
 * device field api interface
 *
 * @author Joeg
 */
public interface IDeviceFieldAPI {

    /**
     * list device field by hardwareid and type
     *
     * @param hardwareId
     * @param type
     */
    public DeviceFieldEntity listDeviceFieldByHardwareIdAndType(String hardwareId, String type);

}
