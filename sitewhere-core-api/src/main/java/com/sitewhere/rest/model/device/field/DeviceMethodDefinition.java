package com.sitewhere.rest.model.device.field;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sitewhere.rest.model.common.MetadataProviderEntity;
import com.sitewhere.spi.device.field.IDeviceMethodDefinition;

import java.io.Serializable;
import java.util.List;

/**
 * Model object for device method definition
 *
 * @author Joeg
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceMethodDefinition implements IDeviceMethodDefinition, Serializable {


    /**
     * Serialization version identifier
     */
    private static final long serialVersionUID = 4475802786874455080L;

    private String methodName;

    private List<DeviceMethodField> fieldList;

    private String eventType;


    public String getMethodName() {
        return methodName;
    }

    public List<DeviceMethodField> getFieldList() {
        return fieldList;
    }

    public String getEventType() {
        return eventType;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setFieldList(List<DeviceMethodField> fieldList) {
        this.fieldList = fieldList;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
}
