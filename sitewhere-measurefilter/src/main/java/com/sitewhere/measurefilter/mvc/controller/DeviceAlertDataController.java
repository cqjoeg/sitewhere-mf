package com.sitewhere.measurefilter.mvc.controller;

import com.sitewhere.rest.model.search.SearchResults;
import com.sitewhere.rest.model.search.field.DeviceAlertDataSearchCriteria;
import com.sitewhere.spi.device.field.domain.IDeviceALertDataEntity;
import com.sitewhere.spi.device.field.service.IDeviceAlertDataService;
import com.sitewhere.spi.search.ISearchResults;
import com.sitewhere.spi.user.SiteWhereRoles;
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
public class DeviceAlertDataController extends MFBaseController{

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
    public ISearchResults<IDeviceALertDataEntity> listDeviceAlertDatas(
            @PathVariable String hardwareid,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "100") int pageSize,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date endDate,
            HttpServletRequest servletRequest) {
        DeviceAlertDataSearchCriteria criteria = new DeviceAlertDataSearchCriteria(page, pageSize, startDate, endDate, hardwareid);
        Page<IDeviceALertDataEntity> pageList = deviceAlertDataService.listDeviceAlertDatas(criteria);
        return new SearchResults<IDeviceALertDataEntity>(pageList.getContent(), pageList.getNumber());
    }


}
