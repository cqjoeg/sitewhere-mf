package com.sitewhere.spi.device.field.request;


import java.util.Date;
import java.util.Map;

/**
 * Created by CQ on 2016/12/13.
 */
public interface IDeviceInterfaceTransferSearchRequest {

    public Map<String, Object> getValues();

    public Date getStartDate();

    public Date getEndDate();

    public Integer getPageNumber();

    public Integer getPageSize();
}
