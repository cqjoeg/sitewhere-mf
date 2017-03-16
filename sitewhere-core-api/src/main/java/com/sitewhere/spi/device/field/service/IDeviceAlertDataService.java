package com.sitewhere.spi.device.field.service;

import com.sitewhere.rest.model.device.field.DeviceAlertData;
import com.sitewhere.rest.model.search.field.DeviceAlertDataSearchCriteria;
import com.sitewhere.spi.device.field.domain.IDeviceALertDataEntity;

import java.util.List;


/**
 * Created by CQ on 2016/11/21.
 */
public interface IDeviceAlertDataService {

    public static final String DEVICE_ALERT_DATA_SERVICE_IMPL = "DeviceAlertDataServiceImpl";

    /**
     * search by Criteria
     *
     * @param criteria
     * @return
     */
    public List<IDeviceALertDataEntity> listDeviceAlertDatasByCriteria(DeviceAlertDataSearchCriteria criteria);


    /**
     * insert a device alert data
     *
     * @param deviceAlertData
     * @return
     */
    public void insertDeviceAlertData(DeviceAlertData deviceAlertData);

    /**
     * Batch insert
     *
     * @param deviceAlertDatas
     */
    public void batchInsert(List<DeviceAlertData> deviceAlertDatas);

}
