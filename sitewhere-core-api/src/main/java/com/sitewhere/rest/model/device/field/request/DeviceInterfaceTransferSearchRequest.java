package com.sitewhere.rest.model.device.field.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sitewhere.spi.device.field.request.IDeviceInterfaceTransferSearchRequest;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * DeviceInterfaceTransfer searchCriteria
 *
 * @author Joeg.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceInterfaceTransferSearchRequest implements IDeviceInterfaceTransferSearchRequest, Serializable {


    private static final long serialVersionUID = 6016309757774572679L;
    /**
     * Start date for search
     */
    private Date startDate;

    /**
     * End date for search
     */
    private Date endDate;
    /**
     * Page number to view
     */
    private Integer pageNumber;

    /**
     * Number of records in a page of results
     */
    private Integer pageSize;


    private Map<String, Object> values;

//    public DeviceInterfaceTransferSearchRequest(Date startDate, Date endDate, Integer pageNumber, Integer pageSize, Map<String, Object> values) {
//        this.startDate = startDate;
//        this.endDate = endDate;
//        this.pageNumber = pageNumber;
//        this.pageSize = pageSize;
//        this.values = values;
//    }

    @Override
    public Map<String, Object> getValues() {
        return values;
    }

    public void setValues(Map<String, Object> values) {
        this.values = values;
    }

    @Override
    public Date getStartDate() {
        return startDate;
    }

    @Override
    public Date getEndDate() {
        return endDate;
    }

    @Override
    public Integer getPageNumber() {
        return pageNumber;
    }

    @Override
    public Integer getPageSize() {
        return pageSize;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
