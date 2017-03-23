<!-- Dialog for operate interface -->
<%@ include file="../includes/templateLocationEntry.inc"%>
<%@ include file="../includes/templateMeasurementsEntry.inc"%>
<%@ include file="../includes/templateAlertEntry.inc"%>

<style>
    .my-modal{
        top:50%;
        left:30%;
        z-index:1050;
        width:1000px;
    }
    .my-modal-body{
        min-height: 300px;
    }
</style>
<div id="io-dialog" class="modal my-modal hide">
    <div class="modal-header k-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h3 id="iodialog-header" data-i18n="includes.invocationViewDialog.CommandInvocationDetails"><span id="iodialog-header-detail"></span></h3>
    </div>
    <div class="modal-body my-modal-body">
        <div>
            <div>
                <div class="sw-sublist-header form-horizontal">
                    <div class="control-group " id="input-content">
<!--                        <label class="label" for="input1" data-i18n="includes.Comments"></label>-->
<!--                        <div class=""><input type="text" value="11111" id="input1"/></div>-->
<!---->
<!--                        <label class="label" for="input2" data-i18n="includes.Comments"></label>-->
<!--                        <div class=""><input type="text" value="11111" id="input2"/></div>-->
                    </div>
                    <a href="javascript:void(0)" id="btn-command-interface" class="btn btn-mini btn-sw-success" data-i18n="public.CommandInterface"></a>
                </div>
            </div>
            <div id="io-content">
            <!-- location-->
                <div id="io-locations" style="display: none">
                    <div class="k-header sw-button-bar">
                        <div class="sw-button-bar-title" data-i18n="public.DeviceLocations"></div>
                        <div>
                            <a id="btn-filter-io-locations" class="btn" href="javascript:void(0)">
                                <i class="fa fa-filter sw-button-icon"></i> <span
                                    data-i18n="public.FilterResults">Filter Results</span>
                            </a> <a id="btn-refresh-locations" class="btn" href="javascript:void(0)">
                                <i class="fa fa-refresh sw-button-icon"></i> <span
                                    data-i18n="public.Refresh">Refresh</span>
                            </a>
                        </div>
                    </div>
                    <table id="locations-io">
                        <colgroup>
                            <col style="width: 20%;" />
                            <col style="width: 20%;" />
                            <col style="width: 20%;" />
                            <col style="width: 20%;" />
                        </colgroup>
                        <thead>
                        <tr>
                            <th data-i18n="public.Latitude"></th>
                            <th data-i18n="public.Longitude"></th>
                            <th data-i18n="public.Elevation"></th>
                            <th data-i18n="public.EventDate"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td colspan="5"></td>
                        </tr>
                        </tbody>
                    </table>
                    <div id="locations-pager-io" class="k-pager-wrap event-pager"></div>
                </div>
            <!-- measurements-->
                <div id="io-measurements" style="display: none;">
                    <div class="k-header sw-button-bar">
                        <div class="sw-button-bar-title"
                             data-i18n="public.DeviceMeasurements"></div>
                        <div>
                            <a id="btn-filter-io-measurements" class="btn"
                               href="javascript:void(0)"> <i
                                    class="fa fa-search sw-button-icon"></i> <span
                                    data-i18n="public.FilterResults">Filter Results</span></a>

<!--                               <a id="btn-refresh-measurements" class="btn" href="javascript:void(0)">-->
<!--                                <i class="fa fa-refresh sw-button-icon"></i> <span-->
<!--                                    data-i18n="public.Refresh">Refresh</span>-->
<!--                            </a>-->
                        </div>
                    </div>
                    <table id="measurements-io">
                        <colgroup>
                            <col style="width: 37%;" />
                            <col style="width: 20%;" />
                        </colgroup>
                        <thead>
                        <tr>
                            <th data-i18n="public.Measurements"></th>
                            <th data-i18n="public.EventDate"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td colspan="3"></td>
                        </tr>
                        </tbody>
                    </table>
                    <div id="measurements-pager-io" class="k-pager-wrap event-pager"></div>
                </div>
             <!-- alerts-->
                <div id="io-alerts" style="display: none;">
                    <div class="k-header sw-button-bar">
                        <div class="sw-button-bar-title" data-i18n="public.DeviceAlerts"></div>
                        <div>
                            <a id="btn-filter-io-alerts" class="btn" href="javascript:void(0)">
                                <i class="fa fa-search sw-button-icon"></i> <span
                                    data-i18n="public.FilterResults">Filter Results</span>
                            </a>
