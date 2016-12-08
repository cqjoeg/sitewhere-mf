package com.sitewhere.measurefilter.rest.model.device;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sitewhere.measurefilter.spi.device.IDeviceMethodDefinition;

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

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public List<DeviceMethodField> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<DeviceMethodField> fieldList) {
        this.fieldList = fieldList;
    }

}
