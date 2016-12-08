package com.sitewhere.measurefilter.mvc.controller;

import com.sitewhere.measurefilter.mvc.domain.DeviceALertDataEntity;
import com.sitewhere.measurefilter.mvc.service.IDeviceAlertDataService;
import com.sitewhere.measurefilter.rest.model.search.SearchResults;
import com.sitewhere.measurefilter.rest.model.search.device.DeviceAlertDataSearchCriteria;
import com.sitewhere.measurefilter.spi.search.ISearchResults;
import com.sitewhere.measurefilter.spi.search.device.IDeviceAlertDataSearchCriteria;
import com.sitewhere.measurefilter.spi.user.SiteWhereRoles;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Controller for DeviceAlertData
 *
 * @author Joeg
 */

@Controller
@CrossOrigin
@RequestMapping("/device/alert/data")
public class DeviceAlertDataController {

    /**
     * Static logger instance
     */
    private static Logger LOGGER = Logger.getLogger(DeviceAlertDataController.class);

    @Autowired
    private IDeviceAlertDataService deviceAlertDataService;

    /**
     * 2. listDeviceAlertDataByHardWareId
     * 3. listDeviceAlertDataByCriteria
     */

    /**
     * list device alert data by hardware id
     *
     * @param hardwareid
     * @param page
     * @param pageSize
     * @param startDate
     * @param endDate
     * @param servletRequest
     * @return
     */
    @RequestMapping(value = "/{hardwareid}", method = RequestMethod.GET)
    @ResponseBody
    @Secured({SiteWhereRoles.REST})
    public ISearchResults<DeviceALertDataEntity> listDeviceAlertDatas(
            @PathVariable String hardwareid,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "100") int pageSize,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date endDate,
            HttpServletRequest servletRequest) {
        IDeviceAlertDataSearchCriteria criteria = new DeviceAlertDataSearchCriteria(page, pageSize, startDate, endDate, hardwareid);
        Page<DeviceALertDataEntity> pageList = deviceAlertDataService.listDeviceAlertDatas(criteria);
        return new SearchResults<DeviceALertDataEntity>(pageList.getContent(), pageList.getNumber());
    }


}
