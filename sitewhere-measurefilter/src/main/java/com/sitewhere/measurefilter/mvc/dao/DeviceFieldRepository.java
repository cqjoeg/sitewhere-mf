package com.sitewhere.measurefilter.mvc.dao;

import com.sitewhere.measurefilter.mvc.domain.DeviceFieldEntity;
import com.sitewhere.measurefilter.mvc.service.IBaseService;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for device field
 *
 * @author Joeg
 */
@Repository
public interface DeviceFieldRepository extends PagingAndSortingRepository<DeviceFieldEntity,Long>{

    /**
     * 更具硬件id 和 field 类型进行查询
     * @param hardwareid
     * @param type
     * @return
     */
    public DeviceFieldEntity findByHardwareidAndType(String hardwareid, String type);

}
