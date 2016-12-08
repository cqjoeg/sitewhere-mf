package com.sitewhere.measurefilter.rest.model.device;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sitewhere.measurefilter.rest.model.common.MetadataProviderEntity;
import com.sitewhere.measurefilter.spi.device.IDeviceInterface;

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

    private String definition;

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

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
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
}
