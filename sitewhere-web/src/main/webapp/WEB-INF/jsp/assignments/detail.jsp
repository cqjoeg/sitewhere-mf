<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="sitewhere_title" value="View Assignment" />
<c:set var="sitewhere_section" value="sites" />
<%@ include file="../includes/top.inc"%>

<style>
.event-pager {
	margin-top: 10px;
}

table#invocations tr td {
	vertical-align: top;
}
</style>

<!-- Title Bar -->
<div class="sw-title-bar content k-header" style="margin-bottom: -1px;">
	<h1 class="ellipsis" data-i18n="assignments.detail.title"></h1>
	<div class="sw-title-bar-right">
		<a id="btn-emulator" class="btn"
			href="${pageContext.request.contextPath}/admin/${tenant.id}/assignments/${assignment.token}/emulator.html">
			<i class="fa fa-bolt sw-button-icon"></i> <span
			data-i18n="assignments.detail.EmulateAssignment">Emulate
				Assignment</span>
		</a> <a id="btn-edit-assignment" class="btn" href="javascript:void(0)">
			<i class="fa fa-edit sw-button-icon"></i> <span
			data-i18n="public.EditAssignment">Edit Assignment</span>
		</a>
	</div>
</div>

<!-- Detail panel for selected assignment -->
<div id="assignment-details" style="line-height: normal;"></div>

<!-- Tab panel -->
<div id="tabs">
	<ul>
		<li class="k-state-active">&nbsp;<font data-i18n="public.Locations"></font></li>
		<li>&nbsp;<font data-i18n="public.Measurements"></font></li>
		<li>&nbsp;<font data-i18n="public.Alerts"></font></li>
		<li>&nbsp;<font data-i18n="public.CommandInvocations"></font></li>
		<li>&nbsp;<font data-i18n="public.MFAlerts"></font></li>
		<%--<li>&nbsp;<font data-i18n="public.Interface"></font></li>--%>
		<li>&nbsp;<font data-i18n="public.Interface"></font></li>
	</ul>
	<%-- location--%>
	<div>
		<div class="k-header sw-button-bar">
			<div class="sw-button-bar-title" data-i18n="public.DeviceLocations"></div>
			<div>
				<a id="btn-filter-locations" class="btn" href="javascript:void(0)">
					<i class="fa fa-filter sw-button-icon"></i> <span
					data-i18n="public.FilterResults">Filter Results</span>
				</a> <a id="btn-refresh-locations" class="btn" href="javascript:void(0)">
					<i class="fa fa-refresh sw-button-icon"></i> <span
					data-i18n="public.Refresh">Refresh</span>
				</a>
			</div>
		</div>
		<table id="locations">
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
		<div id="locations-pager" class="k-pager-wrap event-pager"></div>
	</div>
	<%-- measurements--%>
	<div>
		<div class="k-header sw-button-bar">
			<div class="sw-button-bar-title"
				data-i18n="public.DeviceMeasurements"></div>
			<div>
				<a id="btn-filter-measurements" class="btn"
					href="javascript:void(0)"> <i
					class="fa fa-search sw-button-icon"></i> <span
					data-i18n="public.FilterResults">Filter Results</span></a> <a
					id="btn-refresh-measurements" class="btn" href="javascript:void(0)">
					<i class="fa fa-refresh sw-button-icon"></i> <span
					data-i18n="public.Refresh">Refresh</span>
				</a>
			</div>
		</div>
		<table id="measurements">
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
		<div id="measurements-pager" class="k-pager-wrap event-pager"></div>
	</div>
	<%--device alerts--%>
	<div>
		<div class="k-header sw-button-bar">
			<div class="sw-button-bar-title" data-i18n="public.DeviceAlerts"></div>
			<div>
				<a id="btn-filter-alerts" class="btn" href="javascript:void(0)">
					<i class="fa fa-search sw-button-icon"></i> <span
					data-i18n="public.FilterResults">Filter Results</span>
				</a> <a id="btn-refresh-alerts" class="btn" href="javascript:void(0)">
					<i class="fa fa-refresh sw-button-icon"></i> <span
					data-i18n="public.Refresh">Refresh</span>
				</a>
			</div>
		</div>
		<table id="alerts">
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
		<div id="alerts-pager" class="k-pager-wrap event-pager"></div>
	</div>
	<%-- Device Command Invocations--%>
	<div>
		<div class="k-header sw-button-bar">
			<div class="sw-button-bar-title"
				data-i18n="assignments.detail.DeviceCommandInvocations"></div>
			<div>
				<a id="btn-filter-invocations" class="btn" href="javascript:void(0)">
					<i class="fa fa-search sw-button-icon"></i> <span
					data-i18n="public.FilterResults">Filter Results</span>
				</a> <a id="btn-refresh-invocations" class="btn"
					href="javascript:void(0)"> <i
					class="fa fa-refresh sw-button-icon"></i> <span
					data-i18n="public.Refresh">Refresh</span>
				</a> <a id="btn-create-invocation" class="btn" href="javascript:void(0)">
					<i class="fa fa-bolt sw-button-icon"></i> <span
					data-i18n="assignments.detail.InvokeCommand">Invoke Command</span>
				</a>
			</div>
		</div>
		<table id="invocations">
			<colgroup>
				<col style="width: 30%;" />
				<col style="width: 33%;" />
				<col style="width: 15%;" />
				<col style="width: 15%;" />
				<col style="width: 7%;" />
			</colgroup>
			<thead>
				<tr>
					<th data-i18n="public.Command"></th>
					<th data-i18n="public.Source"></th>
					<th data-i18n="assignments.detail.Target"></th>
					<th data-i18n="public.EventDate"></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="5"></td>
				</tr>
			</tbody>
		</table>
		<div id="invocations-pager" class="k-pager-wrap event-pager"></div>
	</div>
	<%-- my alerts data --%>
	<div>
		<div class="k-header sw-button-bar">
			<div class="sw-button-bar-title" data-i18n="public.DeviceAlerts"></div>
			<div>
					<a id="btn-refresh-mf-alerts" class="btn" href="javascript:void(0)">
					<i class="fa fa-refresh sw-button-icon"></i> <span data-i18n="public.Refresh">Refresh</span>
			</a>
			</div>
		</div>
		<table id="mf-alerts">
			<colgroup>
				<col style="width: 10%;" />
				<col style="width: 20%;" />
				<col style="width: 10%;" />
				<col style="width: 20%;" />
			</colgroup>
			<thead>
			<tr>
				<th data-i18n="public.Type"></th>
				<th data-i18n="public.Range"></th>
				<th data-i18n="public.Value"></th>
				<th data-i18n="public.EventDate"></th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<td colspan="5"></td>
			</tr>
			</tbody>
		</table>
		<div id="mf-alerts-pager" class="k-pager-wrap event-pager"></div>
	</div>
	<%--device interface  (no-edit)--%>
	<div>
		<div class="k-header sw-button-bar">
			<div class="sw-button-bar-title"
				 data-i18n="assignments.detail.DeviceCommandInvocations"></div>
			<div>
				<a id="btn-refresh-device-interface" class="btn"
				   href="javascript:void(0)"> <i
						class="fa fa-refresh sw-button-icon"></i> <span
						data-i18n="public.Refresh">Refresh</span>
				</a>

				<a id="btn-create-devcie-interface" class="btn" href="javascript:void(0)">
					<i class="fa fa-bolt sw-button-icon"></i> <span
						data-i18n="devices.interface.create">Create Interface</span>
				</a>
			</div>
		</div>
		<table id="device-interface">
			<colgroup>
				<col style="width: 50%;" />
				<col style="width: 50%;" />
			</colgroup>
			<thead>
			<tr>
				<th data-i18n="public.OperatioName"></th>
				<th data-i18n="public.Invocation"></th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<td colspan="2"></td>
			</tr>
			</tbody>
		</table>
		<div id="device-interface-pager" class="k-pager-wrap event-pager"></div>
	</div>


