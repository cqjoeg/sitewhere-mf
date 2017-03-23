package com.sitewhere.measurefilter.mvc.service.impl;

import com.google.gson.Gson;
import com.sitewhere.measurefilter.mvc.dao.DeviceInterfaceRepository;
import com.sitewhere.measurefilter.mvc.domain.DeviceInterfaceEntity;
import com.sitewhere.rest.model.device.field.DeviceInterface;
import com.sitewhere.rest.model.device.field.request.DeviceInterfaceCreateRequest;
import com.sitewhere.rest.model.search.field.DeviceInterfaceSearchCriteria;
import com.sitewhere.spi.device.field.IDeviceInterface;
import com.sitewhere.spi.device.field.domain.IDeviceInterfaceEntity;
import com.sitewhere.spi.device.field.request.IDeviceInterfaceCreateRequest;
import com.sitewhere.spi.device.field.service.IDeviceInterfaceService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * DeviceInterfaceserviceimpl
 *
 * @author Joeg
 */
@Service
public class DeviceInterfaceServiceImpl implements IDeviceInterfaceService {

    /**
     * Static logger instance
     */
    private static Logger LOGGER = Logger.getLogger(DeviceFieldServiceImpl.class);

    /**
     * device interface repository
     */
    @Autowired
    private DeviceInterfaceRepository deviceInterfaceRepository;


    @Override
    public IDeviceInterface insertDeviceInterface(DeviceInterfaceCreateRequest deviceInterfaceCreateRequest) {

        DeviceInterfaceEntity deviceInterfaceEntity = deviceInterfaceRepository.findByHardwareidAndMethodname(deviceInterfaceCreateRequest.getHardwareid(), deviceInterfaceCreateRequest.getMethodname());
        if (ObjectUtils.isEmpty(deviceInterfaceEntity)) {
            DeviceInterfaceEntity deviceInterfaceEntityVO = buildDeviceInterfaceEntity(deviceInterfaceCreateRequest);
            return DeviceInterface.copy(deviceInterfaceRepository.save(deviceInterfaceEntityVO));
        } else {
            return null;
        }

    }

    @Override
    public IDeviceInterface updateDeviceInterface(String hardwareId, String methodName, DeviceInterfaceCreateRequest deviceInterfaceCreateRequest) {

        DeviceInterfaceEntity deviceInterfaceEntity = deviceInterfaceRepository.findByHardwareidAndMethodname(hardwareId, methodName);
        if (ObjectUtils.isEmpty((deviceInterfaceEntity))) {
            return null;
        } else {
            deviceInterfaceEntity.setDefinition(new Gson().toJson(deviceInterfaceCreateRequest.getDefinition()));
            deviceInterfaceEntity.setComments(deviceInterfaceCreateRequest.getComments());
            deviceInterfaceEntity.setDeleted(deviceInterfaceCreateRequest.getDeleted());
            deviceInterfaceEntity.setScript(deviceInterfaceCreateRequest.getScript());
            return DeviceInterface.copy(deviceInterfaceRepository.save(deviceInterfaceEntity));
        }
    }

    @Override
    public void deleteDeviceInterface(String hardwareId, String methodName) {
        DeviceInterfaceEntity deviceInterfaceEntity = deviceInterfaceRepository.findByHardwareidAndMethodname(hardwareId, methodName);
        if (!ObjectUtils.isEmpty(deviceInterfaceEntity)) {
            deviceInterfaceRepository.delete(deviceInterfaceEntity);
        }
    }

    @Override
    public List<IDeviceInterfaceEntity> listDeviceInterface(DeviceInterfaceSearchCriteria criteria) {
        Sort.Order idOrder = new Sort.Order(Sort.Direction.DESC, "id");
        Sort sort = new Sort(idOrder);
        PageRequest pageRequest = new PageRequest(criteria.getPageNumber() - 1, criteria.getPageSize(), sort);

        if (StringUtils.isEmpty(criteria.getMethodName())) {
            return deviceInterfaceRepository.findByHardwareid(criteria.getHardwareid(), pageRequest).getContent();
        } else {
            return deviceInterfaceRepository.findByHardwareidAndMethodname(criteria.getHardwareid(), criteria.getMethodName(), pageRequest).getContent();
        }
    }

    @Override
    public IDeviceInterface getDeviceInterfaceByHardwareIdAndMethodName(String hardwareId, String methodName) {
        DeviceInterfaceEntity deviceInterfaceEntity = deviceInterfaceRepository.findByHardwareidAndMethodname(hardwareId, methodName);
        if (ObjectUtils.isEmpty(deviceInterfaceEntity)){
            return null;
        }
        return DeviceInterface.copy(deviceInterfaceEntity) ;
    }

    @Override
    public IDeviceInterface getDeviceInterfaceById(Integer id) {
        DeviceInterfaceEntity deviceInterfaceEntity = deviceInterfaceRepository.findById(id);
        if(ObjectUtils.isEmpty(deviceInterfaceEntity)){
            return  null;
        }
        return DeviceInterface.copy(deviceInterfaceEntity);
    }

    public static DeviceInterfaceEntity buildDeviceInterfaceEntity(IDeviceInterfaceCreateRequest request) {

        return new DeviceInterfaceEntity(
                request.getHardwareid(),
                request.getComments(),
                new Date(),
                request.getMethodname(),
                new Gson().toJson(request.getDefinition()),
                false,
                request.getScript()
        );
    }


}
