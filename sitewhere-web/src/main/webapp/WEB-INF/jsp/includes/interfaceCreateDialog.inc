<!-- Dialog for creating a interface -->
<div id="ic-dialog" class="modal hide">
    <div class="modal-header k-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h3 data-i18n="includes.CreateInterface"></h3>
    </div>
    <div class="modal-body">
        <div id="ic-tabs">
            <ul>
                <li class="k-state-active" data-i18n="includes.InterfaceDetails"></li>
                <li  data-i18n="public.Parameter"></li>
                <li  data-i18n="public.Script"></li>
            </ul>
<!--  Interface Details -->
            <div>
                <form id="ic-general-form" class="form-horizontal" style="padding-top: 20px;">
                    <div class="control-group">
                        <label class="control-label" for="ic-hardware-id" data-i18n="public.HardwareId"> </label>
                        <div class="controls">
                            <input type="text" id="ic-hardware-id" title="#= i18next("public.HardwareId") #" class="input-xlarge">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="ic-interface-name" data-i18n="includes.InterfaceName"> </label>
                        <div class="controls">
                            <input type="text" id="ic-interface-name" title="#= i18next("includes.InterfaceName") #" class="input-xlarge">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="ic-event-type" data-i18n="public.Site"></label>
                        <div class="controls">
                            <select id="ic-event-type"></select>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="ic-comments" data-i18n="includes.Comments"></label>
                        <div class="controls">
                            <textarea id="ic-comments" class="input-xlarge" style="height: 8em;"></textarea>
                        </div>
                    </div>
                </form>
            </div>
<!--  field list-->
            <div>
                <div>
                    <div id="ic-metadata">
                        <jsp:include page="../includes/metadata.jsp" flush="true">
                            <jsp:param name="uid" value="ic" />
                        </jsp:include>
                    </div>
                </div>
            </div>
<!--  script-->
            <div>
                <div class="control-group">
                    <label class="control-label" for="ic-comments" data-i18n="public.Script"></label>
                    <div class="controls">
                        <textarea id="ic-scripts" class="input-xlarge" style="height: 8em;"></textarea>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <a href="javascript:void(0)" class="btn" data-dismiss="modal" data-i18n="public.Cancel"></a> <a
            id="ic-dialog-submit" href="javascript:void(0)" class="btn btn-primary" data-i18n="public.Create"></a>
    </div>
</div>

<!-- Script support for interface create dialog -->
<script>
    /** Function called when dialog is submitted */
    var icSubmitCallback;

    /** Dropdown with list of eventtypes**/
    var icEventTypeDropdown;

    /** eventType data **/
    var eventTypeData = [
        { text: "measurements", value: "measurements" },
        { text: "alert", value: "alert" },
        { text: "location", value: "location" }
    ];

    /** Provides external access to tabs */
    var icTabs;

    $(document).ready(function() {
        /** Create tab strip */
        icTabs = $("#ic-tabs").kendoTabStrip({
            animation : false
        }).data("kendoTabStrip");

        icEventTypeDropdown = $('#ic-event-type').kendoDropDownList({
            dataTextField: "text",
            dataValueField: "value",
            dataSource: eventTypeData,
            index: 0
        }).data("kendoDropDownList");

        /** Handle create dialog submit */
        $('#ic-dialog-submit').click(
            function(event) {
                event.preventDefault();
                if (!icValidate()) {
                    return;
                }
                var inerfaceData = {
                    "hardwareid" : $('#ic-hardware-id').val(),
                    "comments" : $('#ic-comments').val(),
                    "createddate" : '',
                    "methodname" : $('#ic-interface-name').val(),
                    "deleted" :false,
                    "definition" :{
                        "methodName": $('#ic-interface-name').val(),
                        "fieldList" :parseToFieldList(icMetadataDS.data()),
                        "eventType" : icEventTypeDropdown.value()
                    },
                    "script" : $('#ic-scripts').val()
                }
                $.postAuthJSON("${pageContext.request.contextPath}/api/device/interface", inerfaceData,
                    "${basicAuth}", "${tenant.authenticationToken}", onCreateSuccess, onCreateFail);
            });

    });

    /** Called on successful create */
    function onCreateSuccess() {
        $('#ic-dialog').modal('hide');
        if (icSubmitCallback != null) {
            icSubmitCallback();
        }
    }

    /** Handle failed call to create device */
    function onCreateFail(jqXHR, textStatus, errorThrown) {
        handleError(jqXHR, i18next("includes.deviceCreateDialog.UTCD"));
    }

    /**  pase metadataDs to FieldList **/
    function parseToFieldList(data){
        var fl = [];
        for (var i = 0, len = data.length; i < len; i++) {
            var t =  {
                "ptype": data[i].value,
                "pname": data[i].name
            };
            fl.push(t);
        }
        return fl;
    }
    /** Validate everything */
    function icValidate(){
        $.validity.setup({
            outputMode : "label"
        });
        $.validity.start();

        /** Verify hardware id was entered */
        $("#ic-hardware-id").require();

        /** Verify event type was chosen */
        $("#ic-event-type").require();

        /** Verify interface name was entered */
        $("#ic-interface-name").require();

        /** Verify that a script was entered*/
        $("#ic-scripts").require();

        var result = $.validity.end();
        return result.valid;
    }

    /** Open the dialog **/
    function icOpen(e, callback){
        var event = e || window.event;
        event.stopPropagation();

        // Reset form and fielList.
        $('#ic-general-form')[0].reset();
        icMetadataDS.data(new Array());

        // Select first tab.
        icTabs.select(0);

        // Function called on submit.
        icSubmitCallback = callback;
        // Clear old validation errors and show dialog.
        $.validity.clear();
        $('#ic-dialog').modal('show');
    }
</script>