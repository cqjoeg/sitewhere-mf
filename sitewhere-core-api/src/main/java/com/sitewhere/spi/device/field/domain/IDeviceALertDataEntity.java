package com.sitewhere.spi.device.field.domain;

import java.util.Date;

/**
 * interface of Device Alert Data Entity
 *
 * @author Joeg
 */
public interface IDeviceALertDataEntity {
    public Integer getId();


    public String getHardwareid();


    public String getType();


    public String getComments();


    public Date getCreateddate();


    public Double getValue();

}
