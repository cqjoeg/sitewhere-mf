package com.sitewhere.measurefilter.mvc.dao;

import com.sitewhere.measurefilter.mvc.domain.DeviceALertDataEntity;
import com.sitewhere.spi.device.field.domain.IDeviceALertDataEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


/**
 * Repository for DeviceAlertData
 *
 * @author Joeg
 */

@Repository
public interface DeviceAlertDataRepository extends PagingAndSortingRepository<DeviceALertDataEntity, Long> {

    /**
     * 更具hardwareid 分页查找
     *
     * @param hardwareid
     * @param pageable
     * @return
     */
    Page<IDeviceALertDataEntity> findByHardwareid(String hardwareid, Pageable pageable);


//    Page<IDeviceALertDataEntity> findByHardwareidAndAssignmenttoken(String hardwareid, String assignmenttoken, Pageable pageable);


    Page<IDeviceALertDataEntity> findByAssignmenttoken(String assignmenttoken, Pageable pageable);



    /**
     * 更具开始时间和结束分页查找
     *
     * @param hardwareid
     * @param startDate
     * @param endDate
     * @param pageable
     * @return
     */
//    @Query("select dad.hardwareid, dad.type, dad.comments, dad. createddate, dad.value from DeviceALertDataEntity dad " +
//            "where dad.createddate > :startDate and dad.createddate < :endDate and dad.hardwareid = :hardwareid")
//    Page<DeviceAlertData> findByTimePeriod(@Param("hardwareid") String hardwareid, @Param("startDate") Date startDate, @Param("endDate") Date endDate, Pageable pageable);
}
