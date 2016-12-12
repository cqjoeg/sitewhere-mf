package com.sitewhere.rest.model.search.field;


import com.sitewhere.rest.model.search.SearchCriteria;
import com.sitewhere.spi.search.field.IDeviceInterfaceSearchCriteria;

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
