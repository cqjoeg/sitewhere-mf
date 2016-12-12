package com.sitewhere.measurefilter.mvc.service.impl;

import com.sitewhere.measurefilter.mvc.dao.DeviceAlertDataRepository;
import com.sitewhere.measurefilter.mvc.domain.DeviceALertDataEntity;
import com.sitewhere.rest.model.device.field.DeviceAlertData;
import com.sitewhere.rest.model.search.field.DeviceAlertDataSearchCriteria;
import com.sitewhere.spi.device.field.domain.IDeviceALertDataEntity;
import com.sitewhere.spi.device.field.service.IDeviceAlertDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by CQ on 2016/11/21.
 */
@Service(DeviceAlertDataServiceImpl.DEVICE_ALERT_DATA_SERVICE_IMPL)
public class DeviceAlertDataServiceImpl implements IDeviceAlertDataService {

    @Autowired
    private DeviceAlertDataRepository deviceAlertDataRepository;

    @Override
    public Page<IDeviceALertDataEntity> listDeviceAlertDatas(DeviceAlertDataSearchCriteria criteria) {
        Order idOrder = new Order(Direction.DESC, "id");
        Sort sort = new Sort(idOrder);
        PageRequest pageRequest = new PageRequest(criteria.getPageNumber() - 1, criteria.getPageSize(), sort);
        return deviceAlertDataRepository.findByHardwareid(criteria.getHardwareid(), pageRequest);
    }

    @Transactional
    @Override
    public void insertDeviceAlertData(DeviceAlertData deviceAlertData) {
        if (deviceAlertData != null)
            deviceAlertDataRepository.save(buildDeviceAlertDataEntity(deviceAlertData));
    }

    @Override
    public void batchInsert(List<DeviceAlertData> deviceAlertDatas) {
        if (deviceAlertDatas != null && deviceAlertDatas.size() > 0)
            deviceAlertDataRepository.save(buildDeviceAlertDataEntitys(deviceAlertDatas));
    }

    /**
     * @return
     */
    public static DeviceALertDataEntity buildDefaultDeviceAlertDataEntity(String hardwareId, String type, String comments, Date date, Double value) {
        return new DeviceALertDataEntity(hardwareId, type, comments, date, value);
    }

    public static DeviceALertDataEntity buildDeviceAlertDataEntity(DeviceAlertData deviceAlertData) {
        if (deviceAlertData != null) {
            DeviceALertDataEntity deviceALertDataEntity = new DeviceALertDataEntity();
            deviceALertDataEntity.setHardwareid(deviceAlertData.getHardwareid());
            deviceALertDataEntity.setType(deviceAlertData.getType());
            deviceALertDataEntity.setComments(deviceAlertData.getComments());
            deviceALertDataEntity.setCreateddate(deviceAlertData.getCreateddate());
            deviceALertDataEntity.setValue(deviceAlertData.getValue());
            return deviceALertDataEntity;
        } else {
            return null;
        }
    }

    public static List<DeviceALertDataEntity> buildDeviceAlertDataEntitys(List<DeviceAlertData> deviceAlertDatas) {
        DeviceALertDataEntity defaultEntity = new DeviceALertDataEntity();
        defaultEntity.setHardwareid(deviceAlertDatas.get(0).getHardwareid());
        defaultEntity.setType(deviceAlertDatas.get(0).getType());
        defaultEntity.setComments(deviceAlertDatas.get(0).getComments());
        defaultEntity.setCreateddate(deviceAlertDatas.get(0).getCreateddate());
        defaultEntity.setValue(deviceAlertDatas.get(0).getValue());

        List<DeviceALertDataEntity> deviceALertDataEntities = new ArrayList<DeviceALertDataEntity>();
        for (DeviceAlertData entity : deviceAlertDatas) {
            defaultEntity.setComments(entity.getComments());
            defaultEntity.setCreateddate(entity.getCreateddate());
            defaultEntity.setValue(entity.getValue());
            deviceALertDataEntities.add(defaultEntity);
        }
        return deviceALertDataEntities;
    }


}
