package com.sitewhere.spi.device.field;

import com.sitewhere.spi.common.IMetadataProviderEntity;

import java.util.Date;

/**
 * Interface of device field
 *
 * @@author Joeg
 */
public interface IDeviceField extends IMetadataProviderEntity {


    public String getHardwareId();

    public String getType();

    public String getComments();

    public Object getDefinition();

    public Boolean getStarted();

    public Date getCreateddate();

    public Boolean getDeleted();

}
