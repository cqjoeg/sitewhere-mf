package com.sitewhere.spi.device.field;



import com.sitewhere.spi.common.IMetadataProviderEntity;

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

    public Double  getFrom();

    public Double  getTo();

    public String getAssignmenttoken();

}
