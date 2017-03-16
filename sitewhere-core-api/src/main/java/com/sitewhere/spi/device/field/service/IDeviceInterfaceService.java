package com.sitewhere.spi.device.field.service;

import com.sitewhere.rest.model.device.field.request.DeviceInterfaceCreateRequest;
import com.sitewhere.rest.model.search.field.DeviceInterfaceSearchCriteria;
import com.sitewhere.spi.device.field.IDeviceInterface;
import com.sitewhere.spi.device.field.domain.IDeviceInterfaceEntity;

import java.util.List;

/**
 * Interface of DeviceInterfaceService
 *
 * @author Joeg
 */
public interface IDeviceInterfaceService {

    /**
     * insert device method
     *
     * @param deviceInterfaceCreateRequest
     * @return
     */
    public IDeviceInterface insertDeviceInterface(DeviceInterfaceCreateRequest deviceInterfaceCreateRequest);

    /**
     * update device method
     *
     * @param deviceInterfaceCreateRequest
     * @return
     */
    public IDeviceInterface updateDeviceInterface(String hardwareId, String methodName, DeviceInterfaceCreateRequest deviceInterfaceCreateRequest);

    /**
     * delete device method
     *
     * @param hardwareId
     * @param methodName
     * @return
     */
    public void deleteDeviceInterface(String hardwareId, String methodName);

    /**
     * list device interface
     *
     * @param criteria
     * @return
     */
    List<IDeviceInterfaceEntity> listDeviceInterface(DeviceInterfaceSearchCriteria criteria);

    /**
     * get DeviceInterface
     * @param hardwareId
     * @param methodName
     * @return
     */
    IDeviceInterface getDeviceInterfaceByHardwareIdAndMethodName(String hardwareId, String methodName);
}

