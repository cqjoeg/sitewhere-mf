package com.sitewhere.spi.search.field;


import com.sitewhere.spi.search.IDateRangeSearchCriteria;

/**
 * Search criteria particular to devicealertdata searches.
 *
 * @author Joeg
 */
public interface IDeviceAlertDataSearchCriteria extends IDateRangeSearchCriteria {

    public String getHardwareid();
}
