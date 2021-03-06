<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="sitewhere_title" value="Manage Device Groups" />
<c:set var="sitewhere_section" value="devices" />
<%@ include file="../includes/top.inc"%>

<style>
.sw-device-group-list {
	border: 0px;
}
</style>

<!-- Title Bar -->
<div class="sw-title-bar content k-header">
	<h1 class="ellipsis" data-i18n="groups.list.title"></h1>
	<div class="sw-title-bar-right">
		<a id="btn-filter-results" class="btn" href="javascript:void(0)"> <i
			class="fa fa-filter sw-button-icon"></i> <span data-i18n="public.FilterResults">Filter
				Results</span>
		</a> <a id="btn-add-device-group" class="btn" href="javascript:void(0)"> <i
			class="fa fa-plus sw-button-icon"></i> <span data-i18n="groups.list.ANDG">Add New Device
				Group</span>
		</a>
	</div>
</div>
<div id="groups" class="sw-device-group-list"></div>
<div id="pager" class="k-pager-wrap"></div>

<form id="view-device-group-detail" method="get"></form>

<%@ include file="deviceGroupCreateDialog.inc"%>
<%@ include file="../includes/templateRoleEntry.inc"%>
<%@ include file="../includes/templateDeviceGroupEntry.inc"%>
<%@ include file="../includes/commonFunctions.inc"%>

<script>
	/** Set sitewhere_title */
	sitewhere_i18next.sitewhere_title = "groups.list.title";

	/** Reference for device group list datasource */
	var deviceGroupsDS;

	/** Called when 'open' button on the group entry is pressed */
	function onDeviceGroupOpenClicked(e, token) {
		var event = e || window.event;
		event.stopPropagation();
		$("#view-device-group-detail").attr("action",
			"${pageContext.request.contextPath}/admin/${tenant.id}/groups/" + token + ".html");
		$('#view-device-group-detail').submit();
	}

	/** Called when 'edit' button on the group entry is pressed */
	function onDeviceGroupEditClicked(e, token) {
		var event = e || window.event;
		event.stopPropagation();
		dguOpen(token, onDeviceGroupEditComplete)
	}

	/** Called when 'delete' button on the group entry is pressed */
	function onDeviceGroupDeleteClicked(e, token) {
		var event = e || window.event;
		event.stopPropagation();
		swDeviceGroupDelete(token, '${tenant.authenticationToken}', onDeviceGroupDeleteComplete);
	}

	/** Called after a device group is added */
	function onDeviceGroupAdded() {
		deviceGroupsDS.read();
	}

	/** Called after a device group is edited */
	function onDeviceGroupEditComplete() {
		deviceGroupsDS.read();
	}

	/** Called after a device group is deleted */
	function onDeviceGroupDeleteComplete() {
		deviceGroupsDS.read();
	}

	$(document).ready(function() {
		/** Create AJAX datasource for device groups list */
		deviceGroupsDS = new kendo.data.DataSource({
			transport : {
				read : {
					url : "${pageContext.request.contextPath}/api/devicegroups",
					beforeSend : function(req) {
						req.setRequestHeader('Authorization', "Basic ${basicAuth}");
						req.setRequestHeader('X-SiteWhere-Tenant', "${tenant.authenticationToken}");
					},
					dataType : "json",
				}
			},
			schema : {
				data : "results",
				total : "numResults",
				parse : function(response) {
					$.each(response.results, function(index, item) {
						parseDeviceGroupData(item);
					});
					return response;
				}
			},
			serverPaging : true,
			serverSorting : true,
			pageSize : 15,
		});

		/** Create the list of devices */
		$("#groups").kendoListView({
			dataSource : deviceGroupsDS,
			template : kendo.template($("#tpl-device-group-entry").html()),
		});

		/** Pager for device list */
		$("#pager").kendoPager({
			dataSource : deviceGroupsDS
		});

		$("#btn-add-device-group").click(function(event) {
			dgcOpen(event, onDeviceGroupAdded)
		});
	});
</script>

<%@ include file="../includes/bottom.inc"%>