</div>

<%@ include file="../includes/assignmentUpdateDialog.inc"%>
<%@ include file="../includes/commandInvokeDialog.inc"%>
<%@ include file="../includes/invocationViewDialog.inc"%>
<%@ include file="../includes/templateAssignmentDetailHeader.inc"%>
<%@ include file="../includes/templateAssignmentEntry.inc"%>
<%@ include file="../includes/templateInvocationEntry.inc"%>
<%@ include file="../includes/templateInvocationSummaryEntry.inc"%>
<%@ include file="../includes/templateResponseSummaryEntry.inc"%>
<%@ include file="../includes/templateLocationEntry.inc"%>
<%@ include file="../includes/templateMeasurementsEntry.inc"%>
<%@ include file="../includes/templateAlertEntry.inc"%>
<%@ include file="../includes/templateDeviceFieldAlertsEntry.inc"%>
<%@ include file="../includes/commonFunctions.inc"%>


<%@ include file="../includes/templateDeviceInterfaceEntryNOEdit.inc"%>
<%@ include file="../includes/interfaceOperateDialog.inc"%>
<%@ include file="../includes/interfaceCreateDialog.inc"%>
<script>
	/** Set sitewhere_title */
	sitewhere_i18next.sitewhere_title = "assignments.detail.title";

	/** Assignment token */
	var token = '<c:out value="${assignment.token}"/>';

	/** Device specification token */
	var specificationToken = '<c:out value="${assignment.device.specificationToken}"/>';

	/** Datasource for invocations */
	var invocationsDS;

	/** Datasource for locations */
	var locationsDS;

	/** Datasource for measurements */
	var measurementsDS;

	/** Datasource for alerts */
	var alertsDS;

	/** Datasource for field alerts**/
	var fieldAlertsDS;

	/** Datasource for device interface**/
	var deviceInterfaceDS;

	/** Reference to tab panel */
	var tabs;

	/** Size of pages from server */
	var pageSize = 100;

	/** Height of event grids */
	var gridHeight = 350;

	/** Called when 'edit assignment' is clicked */
	function onEditAssignment(e, token) {
		var event = e || window.event;
		event.stopPropagation();
		auOpen(token, onEditAssignmentComplete);
	}

	/** Called after successful edit assignment */
	function onEditAssignmentComplete() {
		// Handle reload.
	}

	/** Called when 'release assignment' is clicked */
	function onReleaseAssignment(e, token) {
		var event = e || window.event;
		event.stopPropagation();
		swReleaseAssignment(token, "${basicAuth}",
				"${tenant.authenticationToken}", onReleaseAssignmentComplete);
	}

	/** Called after successful release assignment */
	function onReleaseAssignmentComplete() {
		loadAssignment();
	}

	/** Called when 'missing assignment' is clicked */
	function onMissingAssignment(e, token) {
		var event = e || window.event;
		event.stopPropagation();
		swAssignmentMissing(token, "${basicAuth}",
				"${tenant.authenticationToken}", onMissingAssignmentComplete);
	}

	/** Called after successful missing assignment */
	function onMissingAssignmentComplete() {
		loadAssignment();
	}

	/** Called to view an invocation */
	function onViewInvocation(id) {
		ivOpen(id);
	}

	/** Called when a interface    has been successfully created */
	function onInterfaceCreated() {
		deviceInterfaceDS.read();
	}

	$(document).ready(function() {

/** Create AJAX datasource for locations list */
						locationsDS = new kendo.data.DataSource(
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
										parse : parseEventResults,
									},
									serverPaging : true,
									serverSorting : true,
									pageSize : pageSize,
								});
						/** Create the location list */
						$("#locations").kendoGrid(
								{
									dataSource : locationsDS,
									rowTemplate : kendo.template($(
											"#tpl-location-entry").html()),
									scrollable : true,
									height : gridHeight,
								});

						$("#locations-pager").kendoPager({
							dataSource : locationsDS
						});

						$("#btn-refresh-locations").click(function() {
							locationsDS.read();
						});
						$('#btn-filter-locations').attr('disabled', true);

