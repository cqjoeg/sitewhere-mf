package com.sitewhere.measurefilter.mvc.service.impl;

import com.sitewhere.measurefilter.mvc.dao.DeviceAlertDataRepository;
import com.sitewhere.measurefilter.mvc.domain.DeviceALertDataEntity;
import com.sitewhere.rest.model.device.field.DeviceAlertData;
import com.sitewhere.rest.model.search.field.DeviceAlertDataSearchCriteria;
import com.sitewhere.spi.device.field.domain.IDeviceALertDataEntity;
import com.sitewhere.spi.device.field.service.IDeviceAlertDataService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by CQ on 2016/11/21.
 */
@Service(DeviceAlertDataServiceImpl.DEVICE_ALERT_DATA_SERVICE_IMPL)
public class DeviceAlertDataServiceImpl implements IDeviceAlertDataService {

    @Autowired
    private DeviceAlertDataRepository deviceAlertDataRepository;

    @Override
    public List<IDeviceALertDataEntity> listDeviceAlertDatasByCriteria(DeviceAlertDataSearchCriteria criteria) {
        Order idOrder = new Order(Direction.DESC, "id");
        Sort sort = new Sort(idOrder);
        PageRequest pageRequest = new PageRequest(criteria.getPageNumber() - 1, criteria.getPageSize(), sort);

        if (StringUtils.isNotEmpty(criteria.getAssignmentToken())){
            return deviceAlertDataRepository.findByAssignmenttoken(criteria.getAssignmentToken(), pageRequest).getContent();
        } else {
            return deviceAlertDataRepository.findByHardwareid(criteria.getHardwareid(), pageRequest).getContent();
        }
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
     * build DeviceAlertDataEntity
     *
     * @param deviceAlertData
     * @return
     */
    public static DeviceALertDataEntity buildDeviceAlertDataEntity(DeviceAlertData deviceAlertData) {
        if (deviceAlertData != null) {
            DeviceALertDataEntity deviceALertDataEntity = new DeviceALertDataEntity();
            deviceALertDataEntity.setHardwareid(deviceAlertData.getHardwareid());
            deviceALertDataEntity.setType(deviceAlertData.getType());
            deviceALertDataEntity.setComments(deviceAlertData.getComments());
            deviceALertDataEntity.setCreateddate(new Timestamp(deviceAlertData.getCreateddate().getTime()));

            deviceALertDataEntity.setValue(deviceAlertData.getValue());
            deviceALertDataEntity.setRange(deviceAlertData.getFrom() + "-" + deviceAlertData.getTo());
            deviceALertDataEntity.setAssignmenttoken(deviceAlertData.getAssignmenttoken());
            return deviceALertDataEntity;
        } else {
            return null;
        }
    }

    /**
     * build DeviceAlertDataEntitys
     *
     * @param deviceAlertDatas
     * @return
     */
    public static List<DeviceALertDataEntity> buildDeviceAlertDataEntitys(List<DeviceAlertData> deviceAlertDatas) {
        List<DeviceALertDataEntity> deviceALertDataEntities = new ArrayList<DeviceALertDataEntity>();
        for (DeviceAlertData entity : deviceAlertDatas) {
            DeviceALertDataEntity defaultEntity = new DeviceALertDataEntity();
            defaultEntity.setHardwareid(entity.getHardwareid());
            defaultEntity.setType(entity.getType());
            defaultEntity.setComments(entity.getComments());
            defaultEntity.setCreateddate(new Timestamp(entity.getCreateddate().getTime()));
            defaultEntity.setValue(entity.getValue());

            defaultEntity.setComments(entity.getComments());
            defaultEntity.setValue(entity.getValue());
            defaultEntity.setRange(entity.getFrom() + "-" + entity.getTo());
            defaultEntity.setAssignmenttoken(entity.getAssignmenttoken());
            deviceALertDataEntities.add(defaultEntity);
        }
        return deviceALertDataEntities;
    }


}

