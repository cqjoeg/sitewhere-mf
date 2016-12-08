package com.sitewhere.measurefilter.spi.device;


import com.sitewhere.measurefilter.spi.common.IMetadataProviderEntity;

import java.util.Date;

/**
 * interface of device interface(method)
 *
 * @author Joeg
 */
public interface IDeviceInterface extends IMetadataProviderEntity {

    public String getHardwareid();

    public String getComments();

    public Date getCreateddate();

    public String getMethodname();

    public String getDefinition();

    public Boolean getDeleted();

    public String getScript();

}
