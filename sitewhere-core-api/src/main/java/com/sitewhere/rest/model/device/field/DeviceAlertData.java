package com.sitewhere.rest.model.device.field;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sitewhere.rest.model.common.MetadataProviderEntity;
import com.sitewhere.spi.device.field.IDeviceAlertData;

import java.io.Serializable;
import java.util.Date;


/**
 * Model object for devicealertdata
 *
 * @author Joeg
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceAlertData extends MetadataProviderEntity implements IDeviceAlertData, Serializable {

    /** Serialization version identifier */
    private static final long serialVersionUID = -3341695175310450879L;

    private String hardwareid;

    private String type;

    private String comments;

    private Date createddate;

    private Double value;


    public DeviceAlertData(String hardwareid, String type, String comments, Date createddate, Double value) {
        this.hardwareid = hardwareid;
        this.type = type;
        this.comments = comments;
        this.createddate = createddate;
        this.value = value;
    }

    public String getHardwareid() {
        return hardwareid;
    }

    public void setHardwareid(String hardwareid) {
        this.hardwareid = hardwareid;
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

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }


}

