package com.sitewhere.measurefilter.spi.device.request;

import com.sitewhere.measurefilter.rest.model.device.DeviceMethodDefinition;
import com.sitewhere.measurefilter.spi.common.IMetadataProviderEntity;

import java.util.Date;

/**
 * IDeviceInterfaceCreateRequest interface
 *
 * @author Joeg
 */
public interface IDeviceInterfaceCreateRequest extends IMetadataProviderEntity {

    public String getHardwareid();

    public String getComments();

    public Date getCreateddate();

    public String getMethodname();

    public DeviceMethodDefinition getDefinition();

    public Boolean getDeleted();

    public String getScript();
}
