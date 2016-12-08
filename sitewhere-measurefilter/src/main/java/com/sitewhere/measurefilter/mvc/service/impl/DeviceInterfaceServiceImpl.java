package com.sitewhere.measurefilter.mvc.service.impl;

import com.google.gson.Gson;
import com.sitewhere.measurefilter.mvc.dao.DeviceInterfaceRepository;
import com.sitewhere.measurefilter.mvc.domain.DeviceInterfaceEntity;
import com.sitewhere.measurefilter.mvc.service.IDeviceInterfaceService;
import com.sitewhere.measurefilter.rest.JsonResult;
import com.sitewhere.measurefilter.rest.model.device.request.DeviceInterfaceCreateRequest;
import com.sitewhere.measurefilter.spi.IResult;
import com.sitewhere.measurefilter.spi.MFSqlException;
import com.sitewhere.measurefilter.spi.device.request.IDeviceInterfaceCreateRequest;
import com.sitewhere.measurefilter.spi.search.device.IDeviceInterfaceSearchCriteria;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

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
    public IResult insertDeviceInterface(IDeviceInterfaceCreateRequest deviceInterfaceCreateRequest) {

        try {
            DeviceInterfaceEntity deviceInterfaceEntity = deviceInterfaceRepository.findByHardwareidAndMethodname(deviceInterfaceCreateRequest.getHardwareid(), deviceInterfaceCreateRequest.getMethodname());
            if (ObjectUtils.isEmpty(deviceInterfaceEntity)) {
                DeviceInterfaceEntity deviceInterfaceEntityVO = DeviceInterfaceCreateRequest.buildDeviceInterfaceEntity(deviceInterfaceCreateRequest);
                deviceInterfaceRepository.save(deviceInterfaceEntityVO);
            } else {
                return JsonResult.failure("The method name existing in the interface, please use the different name of the method");
            }


        } catch (MFSqlException e) {
            LOGGER.error(e.getErrorMsg());
            return JsonResult.failure("save error");
        }
        return JsonResult.success("save success", null);

    }

    @Override
    public IResult updateDeviceInterface(String hardwareId, String methodName, IDeviceInterfaceCreateRequest deviceInterfaceCreateRequest) {

        try {
            DeviceInterfaceEntity deviceInterfaceEntity = deviceInterfaceRepository.findByHardwareidAndMethodname(hardwareId, methodName);
            if (ObjectUtils.isEmpty((deviceInterfaceEntity))) {
                return JsonResult.failure("Save failed, there is no this device or method");
            } else {
                deviceInterfaceEntity.setDefinition(new Gson().toJson(deviceInterfaceCreateRequest.getDefinition()));
                deviceInterfaceEntity.setComments(deviceInterfaceCreateRequest.getComments());
                deviceInterfaceEntity.setDeleted(deviceInterfaceCreateRequest.getDeleted());
                deviceInterfaceEntity.setScript(deviceInterfaceCreateRequest.getScript());
                deviceInterfaceRepository.save(deviceInterfaceEntity);
            }

        } catch (MFSqlException e) {
            LOGGER.error(e.getErrorMsg());
            return JsonResult.failure("update error");
        }
        return JsonResult.success("update success", null);
    }

    @Override
    public IResult deleteDeviceInterface(String hardwareId, String methodName) {
        try {
            DeviceInterfaceEntity deviceInterfaceEntity = deviceInterfaceRepository.findByHardwareidAndMethodname(hardwareId, methodName);
            if (!ObjectUtils.isEmpty(deviceInterfaceEntity)) {
                deviceInterfaceRepository.delete(deviceInterfaceEntity);
            } else {
                return JsonResult.failure("delete failed, there is no this device or method");
            }

        } catch (MFSqlException e) {
            LOGGER.error(e.getErrorMsg());
            return JsonResult.failure("delete error");
        }
        return JsonResult.success("delete success", null);
    }

    @Override
    public Page<DeviceInterfaceEntity> listDeviceInterface(IDeviceInterfaceSearchCriteria criteria) {
        Sort.Order idOrder = new Sort.Order(Sort.Direction.DESC, "id");
        Sort sort = new Sort(idOrder);
        PageRequest pageRequest = new PageRequest(criteria.getPageNumber() - 1, criteria.getPageSize(), sort);

        if (StringUtils.isEmpty(criteria.getMethodName())) {
            return deviceInterfaceRepository.findByHardwareid(criteria.getHardwareid(), pageRequest);
        } else {
            return deviceInterfaceRepository.findByHardwareidAndMethodname(criteria.getHardwareid(), criteria.getMethodName(), pageRequest);
        }
    }


    /*
    @Override
    public IResult updateDeviceInterface(IDeviceInterfaceCreateRequest deviceInterfaceCreateRequest) {

        try {

        } catch (MFSqlException e) {
            LOGGER.error(e.getErrorMsg());
            return JsonResult.failure("save error");
        }
        return JsonResult.success("save success", null);
    }
    */


}
