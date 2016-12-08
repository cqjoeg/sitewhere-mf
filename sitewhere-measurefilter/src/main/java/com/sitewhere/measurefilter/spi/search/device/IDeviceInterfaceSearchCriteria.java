package com.sitewhere.measurefilter.spi.search.device;


import com.sitewhere.measurefilter.spi.search.ISearchCriteria;

/**
 * Search criteria particular to device interface searches.
 *
 * @author Joeg
 */
public interface IDeviceInterfaceSearchCriteria extends ISearchCriteria {

    public String getHardwareid();

    public String getMethodName();
}
