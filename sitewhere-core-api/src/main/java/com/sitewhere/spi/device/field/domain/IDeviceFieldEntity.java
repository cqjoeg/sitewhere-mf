package com.sitewhere.spi.device.field.domain;

import java.util.Date;

/**
 * interface of device field entity
 *
 * @author Joeg
 */
public interface IDeviceFieldEntity {

    public Integer getId();


    public String getHardwareid();


    public String getType();


    public String getComments();


    public Date getCreateddate();


    public Boolean getDeleted();


    public String getDefinition();


    public Boolean getStarted();


}
