package com.sitewhere.spi.device.field.service;


import com.sitewhere.rest.model.device.field.request.DeviceFieldCreateRequest;
import com.sitewhere.spi.device.field.IDeviceField;

/**
 * IdeviceField service interface
 *
 * @author Joeg
 */
public interface IDeviceFieldService {

    /**
     * impl class name ；register Bean name
     */
    public static final String DEVICE_FIELD_SERVICE_IMPL = "deviceFieldServiceImpl";

    /**
     * insert interface
     *
     * @param deviceField
     * @return
     */
    public IDeviceField insertDeviceField(DeviceFieldCreateRequest deviceField);

    /**
     * update interface
     *
     * @param hardwareId
     * @param deviceField
     * @return
     */
    public IDeviceField updateDeviceField(String hardwareId, DeviceFieldCreateRequest deviceField);

    /**
     * delete interface
     *
     * @param hardwareId
     * @param type
     * @return
     */
    public void deleteDeviceField(String hardwareId, String type);

    /**
     * list device field by hardwareid and type
     *
     * @param hardwareId
     * @param type
     */
    public IDeviceField listDeviceFieldByHardwareIdAndType(String hardwareId, String type);

    /**
     * get device field by hardwareid
     * @param hardwareId
     * @return
     */
    public IDeviceField getDeviceFieldByHardwareId(String hardwareId);

}
