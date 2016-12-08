package com.sitewhere.measurefilter.spi.device;


import com.sitewhere.measurefilter.spi.common.IMetadataProviderEntity;

import java.util.Date;

/**
 * Interface for devicealertdata
 *
 * @author Joeg
 */
public interface IDeviceAlertData extends IMetadataProviderEntity {


    public String getHardwareid();

    public String getType();

    public String getComments();

    public Date getCreateddate();

    public Double getValue();

}