/** Create AJAX datasource for measurements list */
						measurementsDS = new kendo.data.DataSource(
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
										parse : parseEventResults,
									},
									serverPaging : true,
									serverSorting : true,
									pageSize : pageSize,
								});

						/** Create the measurements list */
						$("#measurements").kendoGrid(
								{
									dataSource : measurementsDS,
									rowTemplate : kendo.template($(
											"#tpl-measurements-entry").html()),
									scrollable : true,
									height : gridHeight,
								});

						$("#measurements-pager").kendoPager({
							dataSource : measurementsDS
						});

						$("#btn-refresh-measurements").click(function() {
							measurementsDS.read();
						});
						$('#btn-filter-measurements').attr('disabled', true);


/** Create AJAX datasource for alerts list */
						alertsDS = new kendo.data.DataSource(
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
										parse : parseEventResults,
									},
									serverPaging : true,
									serverSorting : true,
									pageSize : pageSize,
								});

						/** Create the alerts list */
						$("#alerts").kendoGrid(
								{
									dataSource : alertsDS,
									rowTemplate : kendo.template($(
											"#tpl-alert-entry").html()),
									scrollable : true,
									height : gridHeight,
								});

						$("#alerts-pager").kendoPager({
							dataSource : alertsDS
						});

						$("#btn-refresh-alerts").click(function() {
							alertsDS.read();
						});
						$('#btn-filter-alerts').attr('disabled', true);

