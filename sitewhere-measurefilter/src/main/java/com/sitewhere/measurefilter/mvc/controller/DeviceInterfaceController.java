package com.sitewhere.measurefilter.mvc.controller;

import com.sitewhere.SiteWhere;
import com.sitewhere.rest.model.device.field.request.DeviceInterfaceCreateRequest;
import com.sitewhere.rest.model.search.DateRangeSearchCriteria;
import com.sitewhere.rest.model.search.SearchResults;
import com.sitewhere.rest.model.search.field.DeviceInterfaceSearchCriteria;
import com.sitewhere.spi.SiteWhereException;
import com.sitewhere.spi.device.field.IDeviceInterface;
import com.sitewhere.spi.device.field.domain.IDeviceInterfaceEntity;
import com.sitewhere.spi.device.field.service.IDeviceInterfaceService;
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
 * Controller for deviceInterface
 *
 * @author Joeg
 */
@Controller
@CrossOrigin
@RequestMapping("/device/interface")
//@Api(value = "/device/interface", description = "Operations related to SiteWhere devices.")
public class DeviceInterfaceController extends MFBaseController {
    /**
     * 1. listDeviceInterfaceByHardWareId
     * 2. getDeviceInterfaceByName
     * 3. createDeviceInterface
     * 4. updateDeviceInterface  (include update parameter / method name / on or off this function)
     * 5. deleteDeviceInterface
     */

    @Autowired
    private IDeviceInterfaceService deviceInterfaceService;
    /**
     * Static logger instance
     */
    private static Logger LOGGER = Logger.getLogger(DeviceInterfaceController.class);

    /**
     * create device method
     *
     * @param deviceInterfaceCreateRequest
     * @param servletRequest
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @Secured({SiteWhereRoles.REST})
    public IDeviceInterface createDeviceInterface(@RequestBody DeviceInterfaceCreateRequest deviceInterfaceCreateRequest,
                                                  HttpServletRequest servletRequest) {
        IDeviceInterface result = deviceInterfaceService.insertDeviceInterface(deviceInterfaceCreateRequest);
        return result;
    }


    /**
     * update device method
     *
     * @param deviceInterfaceCreateRequest
     * @param servletRequest
     * @return
     */
    @RequestMapping(value = "/{hardwareId}/{methodName}", method = RequestMethod.PUT)
    @ResponseBody
    @Secured({SiteWhereRoles.REST})
    public IDeviceInterface updateDeviceInterface(
            @PathVariable String hardwareId,
            @PathVariable String methodName,
            @RequestBody DeviceInterfaceCreateRequest deviceInterfaceCreateRequest, HttpServletRequest servletRequest) {
        IDeviceInterface result = deviceInterfaceService.updateDeviceInterface(hardwareId, methodName, deviceInterfaceCreateRequest);
        return result;
    }


    /**
     * delete device method
     *
     * @param hardwareId
     * @param methodName
     * @param servletRequest
     * @return
     */
    @RequestMapping(value = "/{hardwareId}/{methodName}", method = RequestMethod.DELETE)
    @ResponseBody
    @Secured({SiteWhereRoles.REST})
    public IDeviceInterface deleteDeviceInterface(@PathVariable String hardwareId, @PathVariable String methodName, HttpServletRequest servletRequest) {
        deviceInterfaceService.deleteDeviceInterface(hardwareId, methodName);
        return null;
    }

    @RequestMapping(value = "/{hardwareId}", method = RequestMethod.GET)
    @ResponseBody
    @Secured({SiteWhereRoles.REST})
    public ISearchResults<IDeviceInterfaceEntity> listDeviceInterface(
            @PathVariable String hardwareId,
            @RequestParam(required = false) String methodName,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "100") int pageSize) {
        DeviceInterfaceSearchCriteria criteria = new DeviceInterfaceSearchCriteria(page, pageSize, hardwareId, methodName);
        Page<IDeviceInterfaceEntity> pageList = deviceInterfaceService.listDeviceInterface(criteria);
        return new SearchResults<IDeviceInterfaceEntity>(pageList.getContent(), pageList.getNumber());
    }


    @RequestMapping(value = "/{hardwareid}/{methodname}")
    @ResponseBody
    public ISearchResults transfer(@PathVariable String hardwareid, @PathVariable String methodname,
                                   @RequestParam(required = false, defaultValue = "1") int page,
                                   @RequestParam(required = false, defaultValue = "0") int pageSize,
                                   @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date startDate,
                                   @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date endDate,
                                   HttpServletRequest servletRequest) throws SiteWhereException {
        DateRangeSearchCriteria criteria =
                new DateRangeSearchCriteria(page, pageSize, startDate, endDate);
        String token = getAssignmentTokenByHardwareId(hardwareid, servletRequest);
//        deviceInterfaceService

        SiteWhere.getServer().getDeviceEventManagement(
                getTenant(servletRequest)).listDeviceMeasurements(token, criteria);

        return null;

    }

}
