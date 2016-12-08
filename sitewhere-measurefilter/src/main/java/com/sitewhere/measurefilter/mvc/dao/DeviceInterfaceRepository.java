package com.sitewhere.measurefilter.mvc.dao;

import com.sitewhere.measurefilter.mvc.domain.DeviceInterfaceEntity;
import com.sitewhere.measurefilter.rest.model.device.DeviceInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository for deviceinterface
 *
 * @author Joeg
 */
@Repository
public interface DeviceInterfaceRepository extends PagingAndSortingRepository<DeviceInterfaceEntity, Long> {


    /**
     * 更具硬件id 和 methodname 进行查询
     *
     * @param hardwareid
     * @param methodname
     * @return
     */
    public DeviceInterfaceEntity findByHardwareidAndMethodname(String hardwareid, String methodname);


    /**
     * 更具硬件id进行 分页查询
     *
     * @param hardwareid
     * @param pageable
     * @return
     */
    public Page<DeviceInterfaceEntity> findByHardwareid(String hardwareid, Pageable pageable);

    /**
     * 更具硬件id 和 methodname 进行 分页查询
     *
     * @param hardwareid
     * @param methodname
     * @param pageable
     * @return
     */
    public Page<DeviceInterfaceEntity> findByHardwareidAndMethodname(String hardwareid, String methodname, Pageable pageable);

}