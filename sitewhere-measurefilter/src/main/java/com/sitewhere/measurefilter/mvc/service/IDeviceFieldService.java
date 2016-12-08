package com.sitewhere.measurefilter.mvc.service;

import com.sitewhere.measurefilter.mvc.domain.DeviceFieldEntity;
import com.sitewhere.measurefilter.rest.model.device.request.DeviceFieldCreateRequest;
import com.sitewhere.measurefilter.spi.IResult;

/**
 * IdeviceField service interface
 *
 * @author Joeg
 */
public interface IDeviceFieldService {

    /**
     * insert interface
     *
     * @param deviceField
     * @return
     */
    public IResult insertDeviceField(DeviceFieldCreateRequest deviceField);

    /**
     * update interface
     *
     * @param hardwareId
     * @param deviceField
     * @return
     */
    public IResult updateDeviceField(String hardwareId, DeviceFieldCreateRequest deviceField);

    /**
     * delete interface
     *
     * @param hardwareId
     * @param type
     * @return
     */
    public IResult deleteDeviceField(String hardwareId, String type);

    /**
     * list device field by hardwareid and type
     *
     * @param hardwareId
     * @param type
     */
    public DeviceFieldEntity listDeviceFieldByHardwareIdAndType(String hardwareId, String type);

}
