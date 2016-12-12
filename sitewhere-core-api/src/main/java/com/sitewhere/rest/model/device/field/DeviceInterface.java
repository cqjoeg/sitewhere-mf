package com.sitewhere.rest.model.device.field;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.Gson;
import com.sitewhere.rest.model.common.MetadataProviderEntity;
import com.sitewhere.spi.device.field.IDeviceInterface;
import com.sitewhere.spi.device.field.domain.IDeviceInterfaceEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * Model object for device interface(method)
 *
 * @author Joeg
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceInterface extends MetadataProviderEntity implements IDeviceInterface, Serializable {

    /**
     * Serialization version identifier
     */
    private static final long serialVersionUID = -6726214439780173563L;

    private String hardwareid;

    private String comments;

    private Date createddate;

    private String methodname;

    private DeviceMethodDefinition definition;

    private Boolean deleted;

    private String script;


    public String getHardwareid() {
        return hardwareid;
    }

    public void setHardwareid(String hardwareid) {
        this.hardwareid = hardwareid;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    public String getMethodname() {
        return methodname;
    }

    public void setMethodname(String methodname) {
        this.methodname = methodname;
    }

    public DeviceMethodDefinition getDefinition() {
        return definition;
    }

    public void setDefinition(DeviceMethodDefinition definition) {
        this.definition = definition;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public static DeviceInterface copy(IDeviceInterfaceEntity entity) {
        DeviceInterface deviceInterface = new DeviceInterface();
        deviceInterface.setHardwareid(entity.getHardwareid());
        deviceInterface.setComments(entity.getComments());
        deviceInterface.setCreateddate(entity.getCreateddate());
        deviceInterface.setMethodname(entity.getMethodname());
        deviceInterface.setDefinition(new Gson().fromJson(entity.getDefinition(), DeviceMethodDefinition.class));
        deviceInterface.setDeleted(entity.getDeleted());
        deviceInterface.setScript(entity.getScript());
        return deviceInterface;
    }


}
