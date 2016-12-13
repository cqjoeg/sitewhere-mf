package com.sitewhere.measurefilter;

import com.sitewhere.rest.model.device.field.DeviceMethodField;
import com.sitewhere.rest.model.search.DateRangeSearchCriteria;
import com.sitewhere.spi.SiteWhereException;
import com.sitewhere.spi.device.field.IDeviceInterface;
import com.sitewhere.spi.device.field.request.IDeviceInterfaceTransferSearchRequest;
import com.sitewhere.spi.search.ISearchResults;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 */
public abstract class DeviceInterfaceTransfer {

    private IDeviceInterface deviceInterface;
    private IDeviceInterfaceTransferSearchRequest deviceInterfaceTransferSearchRequest;
    private String token;
    private HttpServletRequest servletRequest;
    private String eventType;
    private List<DeviceMethodField> deviceMethodFields;
    private DateRangeSearchCriteria dateRangeSearchCriteria;
    private List<String> methodFieldName;


    public DeviceInterfaceTransfer(IDeviceInterface a, IDeviceInterfaceTransferSearchRequest b, String token, HttpServletRequest servletRequest) throws SiteWhereException, ScriptException {
        this.deviceInterface = a;
        this.deviceInterfaceTransferSearchRequest = b;
        this.token = token;
        this.servletRequest = servletRequest;

        eventType = setEventType(a);
        deviceMethodFields = setDeviceMethodFields(a);
        dateRangeSearchCriteria = setDateRangeSearchCriteria(b);
        methodFieldName = new ArrayList<String>();
        for (DeviceMethodField deviceMethodField : deviceMethodFields) {
            methodFieldName.add(deviceMethodField.getPname());
        }
        filterRequest(a, b, methodFieldName);
        setData(eventType, dateRangeSearchCriteria, token, servletRequest);

        transfer();
    }


    protected abstract void filterRequest(IDeviceInterface a, IDeviceInterfaceTransferSearchRequest b, List<String> methodFieldName);

    protected abstract String setEventType(IDeviceInterface a);

    protected abstract List<DeviceMethodField> setDeviceMethodFields(IDeviceInterface a);

    protected abstract DateRangeSearchCriteria setDateRangeSearchCriteria(IDeviceInterfaceTransferSearchRequest request);

    protected abstract ISearchResults setData(String eventType, DateRangeSearchCriteria criteria, String token, HttpServletRequest servletRequest) throws SiteWhereException;

    protected void transfer() throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("groovy");
        Iterator iter = deviceInterfaceTransferSearchRequest.getValues().entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            engine.put((String) key, value);
        }
        engine.eval("println 'hello'");
    }

}
