package com.sitewhere.measurefilter.mvc.controller;

import com.sitewhere.measurefilter.mvc.domain.DeviceInterfaceEntity;
import com.sitewhere.measurefilter.mvc.service.IDeviceInterfaceService;
import com.sitewhere.measurefilter.rest.model.device.request.DeviceInterfaceCreateRequest;
import com.sitewhere.measurefilter.rest.model.search.SearchResults;
import com.sitewhere.measurefilter.rest.model.search.device.DeviceInterfaceSearchCriteria;
import com.sitewhere.measurefilter.spi.IResult;
import com.sitewhere.measurefilter.spi.search.ISearchResults;
import com.sitewhere.measurefilter.spi.search.device.IDeviceInterfaceSearchCriteria;
import com.sitewhere.measurefilter.spi.user.SiteWhereRoles;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller for deviceInterface
 *
 * @author Joeg
 */
@Controller
@CrossOrigin
@RequestMapping("/device/interface")
//@Api(value = "/device/interface", description = "Operations related to SiteWhere devices.")
public class DeviceInterfaceController {
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
    public IResult createDeviceInterface(@RequestBody DeviceInterfaceCreateRequest deviceInterfaceCreateRequest,
                                         HttpServletRequest servletRequest) {
        IResult result = deviceInterfaceService.insertDeviceInterface(deviceInterfaceCreateRequest);
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
    public IResult updateDeviceInterface(
            @PathVariable String hardwareId,
            @PathVariable String methodName,
            @RequestBody DeviceInterfaceCreateRequest deviceInterfaceCreateRequest, HttpServletRequest servletRequest) {
        IResult result = deviceInterfaceService.updateDeviceInterface(hardwareId, methodName, deviceInterfaceCreateRequest);
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
    public IResult deleteDeviceInterface(@PathVariable String hardwareId, @PathVariable String methodName, HttpServletRequest servletRequest) {
        IResult result = deviceInterfaceService.deleteDeviceInterface(hardwareId, methodName);
        return result;
    }

    @RequestMapping(value = "/{hardwareId}", method = RequestMethod.GET)
    @ResponseBody
    @Secured({SiteWhereRoles.REST})
    public ISearchResults<DeviceInterfaceEntity> listDeviceInterface(
            @PathVariable String hardwareId,
            @RequestParam(required = false) String methodName,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "100") int pageSize) {
        IDeviceInterfaceSearchCriteria criteria = new DeviceInterfaceSearchCriteria(page, pageSize, hardwareId, methodName);
        Page<DeviceInterfaceEntity> pageList = deviceInterfaceService.listDeviceInterface(criteria);
        return new SearchResults<DeviceInterfaceEntity>(pageList.getContent(), pageList.getNumber());
    }
}
