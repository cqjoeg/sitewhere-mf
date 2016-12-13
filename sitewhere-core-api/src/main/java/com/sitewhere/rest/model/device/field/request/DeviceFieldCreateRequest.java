package com.sitewhere.rest.model.device.field.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.Gson;
import com.sitewhere.spi.device.field.request.IDeviceFieldCreateRequest;

import java.io.Serializable;

/**
 * Model object for DeviceFieldCreateRequest
 *
 * @author Joeg
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceFieldCreateRequest implements IDeviceFieldCreateRequest, Serializable {

    /**
     * Serialization version identifier
     */
    private static final long serialVersionUID = -6740471634040228671L;

    /**
     * Unique hardware id for device
     */
    private String hardwareId;

    /**
     * field type
     */
    private String type;

    /**
     * field's comments
     */
    private String comments;

    /**
     * field's definition (needs other method to parsing)
     */
    private Object definition;

    /**
     * Determine whether the service start
     */
    private Boolean started;

    public String getHardwareId() {
        return hardwareId;
    }

    public void setHardwareId(String hardwareId) {
        this.hardwareId = hardwareId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Object getDefinition() {
        return definition;
    }

    public void setDefinition(Object definition) {
        this.definition = definition;
    }

    public Boolean getStarted() {
        return started;
    }

    public void setStarted(Boolean started) {
        this.started = started;
    }


    public String getDefinitionToString() {
        return new Gson().toJson(definition);
    }
}