<!--                            <a id="btn-refresh-alerts" class="btn" href="javascript:void(0)">-->
<!--                                <i class="fa fa-refresh sw-button-icon"></i> <span-->
<!--                                    data-i18n="public.Refresh">Refresh</span>-->
<!--                            </a>-->
                        </div>
                    </div>
                    <table id="alerts-io">
                        <colgroup>
                            <col style="width: 10%;" />
                            <col style="width: 20%;" />
                            <col style="width: 10%;" />
                            <col style="width: 20%;" />
                        </colgroup>
                        <thead>
                        <tr>
                            <th data-i18n="public.Type"></th>
                            <th data-i18n="public.Message"></th>
                            <th data-i18n="public.Source"></th>
                            <th data-i18n="public.EventDate"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td colspan="5"></td>
                        </tr>
                        </tbody>
                    </table>
                    <div id="alerts-pager-io" class="k-pager-wrap event-pager"></div>
                </div>

            </div>
        </div>
    </div>
    <div class="modal-footer">
        <a href="javascript:void(0)" class="btn" data-dismiss="modal" data-i18n="public.Close"></a>
    </div>
</div>

<script type="text/x-kendo-template" id="tpl-io-input-value">
    <label class="label" for="input_"'+ i +'>'+interfaceDefinition.fieldList[i].pname+'</label>
    <div><input type="text" value="" id="input_"'+i+'/></div>
</script>



