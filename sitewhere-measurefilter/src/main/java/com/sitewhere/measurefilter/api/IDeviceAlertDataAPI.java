package com.sitewhere.measurefilter.api;

import com.sitewhere.measurefilter.mvc.domain.DeviceALertDataEntity;

import java.util.List;

/**
 * Interface of device alert date api
 *
 * @author Joeg
 */
public interface IDeviceAlertDataAPI {


    /**
     * 插入 单条 设备报警信息
     *
     * @param hardwareId
     * @param type
     * @param comments
     * @param value
     */
    public void insertDeviceAlertData(String hardwareId, String type, String comments, Double value);


    /**
     * 批量插入设备报警信息
     *
     * @param deviceALertDataEntities
     */
    public void batchInsert(List<DeviceALertDataEntity> deviceALertDataEntities);
}
