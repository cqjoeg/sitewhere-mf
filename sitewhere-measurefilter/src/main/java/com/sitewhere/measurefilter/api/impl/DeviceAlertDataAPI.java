package com.sitewhere.measurefilter.api.impl;

import com.sitewhere.measurefilter.api.IDeviceAlertDataAPI;
import com.sitewhere.measurefilter.mvc.domain.DeviceALertDataEntity;
import com.sitewhere.measurefilter.mvc.service.IDeviceAlertDataService;
import com.sitewhere.measurefilter.mvc.service.impl.DeviceAlertDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * DeviceAlertDataAPI
 *
 * @author Joeg
 */
@Service("DeviceAlertDataAPI")
public class DeviceAlertDataAPI implements IDeviceAlertDataAPI {

    @Autowired
    IDeviceAlertDataService deviceAlertDataService;


    @Override
    public void insertDeviceAlertData(String hardwareId, String type, String comments, Double value) {
        deviceAlertDataService.insertDeviceAlertData(
                DeviceAlertDataServiceImpl.buildDefaultDeviceAlertDataEntity(hardwareId, type, comments, new Date(), value)
        );
    }

    @Override
    public void batchInsert(List<DeviceALertDataEntity> deviceALertDataEntities) {
        deviceAlertDataService.batchInsert(deviceALertDataEntities);
    }
}
