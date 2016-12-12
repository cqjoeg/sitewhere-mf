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

    private List<String> key;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getKey() {
        return key;
    }

    public void setKey(List<String> key) {
        this.key = key;
    }
}

