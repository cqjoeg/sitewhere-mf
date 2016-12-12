package com.sitewhere.rest.model.device.field;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sitewhere.rest.model.common.MetadataProviderEntity;
import com.sitewhere.spi.device.field.IDeviceMethodField;

import java.io.Serializable;

/**
 * Model object for device method field
 *
 * @author Joeg
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceMethodField extends MetadataProviderEntity implements IDeviceMethodField, Serializable {

    /**
     * Serialization version identifier
     */
    private static final long serialVersionUID = -2672626298514293809L;
    /**
     * 参数类型  : STRING INTEGER BOOLEAN ...
     */
    private String ptype;

    /**
     * 参数名字 不能相同
     */
    private String pname;

    public String getPtype() {
        return ptype;
    }

    public void setPtype(String ptype) {
        this.ptype = ptype;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }


}
