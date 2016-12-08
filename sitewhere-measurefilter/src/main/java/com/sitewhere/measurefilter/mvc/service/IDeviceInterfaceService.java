package com.sitewhere.measurefilter.mvc.service;

import com.sitewhere.measurefilter.mvc.domain.DeviceInterfaceEntity;
import com.sitewhere.measurefilter.rest.model.device.DeviceInterface;
import com.sitewhere.measurefilter.spi.IResult;
import com.sitewhere.measurefilter.spi.device.request.IDeviceInterfaceCreateRequest;
import com.sitewhere.measurefilter.spi.search.device.IDeviceInterfaceSearchCriteria;
import org.springframework.data.domain.Page;

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
    public IResult insertDeviceInterface(IDeviceInterfaceCreateRequest deviceInterfaceCreateRequest);

    /**
     * update device method
     *
     * @param deviceInterfaceCreateRequest
     * @return
     */
    IResult updateDeviceInterface(String hardwareId, String methodName, IDeviceInterfaceCreateRequest deviceInterfaceCreateRequest);

    /**
     * delete device method
     *
     * @param hardwareId
     * @param methodName
     * @return
     */
    IResult deleteDeviceInterface(String hardwareId, String methodName);

    /**
     * list device interface
     *
     * @param criteria
     * @return
     */
    Page<DeviceInterfaceEntity> listDeviceInterface(IDeviceInterfaceSearchCriteria criteria);
}

