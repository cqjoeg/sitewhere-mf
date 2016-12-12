package com.sitewhere.spi.search.field;


import com.sitewhere.spi.search.ISearchCriteria;

/**
 * Search criteria particular to device interface searches.
 *
 * @author Joeg
 */
public interface IDeviceInterfaceSearchCriteria extends ISearchCriteria {

    public String getHardwareid();

    public String getMethodName();
}
