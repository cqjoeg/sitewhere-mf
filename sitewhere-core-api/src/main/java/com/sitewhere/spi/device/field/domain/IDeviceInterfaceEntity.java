package com.sitewhere.spi.device.field.domain;

import java.util.Date;

/**
 * Interface of DeviceInterfaceEntity
 *
 * @author Joeg
 */
public interface IDeviceInterfaceEntity {

    public Integer getId();


    public String getHardwareid();


    public String getComments();


    public Date getCreateddate();


    public String getMethodname();


    public String getDefinition();


    public Boolean getDeleted();

    public String getScript();


}
