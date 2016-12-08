package com.sitewhere.measurefilter.mvc.controller;

import com.sitewhere.measurefilter.mvc.service.IDeviceFieldService;
import com.sitewhere.measurefilter.rest.model.device.request.DeviceFieldCreateRequest;
import com.sitewhere.measurefilter.spi.IResult;
import com.sitewhere.measurefilter.spi.SiteWhereException;
import com.sitewhere.measurefilter.spi.user.SiteWhereRoles;
import com.wordnik.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * Controller for mfdevice
 *
 * @author Joeg
 */

@Controller
@CrossOrigin
@RequestMapping(value = "/device/field")
//@Api(value = "/device/field", description = "Operations related to SiteWhere devices.")
public class DeviceFieldController {

    /**
     * Static logger instance
     */
    private static Logger LOGGER = Logger.getLogger(DeviceFieldController.class);

    @Autowired
    private IDeviceFieldService deviceFieldService;

    /**
     * function
     * 1. createDeviceField
     * 2. updateDeviceField
     * 3. deleteDeviceField
     * 4. getDeviceFieldByHardwareId
     * 5.
     * */

    /**
     * Create a device.
     *
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "Create new DeviceField")
    @Secured({SiteWhereRoles.REST})
    public IResult createDeviceField(@RequestBody DeviceFieldCreateRequest request, HttpServletRequest servletRequest)
            throws SiteWhereException {
        IResult result = deviceFieldService.insertDeviceField(request);
        return result;
    }

    /**
     * Update devicefield
     *
     * @param hardwareId
     * @param request
     * @param servletRequest
     * @return
     * @throws SiteWhereException
     */
    @RequestMapping(value = "/{hardwareId}", method = RequestMethod.PUT)
    @ResponseBody
    @Secured({SiteWhereRoles.REST})
    public IResult updateDeviceField(
            @PathVariable String hardwareId,
            @RequestBody DeviceFieldCreateRequest request, HttpServletRequest servletRequest)
            throws SiteWhereException {
        IResult result = deviceFieldService.updateDeviceField(hardwareId, request);
        return result;
    }

    @RequestMapping(value = "/{hardwareId}/{type}", method = RequestMethod.DELETE)
    @ResponseBody
    @Secured({SiteWhereRoles.REST})
    public IResult deleteDeviceField(
            @PathVariable String hardwareId, @PathVariable String type,
            HttpServletRequest servletRequest) {
        IResult result = deviceFieldService.deleteDeviceField(hardwareId, type);
        return result;
    }

}
