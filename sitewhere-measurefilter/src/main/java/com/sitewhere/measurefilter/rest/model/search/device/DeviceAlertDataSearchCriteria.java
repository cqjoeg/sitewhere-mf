package com.sitewhere.measurefilter.rest.model.search.device;

import com.sitewhere.measurefilter.rest.model.search.DateRangeSearchCriteria;
import com.sitewhere.measurefilter.spi.search.device.IDeviceAlertDataSearchCriteria;

import java.util.Date;

/**
 * Adds options specific to devicealertdata searches.
 *
 * @author Joeg
 */
public class DeviceAlertDataSearchCriteria extends DateRangeSearchCriteria implements IDeviceAlertDataSearchCriteria{

    /** hardwareid*/
    private String hardwareid;

    public DeviceAlertDataSearchCriteria(int pageNumber, int pageSize, Date startDate, Date endDate) {
        super(pageNumber, pageSize, startDate, endDate);
    }

    public DeviceAlertDataSearchCriteria(int pageNumber, int pageSize, Date startDate, Date endDate, String hardwareid) {
        super(pageNumber, pageSize, startDate, endDate);
        this.hardwareid = hardwareid;
    }

    @Override
    public String getHardwareid() {
        return hardwareid;
    }

    public void setHardwareid(String hardwareid) {
        this.hardwareid = hardwareid;
    }
}
