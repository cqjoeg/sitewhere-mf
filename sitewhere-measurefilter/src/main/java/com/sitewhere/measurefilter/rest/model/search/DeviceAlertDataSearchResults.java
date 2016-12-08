package com.sitewhere.measurefilter.rest.model.search;

import com.sitewhere.measurefilter.mvc.domain.DeviceALertDataEntity;

import java.util.List;

/**
 * Created by CQ on 2016/11/21.
 */
public class DeviceAlertDataSearchResults extends SearchResults<DeviceALertDataEntity> {
    public DeviceAlertDataSearchResults(List<DeviceALertDataEntity> all) {
        super(all);
    }

    public DeviceAlertDataSearchResults(List<DeviceALertDataEntity> page, long total) {
        super(page, total);
    }
}