/** Create AJAX datasource for invocations list */
						invocationsDS = new kendo.data.DataSource(
								{
									transport : {
										read : {
											url : "${pageContext.request.contextPath}/api/assignments/"
													+ token + "/invocations",
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
										parse : parseEventResults,
									},
									serverPaging : true,
									serverSorting : true,
									pageSize : pageSize,
								});

						/** Create the invocations list */
						$("#invocations").kendoGrid(
								{
									dataSource : invocationsDS,
									rowTemplate : kendo.template($(
											"#tpl-invocation-entry").html()),
									scrollable : true,
									height : gridHeight,
								});

						$("#btn-refresh-invocations").click(function() {
							invocationsDS.read();
						});
						$('#btn-filter-invocations').attr('disabled', true);

						$("#btn-create-invocation").click(
								function() {
									ciOpen(token, specificationToken,
											onInvokeCommandSuccess);
								});

						$("#invocations-pager").kendoPager({
							dataSource : invocationsDS
						});

/** crate Ajax datasoure for field device alerts**/
		fieldAlertsDS = new kendo.data.DataSource(
				{
					transport : {
						read : {
							url : "${pageContext.request.contextPath}/api/device/alert/data/" + token + "/alerts" ,
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
						parse : parseEventResults,
					},
					serverPaging : true,
					serverSorting : true,
					pageSize : pageSize,
				}
		)

		/** Create the mf-alerts list */
		$("#mf-alerts").kendoGrid(
				{
					dataSource : fieldAlertsDS,
					rowTemplate : kendo.template($(
							"#tpl-field-alerts-entry").html()),
					scrollable : true,
					height : gridHeight,
				});
		$("#mf-alerts-pager").kendoPager({
			dataSource : fieldAlertsDS
		});

		$("#btn-refresh-mf-alerts").click(function() {
			fieldAlertsDS.read();
		});
		$('#btn-filter-mf-alerts').attr('disabled', true);


		/** create ajax datasource for device interface  **/
		deviceInterfaceDS = new kendo.data.DataSource({
					transport : {
						read : {
							url : "${pageContext.request.contextPath}/api/device/interface/"+token + "/token",
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
						parse : function(response){
							return response;
						}
					},
					serverPaging : true,
					serverSorting : true,
					pageSize : 15,
				}
		);
		/** Create the device interface list */
		$("#device-interface").kendoGrid({
					dataSource : deviceInterfaceDS,
					rowTemplate : kendo.template($("#tpl-devcie-interface-entry-no-edit").html()),
					scrollable : true,
					height : gridHeight
				});

		$("#device-interface-pager").kendoPager({
			dataSource : deviceInterfaceDS
		});

		/** device interface refresh**/
		$("#btn-refresh-device-interface").click(function() {
			deviceInterfaceDS.read();
		});



						$("#btn-edit-assignment").click(function() {
							auOpen(token, onAssignmentEditSuccess);
						});

						/** Handle interface create dialog */
						$("#btn-create-devcie-interface").click(function(event) {
							icOpen(event, onInterfaceCreated);
						});
						/** Create the tab strip */
						tabs = $("#tabs").kendoTabStrip({
							animation : false,
							activate : onActivate
						}).data("kendoTabStrip");

						loadAssignment();
					});

	/** Called to interface operation**/
	function onViewInterfaceOperate(hardwareid, methodname){
		console.log("use onViewInterfaceOperate");
		dioOpen(hardwareid, methodname);
	}



	/** Force grid refresh on first tab activate (KendoUI bug) */
	function onActivate(e) {
		var tabName = e.item.textContent;
		if (!e.item.swInitialized) {
			if (tabName == "Locations") {
				locationsDS.read();
				e.item.swInitialized = true;
			} else if (tabName == "Measurements") {
				measurementsDS.read();
				e.item.swInitialized = true;
			} else if (tabName == "Alerts") {
				alertsDS.read();
				e.item.swInitialized = true;
			} else if (tabName == "Command Invocations") {
				invocationsDS.read();
				e.item.swInitialized = true;
			} else if (tabName == "MFAlerts"){
				fieldAlertsDS.read();
				e.item.swInitialized = true;
			} else if(tabName == "Interface"){
				deviceInterfaceDS.read();
				e.item.swInitialized = true;
			}
		}
	};

	/** Loads information for the selected assignment */
	function loadAssignment() {
		$.getAuthJSON("${pageContext.request.contextPath}/api/assignments/"
				+ token, "${basicAuth}", "${tenant.authenticationToken}",
				loadGetSuccess, loadGetFailed);
	}

	/** Called on successful assignment load request */
	function loadGetSuccess(data, status, jqXHR) {
		var template = kendo
				.template($("#tpl-assignment-detail-header").html());
		parseAssignmentData(data);
		data.inDetailView = true;
		$('#assignment-details').html(template(data));
	}

	/** Handle error on getting assignment data */
	function loadGetFailed(jqXHR, textStatus, errorThrown) {
		handleError(jqXHR, "Unable to load assignment data.");
	}

	/** Parses event response records to format dates */
	function parseEventResults(response) {
		$.each(response.results, function(index, item) {
			parseEventData(item);
		});
		return response;
	}

	/** Called after successful edit of assignment */
	function onAssignmentEditSuccess() {
		loadAssignment();
	}

	/** Called after successful edit of assignment */
	function onInvokeCommandSuccess() {
		invocationsDS.read();
	}
</script>

<%@ include file="../includes/bottom.inc"%>