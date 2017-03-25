<!-- Dialog for edit a interface -->

<div id="ie-dialog" class="modal hide">
    <div class="modal-header k-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h3 data-i18n="includes.EditInterface"></h3>
    </div>
    <div class="modal-body">
        <div id="ie-tabs">
            <ul>
                <li class="k-state-active" data-i18n="includes.InterfaceDetails"></li>
                <li  data-i18n="public.Parameter"></li>
                <li  data-i18n="public.Script"></li>
            </ul>
            <!--  Interface Details -->
            <div>
                <form id="ie-general-form" class="form-horizontal" style="padding-top: 20px;">
                    <div class="control-group">
                        <label class="control-label" for="ie-hardware-id" data-i18n="public.HardwareId"> </label>
                        <div class="controls">
                            <input type="text" id="ie-hardware-id" readonly="readonly" title="#= i18next("public.HardwareId") #" class="input-xlarge">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="ie-interface-name" data-i18n="includes.InterfaceName"> </label>
                        <div class="controls">
                            <input type="text" id="ie-interface-name" readonly="readonly" title="#= i18next("includes.InterfaceName") #" class="input-xlarge">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="ie-event-type" data-i18n="public.Site"></label>
                        <div class="controls">
                            <select id="ie-event-type"></select>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="ie-comments" data-i18n="includes.Comments"></label>
                        <div class="controls">
                            <textarea id="ie-comments" class="input-xlarge" style="height: 8em;"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <!--  field list-->
            <div>
                <div>
                    <div id="ie-metadata">
                        <jsp:include page="../includes/metadata.jsp" flush="true">
                            <jsp:param name="uid" value="ie" />
                        </jsp:include>
                    </div>
                </div>
            </div>
            <!--  script-->
            <div>
                <div class="control-group">
                    <label class="control-label" for="ie-comments" data-i18n="public.Script"></label>
                    <div class="controls">
                        <textarea id="ie-scripts" class="input-xlarge" style="height: 8em;"></textarea>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <a href="javascript:void(0)" class="btn" data-dismiss="modal" data-i18n="public.Cancel"></a> <a
            id="ie-dialog-submit" href="javascript:void(0)" class="btn btn-primary" data-i18n="public.Edit"></a>
    </div>
</div>


<!-- Script support for interface edit dialog -->
<script>

    /** Function called when dialog is submitted */
    var ieSubmitCallback;

    /** Dropdown with list of eventtypes**/
    var ieEventTypeDropdown;

    /** eventType data **/
    var eventTypeData = [
        { text: "measurements", value: "measurements" },
        { text: "alert", value: "alert" },
        { text: "location", value: "location" }
    ];

    /** Provides external access to tabs */
    var ieTabs;

    $(document).ready(function() {
        /** Create tab strip */
        ieTabs = $("#ie-tabs").kendoTabStrip({
            animation : false
        }).data("kendoTabStrip");


        ieEventTypeDropdown = $('#ie-event-type').kendoDropDownList({
            dataTextField: "text",
            dataValueField: "value",
            dataSource: eventTypeData,
            index: 0
        }).data("kendoDropDownList");


        /** Handle create dialog submit */
        $('#ie-dialog-submit').click(function(event) {
            event.preventDefault();
            if (!ieValidate()) {
                return;
            }
            var inerfaceData = {
                "hardwareid" : $('#ie-hardware-id').val(),
                "comments" : $('#ie-comments').val(),
                "createddate" : '',
                "methodname" : $('#ie-interface-name').val(),
                "deleted" :false,
                "definition" :{
                    "methodName": $('#ie-interface-name').val(),
                    "fieldList" :parseToFieldList(ieMetadataDS.data()),
                    "eventType" : ieEventTypeDropdown.value()
                },
                "script" : $('#ie-scripts').val()
            }
            $.putAuthJSON("${pageContext.request.contextPath}/api/device/interface/" + hardwareId + "/" + $('#ie-interface-name').val() , inerfaceData,
                "${basicAuth}", "${tenant.authenticationToken}", onEditSuccess, onEditFail);
        });

    });

    /** Called on successful edit */
    function onEditSuccess() {
        $('#ie-dialog').modal('hide');
        if (icSubmitCallback != null) {
            icSubmitCallback();
        }
    }

    /** Handle failed call to create device */
    function onEditFail(jqXHR, textStatus, errorThrown) {
        handleError(jqXHR, i18next("includes.deviceCreateDialog.UTCD"));
    }

    /** Validate everything */
    function ieValidate(){
        $.validity.setup({
            outputMode : "label"
        });
        $.validity.start();

        /** Verify hardware id was entered */
        $("#ie-hardware-id").require();

        /** Verify event type was chosen */
        $("#ie-event-type").require();

        /** Verify interface name was entered */
        $("#ie-interface-name").require();

        /** Verify that a script was entered*/
        $("#ie-scripts").require();

        var result = $.validity.end();
        return result.valid;
    }

    /** Open the dialog **/
    function ieOpen(hardwareId, methodName, callback){
//        var event = e || window.event;
//        event.stopPropagation();
        $.getAuthJSON("${pageContext.request.contextPath}/api/device/interface/" + hardwareId + "/" + methodName,
            "${basicAuth}",
            "${tenant.authenticationToken}",
            loadInterfaceSuccess,
            loadInterfaceFailed);

        // Function called on submit.
        ieSubmitCallback = callback;
    }


    /** Called on successful device inteface load */
    function loadInterfaceSuccess(data, status, jqXHR) {
        // Reset form and fielList.
        $('#ie-general-form')[0].reset();
        $('#ie-hardware-id').val(data.hardwareid);
        $('#ie-comments').val(data.comments);
        $('#ie-interface-name').val(data.methodname);
        $('#ie-scripts').val(data.script);

        if(data.hasOwnProperty('definition')){
            if(data.definition.hasOwnProperty('fieldList')){
                ieMetadataDS.data(ielookupAsMetadata(data.definition.fieldList));
            } else
                ieMetadataDS.data(new Array());
        } else  {
            ieMetadataDS.data(new Array());
        }

        // Select first tab.
        ieTabs.select(0);

        // Clear old validation errors and show dialog.
        $.validity.clear();
        $('#ie-dialog').modal('show');
    }

    /** Handle error on getting device inteface*/
    function loadInterfaceFailed(jqXHR, textStatus, errorThrown) {
        handleError(jqXHR, "Unable to load device data.");
    }

    function ielookupAsMetadata(lookup) {
        var metadata = [];
        var key;
        for(var i = 0, len = lookup.length; i<len; i++){
            metadata.push({
                "name" : lookup[i].pname,
                "value" : lookup[i].ptype
            });
        }
        return metadata;
    }

</script>