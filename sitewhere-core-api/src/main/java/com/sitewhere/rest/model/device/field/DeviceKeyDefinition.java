package com.sitewhere.rest.model.device.field;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

/**
 * DeviceAlertRangeDefinition
 *
 * @author Joeg
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceKeyDefinition implements Serializable {

    /**
     * Serialization version identifier
     */
    private static final long serialVersionUID = 3565485779090119612L;

    private String key;

    private List<AlertRange> alertRanges;

    //extend 可在此处扩展其他的

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<AlertRange> getAlertRanges() {
        return alertRanges;
    }

    public void setAlertRanges(List<AlertRange> alertRanges) {
        this.alertRanges = alertRanges;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public class AlertRange implements Serializable {

        /**
         * Serialization version identifier
         */
        private static final long serialVersionUID = -2317791930385222811L;
        private Double from;
        private Double to;
        private String message;

        public Double getFrom() {
            return from;
        }

        public void setFrom(Double from) {
            this.from = from;
        }

        public Double getTo() {
            return to;
        }

        public void setTo(Double to) {
            this.to = to;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }



}



