package com.sitewhere.spi.device.field.request;


import com.sitewhere.rest.model.device.field.DeviceMethodDefinition;
import com.sitewhere.spi.common.IMetadataProviderEntity;

import java.util.Date;

/**
 * IDeviceInterfaceCreateRequest interface
 *
 * @author Joeg
 */
public interface IDeviceInterfaceCreateRequest {

    public String getHardwareid();

    public String getComments();

    public Date getCreateddate();

    public String getMethodname();

    public DeviceMethodDefinition getDefinition();

    public Boolean getDeleted();

    public String getScript();
}
