package com.sitewhere.rest.model.search.field;


import com.sitewhere.rest.model.search.DateRangeSearchCriteria;
import com.sitewhere.spi.search.field.IDeviceAlertDataSearchCriteria;

import java.util.Date;

/**
 * Adds options specific to devicealertdata searches.
 *
 * @author Joeg
 */
public class DeviceAlertDataSearchCriteria extends DateRangeSearchCriteria implements IDeviceAlertDataSearchCriteria {

    /** hardwareid*/
    private String hardwareid;

    private String assignmentToken;

    public DeviceAlertDataSearchCriteria(int pageNumber, int pageSize, Date startDate, Date endDate) {
        super(pageNumber, pageSize, startDate, endDate);
    }

    public DeviceAlertDataSearchCriteria(int pageNumber, int pageSize, Date startDate, Date endDate, String hardwareid) {
        super(pageNumber, pageSize, startDate, endDate);
        this.hardwareid = hardwareid;
    }

    public DeviceAlertDataSearchCriteria(int pageNumber, int pageSize, Date startDate, Date endDate, String hardwareid, String assignmentToken) {
        super(pageNumber, pageSize, startDate, endDate);
        this.hardwareid = hardwareid;
        this.assignmentToken = assignmentToken;
    }

    @Override
    public String getHardwareid() {
        return hardwareid;
    }

    public void setHardwareid(String hardwareid) {
        this.hardwareid = hardwareid;
    }

    @Override
    public String getAssignmentToken() {
        return assignmentToken;
    }

    public void setAssignmentToken(String assignmentToken) {
        this.assignmentToken = assignmentToken;
    }
}
