package com.sitewhere.spi.device.field;

import com.sitewhere.rest.model.device.field.DeviceMethodField;
import com.sitewhere.spi.common.IMetadataProviderEntity;

import java.util.List;

/**
 * interface of device interface method definition
 *
 * @author Joeg
 */
public interface IDeviceMethodDefinition extends IMetadataProviderEntity {

    public String getMethodName();

    public List<DeviceMethodField> getFieldList();
}
