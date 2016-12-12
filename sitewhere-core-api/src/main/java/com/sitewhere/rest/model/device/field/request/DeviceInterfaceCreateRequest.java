package com.sitewhere.rest.model.device.field.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sitewhere.rest.model.common.MetadataProviderEntity;
import com.sitewhere.rest.model.device.field.DeviceMethodDefinition;
import com.sitewhere.spi.device.field.request.IDeviceInterfaceCreateRequest;

import java.io.Serializable;
import java.util.Date;

/**
 * Model object for DeviceInterfaceCreateRequest
 *
 * @author Joeg
 */

@JsonInclude(Include.NON_NULL)
public class DeviceInterfaceCreateRequest extends MetadataProviderEntity implements IDeviceInterfaceCreateRequest, Serializable {

    /**
     * Serialization version identifier
     */
    private static final long serialVersionUID = -1100398209386461524L;

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

//    public static DeviceInterfaceEntity buildDeviceInterfaceEntity(IDeviceInterfaceCreateRequest request) {
//        return new DeviceInterfaceEntity(
//                request.getHardwareid(),
//                request.getComments(),
//                new Date(),
//                request.getMethodname(),
//                new Gson().toJson(request.getDefinition()),
//                false,
//                request.getScript()
//        );
//    }
}
