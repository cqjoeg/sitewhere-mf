package com.sitewhere.measurefilter.mvc.controller;

import com.sitewhere.rest.model.device.field.request.DeviceFieldCreateRequest;
import com.sitewhere.spi.SiteWhereException;
import com.sitewhere.spi.device.field.IDeviceField;
import com.sitewhere.spi.device.field.service.IDeviceFieldService;
import com.sitewhere.spi.user.SiteWhereRoles;
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
public class DeviceFieldController extends MFBaseController{

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

    @RequestMapping(value = "/{hardwareId}", method = RequestMethod.GET)
    @ResponseBody
    @Secured({SiteWhereRoles.REST})
    public IDeviceField getDeviceFieldByHardwareId(
            @PathVariable String hardwareId, HttpServletRequest servletRequest)throws SiteWhereException {
        IDeviceField result = deviceFieldService.getDeviceFieldByHardwareId(hardwareId);
        return result;
    }

    /**
     * Create a device.
     *
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @Secured({SiteWhereRoles.REST})
    public IDeviceField createDeviceField(@RequestBody DeviceFieldCreateRequest request, HttpServletRequest servletRequest)
            throws SiteWhereException {
        IDeviceField result = deviceFieldService.insertDeviceField(request);
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
    public IDeviceField updateDeviceField(
            @PathVariable String hardwareId,
            @RequestBody DeviceFieldCreateRequest request, HttpServletRequest servletRequest)
            throws SiteWhereException {
        IDeviceField result = deviceFieldService.updateDeviceField(hardwareId, request);
        return result;
    }

    @RequestMapping(value = "/{hardwareId}/{type}", method = RequestMethod.DELETE)
    @ResponseBody
    @Secured({SiteWhereRoles.REST})
    public IDeviceField deleteDeviceField(
            @PathVariable String hardwareId, @PathVariable String type,
            HttpServletRequest servletRequest) {
        deviceFieldService.deleteDeviceField(hardwareId, type);
        return null;
    }

}