<!-- Script support for interface operate dialog -->
<script>

    /** Datasource for locations */
    var ioLocationsDS;

    /** Datasource for measurements */
    var ioMeasurementsDS;

    /** Datasource for alerts */
    var ioAlertsDS;

    /** interface data**/
    var interfaceData;

    /** interface definition **/
    var interfaceDefinition;


    /** fieldList Length**/
    var fieldListLength;

    /** current eventType**/
    var eventType;

    /** Size of pages from server */
    var pageSize = 100;

    /** Height of event grids */
    var gridHeight = 350;

    /** enum evenTypes**/
    var eventTypes = {
        "MEASUREMENTS":"measurements",
        "ALERTS":"alert",
        "LOCATION":"location"
    };

    var script;

    var eValue = {}

    $(document).ready(function() {
        ioMeasurementsDS = new kendo.data.DataSource(
            {
                transport : {
                    read : {
                        url : "${pageContext.request.contextPath}/api/assignments/"
                        + token + "/measurements",
                        beforeSend : function(req) {
                            req.setRequestHeader(
                                'Authorization',
                                "Basic ${basicAuth}");
                            req
                                .setRequestHeader(
                                    'X-SiteWhere-Tenant',
                                    "${tenant.authenticationToken}");
                        },
                        dataType : "json",
                    }
                },
                schema : {
                    data : "results",
                    total : "numResults",
                    parse : evalFilteEventResults,
                },
                serverPaging : true,
                serverSorting : true,
                pageSize : pageSize,
            });
        /** Create the measurements list */
        $("#measurements-io").kendoGrid(
            {
                dataSource : ioMeasurementsDS,
                rowTemplate : kendo.template($("#tpl-measurements-entry").html()),
                scrollable : true,
                height : gridHeight,
            });
        $("#measurements-pager-io").kendoPager({
            dataSource : ioMeasurementsDS
        });

        $('#btn-filter-io-measurements').attr('disabled', true);

        ioLocationsDS = new kendo.data.DataSource(
            {
                transport : {
                    read : {
                        url : "${pageContext.request.contextPath}/api/assignments/"
                        + token + "/locations",
                        beforeSend : function(req) {
                            req.setRequestHeader(
                                'Authorization',
                                "Basic ${basicAuth}");
                            req
                                .setRequestHeader(
                                    'X-SiteWhere-Tenant',
                                    "${tenant.authenticationToken}");
                        },
                        dataType : "json",
                    }
                },
                schema : {
                    data : "results",
                    total : "numResults",
                    parse : evalFilteEventResults,
                },
                serverPaging : true,
                serverSorting : true,
                pageSize : pageSize,
            });


        /** Create the location list */
        $("#locations-io").kendoGrid(
            {
                dataSource : ioLocationsDS,
                rowTemplate : kendo.template($("#tpl-location-entry").html()),
                scrollable : true,
                height : gridHeight
            });
        $("#locations-pager-io").kendoPager({
            dataSource : ioLocationsDS
        });
        $('#btn-filter-io-locations').attr('disabled', true);

        ioAlertsDS = new kendo.data.DataSource(
            {
                transport : {
                    read : {
                        url : "${pageContext.request.contextPath}/api/assignments/"
                        + token + "/alerts",
                        beforeSend : function(req) {
                            req.setRequestHeader(
                                'Authorization',
                                "Basic ${basicAuth}");
                            req
                                .setRequestHeader(
                                    'X-SiteWhere-Tenant',
                                    "${tenant.authenticationToken}");
                        },
                        dataType : "json",
                    }
                },
                schema : {
                    data : "results",
                    total : "numResults",
                    parse : evalFilteEventResults,
                },
                serverPaging : true,
                serverSorting : true,
                pageSize : pageSize,
            });
        /** Create the alerts list */
        $("#alerts-io").kendoGrid(
            {
                dataSource : ioAlertsDS,
                rowTemplate : kendo.template($(
                    "#tpl-alert-entry").html()),
                scrollable : true,
                height : gridHeight
            });
        $("#alerts-pager-io").kendoPager({
            dataSource : ioAlertsDS
        });
        $('#btn-filter-io-alerts').attr('disabled', true);


    });

    /** Open the dialog  **/
    function dioOpen(hardwareid, methodname) {
        $.getAuthJSON("${pageContext.request.contextPath}/api/device/interface/" + hardwareid + "?methodname=" + methodname,
            "${basicAuth}", "${tenant.authenticationToken}", dioSuccess, dioFailed);
    }

    /** Called on successful device interface load  **/
    function dioSuccess(data, status, jqXHR) {
        if(data.numResults != 0){
            interfaceData = data.results[0];
            interfaceDefinition = JSON.parse(data.results[0].definition);
            script = data.results[0].script;
        }

        //给头部 append  methodname 和 eventType
        var headerStr = ' - ' + interfaceData.methodname + ' - ' + interfaceDefinition.eventType;
        $('#iodialog-header-detail').html(headerStr);

        if(hasObj(interfaceDefinition , "fieldList")) {
            fieldListLength = interfaceDefinition.fieldList.length;
            eValue = {};
            var valuesStr = '';
            for (var i = 0, l = interfaceDefinition.fieldList.length; i < l; i++) {
                interfaceDefinition.fieldList[i].index = i;
                var mapTemple = '<label class="label" for="input_'+ i +'">'+interfaceDefinition.fieldList[i].pname+'</label>' +
                    '<div><input type="text" value="" id="input_'+i+'"/></div>';
                valuesStr += mapTemple;
            }
            $('#input-content').html(valuesStr);
        }

        // 显示 eventType 对应的列表
        switch (interfaceDefinition.eventType){
            case eventTypes.MEASUREMENTS:
                $('#io-measurements').css({"display":"block"});
                eventType = eventTypes.MEASUREMENTS;
                $('#btn-command-interface').click(function () {

                    $.each($('label[for^=input_]'), function (index, item) {

                        var pname = interfaceDefinition.fieldList[index].pname;
                        eValue[pname]  =  $('#input_' + index).val() ;
                    });

                    ioMeasurementsDS.read();
                });
                break;
            case eventTypes.LOCATION:
                $('#io-locations').css({"display":"block"});
                eventType = eventTypes.LOCATION;
                $('#btn-command-interface').click(function () {
                    ioLocationsDS.read();
                });
                break;
            case eventTypes.ALERTS:
                $('#io-alerts').css({"display":"block"});
                eventType = eventTypes.ALERTS;
                $('#btn-command-interface').click(function () {
                    ioAlertsDS.read();
                });
                break;
            default:
                break;
        }

        $('#io-dialog').modal('show');
    }

    /** Handle error on getting device interface  **/
    function dioFailed(jqXHR, textStatus, errorThrown) {
        handleError(jqXHR, "Unable to load device interface to view.");
    }

    /** load  interface script  ,use eval(script) to deal with retun data **/
    function evalFilteEventResults(response) {
        var res = [];
        $.each(response.results, function(index, item) {
            eval(script);
        });
        response.results = res;
        response.numResults = res.length;
        return response;
    }

</script>