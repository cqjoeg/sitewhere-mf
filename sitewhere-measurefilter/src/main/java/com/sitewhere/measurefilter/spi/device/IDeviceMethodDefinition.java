package com.sitewhere.measurefilter.spi.device;

import com.sitewhere.measurefilter.rest.model.device.DeviceMethodField;

import java.util.List;

/**
 * interface of device interface method definition
 *
 * @author Joeg
 */
public interface IDeviceMethodDefinition {

    public String getMethodName();

    public List<DeviceMethodField> getFieldList();
}
