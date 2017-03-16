package com.sitewhere.spi.device.field.domain;

import java.sql.Timestamp;

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


    public Timestamp getCreateddate();


    public Double getValue();

}
