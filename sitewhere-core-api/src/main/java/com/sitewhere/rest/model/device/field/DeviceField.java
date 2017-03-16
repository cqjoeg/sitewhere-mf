package com.sitewhere.rest.model.device.field;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.Gson;
import com.sitewhere.rest.model.common.MetadataProviderEntity;
import com.sitewhere.spi.device.field.IDeviceField;
import com.sitewhere.spi.device.field.domain.IDeviceFieldEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * Device Field
 *
 * @author Joeg
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceField extends MetadataProviderEntity implements IDeviceField, Serializable {

    /**
     * Serialization version identifier
     */
    private static final long serialVersionUID = 6538133812168463632L;
    public static final String MEASUREMENTS = "measurements";

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

    /**
     * create date time
     */
    private Date createddate;

    /**
     * deleted
     */
    private Boolean deleted;


    public String getHardwareId() {
        return hardwareId;
    }

    public String getType() {
        return type;
    }

    public String getComments() {
        return comments;
    }

    public Object getDefinition() {
        return definition;
    }

    public Boolean getStarted() {
        return started;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setHardwareId(String hardwareId) {
        this.hardwareId = hardwareId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setDefinition(Object definition) {
        this.definition = definition;
    }

    public void setStarted(Boolean started) {
        this.started = started;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public static DeviceField copy(IDeviceFieldEntity definition) {
        DeviceField deviceField = new DeviceField();
        deviceField.setHardwareId(definition.getHardwareid());
        deviceField.setType(definition.getType());
        deviceField.setComments(definition.getComments());
            deviceField.setDefinition(new Gson().fromJson(definition.getDefinition(), DeviceFieldDefinition.class));
        deviceField.setStarted(definition.getStarted());
        deviceField.setCreateddate(definition.getCreateddate());
        deviceField.setDeleted(definition.getDeleted());
        return deviceField;
    }


}
