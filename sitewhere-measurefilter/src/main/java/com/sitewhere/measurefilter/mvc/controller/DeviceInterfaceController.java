package com.sitewhere.measurefilter.mvc.controller;

import com.sitewhere.SiteWhere;
import com.sitewhere.rest.model.device.field.request.DeviceInterfaceCreateRequest;
import com.sitewhere.rest.model.device.field.request.DeviceInterfaceTransferSearchRequest;
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
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
            @RequestParam(required = false, defaultValue = "100") int pageSize,
            HttpServletRequest servletRequest) throws SiteWhereException {

        DeviceInterfaceSearchCriteria criteria = new DeviceInterfaceSearchCriteria(page, pageSize, hardwareId, methodName);
        List<IDeviceInterfaceEntity> pageList = deviceInterfaceService.listDeviceInterface(criteria);
        return new SearchResults<IDeviceInterfaceEntity>(pageList, pageList.size());
    }


    @RequestMapping(value = "/{token}/token", method = RequestMethod.GET)
    @ResponseBody
    @Secured({SiteWhereRoles.REST})
    public ISearchResults<IDeviceInterfaceEntity> listDeviceInterface(
            @PathVariable String token,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "100") int pageSize,
            HttpServletRequest servletRequest) throws SiteWhereException {

        String hardwareId = SiteWhere.getServer().getDeviceManagement(getTenant(servletRequest)).getDeviceAssignmentByToken(token).getDeviceHardwareId();
        DeviceInterfaceSearchCriteria criteria = new DeviceInterfaceSearchCriteria(page, pageSize, hardwareId, null);
        List<IDeviceInterfaceEntity> pageList = deviceInterfaceService.listDeviceInterface(criteria);
        return new SearchResults<IDeviceInterfaceEntity>(pageList, pageList.size());
    }


    @RequestMapping(value = "/{hardwareid}/{methodname}", method = RequestMethod.POST)
    @ResponseBody
    @Secured({SiteWhereRoles.REST})
    public ISearchResults transfer(@PathVariable String hardwareid, @PathVariable String methodname,
                                   @RequestBody DeviceInterfaceTransferSearchRequest request,
//                                   @RequestParam(required = false, defaultValue = "1") int page,
//                                   @RequestParam(required = false, defaultValue = "0") int pageSize,
//                                   @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date startDate,
//                                   @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date endDate,
                                   HttpServletRequest servletRequest) throws SiteWhereException, ScriptException {
        //get assToken
        IDeviceInterface deviceInterface = deviceInterfaceService.getDeviceInterfaceByHardwareIdAndMethodName(hardwareid, methodname);
        String script = deviceInterface.getScript();
        String eventType = deviceInterface.getDefinition().getEventType();

        //后面 seacher 的数据都是 当前 hardwareid 下
        String token = getAssignmentTokenByHardwareId(hardwareid, servletRequest);

        DateRangeSearchCriteria criteria = new DateRangeSearchCriteria(request.getPageNumber(), request.getPageSize(), request.getStartDate(), request.getEndDate());
        ISearchResults<?> sr = null;
        switch (eventType) {
            case "measurements":
                sr = SiteWhere.getServer().getDeviceEventManagement(
                        getTenant(servletRequest)).listDeviceMeasurements(token, criteria);
                break;
            case "alert":
                sr = SiteWhere.getServer().getDeviceEventManagement(
                        getTenant(servletRequest)).listDeviceAlerts(token, criteria);

                break;
            case "location":
                sr = SiteWhere.getServer().getDeviceEventManagement(
                        getTenant(servletRequest)).listDeviceLocations(token, criteria);
                break;
        }

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("groovy");
        Iterator iter = request.getValues().entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            engine.put((String) key, value);
        }
        engine.put("list", sr.getResults());
        List resList = new ArrayList();
        engine.put("resList", resList);
        engine.eval(script);

        return new SearchResults((List) engine.get("resList"));
    }


}
