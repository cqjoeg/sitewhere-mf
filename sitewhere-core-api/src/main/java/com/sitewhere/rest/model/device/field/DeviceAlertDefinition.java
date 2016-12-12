package com.sitewhere.rest.model.device.field;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

/**
 * DeviceAlertDefinition
 *
 * @author Joeg
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceAlertDefinition implements Serializable {

    public static final String ALERT = "alert";
    
    /**
     * Serialization version identifier
     */
    private static final long serialVersionUID = 5782984784829975510L;

    private String type;

    private List<DeviceAlertRangeDefinition> keys;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<DeviceAlertRangeDefinition> getKeys() {
        return keys;
    }

    public void setKeys(List<DeviceAlertRangeDefinition> keys) {
        this.keys = keys;
    }
}
