package com.sitewhere.spi.device.field;



import com.sitewhere.rest.model.device.field.DeviceMethodDefinition;
import com.sitewhere.spi.common.IMetadataProviderEntity;

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

    public DeviceMethodDefinition getDefinition();

    public Boolean getDeleted();

    public String getScript();

}
