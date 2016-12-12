package com.sitewhere.spi.device.field;


import com.sitewhere.spi.common.IMetadataProviderEntity;

/**
 * interface of device interface(method field)
 *
 * @author Joeg
 */
public interface IDeviceMethodField extends IMetadataProviderEntity {

    public String getPtype();

    public String getPname();
}
