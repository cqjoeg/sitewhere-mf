package com.sitewhere.measurefilter.spi.search.device;


import com.sitewhere.measurefilter.spi.search.IDateRangeSearchCriteria;

/**
 * Search criteria particular to devicealertdata searches.
 *
 * @author Joeg
 */
public interface IDeviceAlertDataSearchCriteria extends IDateRangeSearchCriteria {

    public String getHardwareid();
}
