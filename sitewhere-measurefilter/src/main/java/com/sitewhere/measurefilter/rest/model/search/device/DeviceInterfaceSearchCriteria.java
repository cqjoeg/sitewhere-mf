package com.sitewhere.measurefilter.rest.model.search.device;

import com.sitewhere.measurefilter.rest.model.search.SearchCriteria;
import com.sitewhere.measurefilter.spi.search.device.IDeviceInterfaceSearchCriteria;

import java.util.Date;

/**
 * @author Joeg
 */
public class DeviceInterfaceSearchCriteria extends SearchCriteria implements IDeviceInterfaceSearchCriteria {

    /**
     * hardwareid
     */
    private String hardwareid;

    /**
     * method name
     */
    private String methodname;

    public DeviceInterfaceSearchCriteria(int pageNumber, int pageSize) {
        super(pageNumber, pageSize);
    }



    public DeviceInterfaceSearchCriteria(int pageNumber, int pageSize, String hardwareid, String methodname) {
        super(pageNumber, pageSize);
        this.hardwareid = hardwareid;
        this.methodname = methodname;
    }

    @Override
    public String getHardwareid() {
        return hardwareid;
    }

    @Override
    public String getMethodName() {
        return methodname;
    }


}
