package com.sitewhere.measurefilter.mvc.service;

import com.sitewhere.measurefilter.mvc.domain.DeviceALertDataEntity;
import com.sitewhere.measurefilter.spi.search.device.IDeviceAlertDataSearchCriteria;
import org.springframework.data.domain.Page;

import java.util.List;


/**
 * Created by CQ on 2016/11/21.
 */
public interface IDeviceAlertDataService {

    public Page<DeviceALertDataEntity> listDeviceAlertDatas(IDeviceAlertDataSearchCriteria criteria);

    public void insertDeviceAlertData(DeviceALertDataEntity deviceALertDataEntity);

    public void batchInsert(List<DeviceALertDataEntity> deviceALertDataEntities);
}
