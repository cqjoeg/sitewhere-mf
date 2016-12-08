package com.sitewhere.measurefilter.mvc.service.impl;

import com.sitewhere.measurefilter.mvc.dao.DeviceAlertDataRepository;
import com.sitewhere.measurefilter.mvc.domain.DeviceALertDataEntity;
import com.sitewhere.measurefilter.mvc.service.IDeviceAlertDataService;
import com.sitewhere.measurefilter.spi.search.device.IDeviceAlertDataSearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * Created by CQ on 2016/11/21.
 */
@Service
public class DeviceAlertDataServiceImpl implements IDeviceAlertDataService {

    @Autowired
    private DeviceAlertDataRepository deviceAlertDataRepository;

    @Override
    public Page<DeviceALertDataEntity> listDeviceAlertDatas(IDeviceAlertDataSearchCriteria criteria) {
        Order idOrder = new Order(Direction.DESC, "id");
        Sort sort = new Sort(idOrder);
        PageRequest pageRequest = new PageRequest(criteria.getPageNumber() - 1, criteria.getPageSize(), sort);
        return deviceAlertDataRepository.findByHardwareid(criteria.getHardwareid(), pageRequest);
//        if (!ObjectUtils.isEmpty(criteria.getStartDate()) && !ObjectUtils.isEmpty(criteria.getEndDate())) {
////          return deviceAlertDataRepository.findByTimePeriod(criteria.getHardwareid(),criteria.getStartDate(),criteria.getEndDate(),pageRequest);
//            return null;
//        } else {
//            return deviceAlertDataRepository.findByHardwareid(criteria.getHardwareid(), pageRequest);
//        }

    }

    @Transactional
    @Override
    public void insertDeviceAlertData(DeviceALertDataEntity deviceALertDataEntity) {
        deviceAlertDataRepository.save(deviceALertDataEntity);
    }

    @Override
    public void batchInsert(List<DeviceALertDataEntity> deviceALertDataEntities) {
        deviceAlertDataRepository.save(deviceALertDataEntities);
    }

    /**
     * @return
     */
    public static DeviceALertDataEntity buildDefaultDeviceAlertDataEntity(String hardwareId, String type, String comments, Date date, Double value) {
        return new DeviceALertDataEntity(hardwareId, type, comments, date, value);
    }

}
