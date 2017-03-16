package com.sitewhere.rest.model.device.field;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

/**
 * DeviceFieldDefinition
 *
 * @author Joeg
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceFieldDefinition implements Serializable {


    /**
     * Serialization version identifier
     */
    private static final long serialVersionUID = 587323626904170473L;

    private String type;


    private List<DeviceKeyDefinition> keys;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<DeviceKeyDefinition> getKeys() {
        return keys;
    }

    public void setKeys(List<DeviceKeyDefinition> keys) {
        this.keys = keys;
    }
}

