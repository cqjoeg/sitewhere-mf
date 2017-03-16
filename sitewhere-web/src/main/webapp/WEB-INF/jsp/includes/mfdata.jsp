

<div class="sw-sublist-header">
	<div style="width: 120px;" data-i18n="public.Name"></div>
	<div style="width: 120px" data-i18n="public.From"></div>
	<div style="width: 120px" data-i18n="public.To"></div>
</div>
<table id="sw-metadata-<%=request.getParameter("mfid")%>" class="sw-sublist-list k-widget k-listview">

</table>

<%
	if (request.getParameter("mdReadOnly") == null) {
%>
<div class="sw-sublist-add-new">
	<div class="sw-sublist-footer">
		<div style="width: 120px; margin-left: 3px;" data-i18n="public.Name"></div>
		<div style="width: 120px" data-i18n="public.From"></div>
		<div style="width: 120px" data-i18n="public.To"></div>
	</div>
	<input type="text" id="sw-mfdata-name-<%=request.getParameter("mfid")%>" style="width: 120px; margin-bottom: 0px; margin-right: 10px;" title="MF name">
	<input type="text" id="sw-mfdata-from-<%=request.getParameter("mfid")%>" style="width: 120px; margin-bottom: 0px; margin-right: 10px;" title="MF from">
	<input type="text" id="sw-mfdata-to-<%=request.getParameter("mfid")%>" style="width: 120px; margin-bottom: 0px; margin-right: 10px;" title="MF to">

	<a class="btn" href="javascript:void(0)" onclick="onAddMFdata_<%=request.getParameter("mfid")%>()">
		<i class="fa fa-plus sw-button-icon"></i> <span data-i18n="public.Add">Add</span>
	</a>
	<div id="sw-mfdata-error-<%=request.getParameter("mfid")%>" style="color: #f00; display: none;"></div>
</div>
<%
	}
%>

<script>
	var <%= request.getParameter("mfid")%>_MFData = {
		"type":"measurements",
		"keys":[
		]
	};

	/**  **/
	function onAddMFdata_<%= request.getParameter("mfid")%>() {
		// Reset error.
		$("#sw-mfdata-error-<%= request.getParameter("mfid")%>").hide();
		var error = "";
		var name = $("#sw-mfdata-name-<%= request.getParameter("mfid")%>").val();
		var from = $("#sw-mfdata-from-<%= request.getParameter("mfid")%>").val();
		var to = $("#sw-mfdata-to-<%= request.getParameter("mfid")%>").val();
		pushMfData_<%= request.getParameter("mfid")%>(name, parseFloat(from) , parseFloat(to));
		// Check for empty.
		if (name.length == 0) {
			error = i18next("includes.metadata.Nameisrequired");
		}
		var regex = /^[\w-]+$/;
		if (!regex.test(name)) {
			error = i18next("includes.metadata.ICIN");
		}

		// Check for already used.

		if (error.length > 0) {
			$("#sw-mfdata-error-<%= request.getParameter("mfid")%>").html(error);
			$("#sw-mfdata-error-<%= request.getParameter("mfid")%>").toggle();
		} else {
			initMFDataView_<%= request.getParameter("mfid")%>();
//			$("#sw-metadata-mf").append(glueTemlateItem(name, from, to));
//			$("#sw-mfdata-name-mf").val("");
			$("#sw-mfdata-from-<%= request.getParameter("mfid")%>").val("");
			$("#sw-mfdata-to-<%= request.getParameter("mfid")%>").val("");
		}
	}

	/** Deletes a metadata entry by name */
	function onDeleteMetadata_<%= request.getParameter("mfid")%>(name, from, to) {
		//MFData.keys.
		for (var index = 0; index < <%= request.getParameter("mfid")%>_MFData.keys.length ; index ++){
			var exist = <%= request.getParameter("mfid")%>_MFData .keys[index];
			if (exist.key == name) {
				for (var j = 0; j < exist.alertRanges.length ;j++){
					var e = exist.alertRanges[j];
					if (e.from == from && e.to == to){
						if(exist.alertRanges.length == 1){
							<%= request.getParameter("mfid")%>_MFData.keys.splice(index, 1);
						} else {
							<%= request.getParameter("mfid")%>_MFData.keys[index].alertRanges.splice(j, 1);
						}
					}
				}
			}
		}
		initMFDataView_<%= request.getParameter("mfid")%>();
	}

	/**
	 * 返回 item
	 */
	function glueTemlateItem_<%= request.getParameter("mfid")%>(name, from, to, first){
		var template =
				'<tr class="sw-list-entry" role="option" aria-selected="false">' ;
		if(first){
			template += '<td style="width: 120px">'+ name +'</td>' ;
		} else {
			template += '<td style="width: 120px"></td>' ;
		}
		template+=
				'<td style="width: 120px">'+ from +'</td>'+
				'<td style="width: 120px">'+ to +'</td>'+
				'<td>'+
				'<div style="text-align: right;">'+
				'<i class="fa fa-remove sw-action-glyph sw-delete-glyph" onclick="onDeleteMetadata_<%= request.getParameter("mfid")%>(\''+ name +'\','+ from +','+ to +')"></i>'+
				'</div>'+
				'</td>'+
				'</tr>';
		return template;
	}

	/**
	 * push to MFData
	 */
	function  pushMfData_<%= request.getParameter("mfid")%>(name, from, to){
		var newKey = true;
		for (var index = 0; index < <%= request.getParameter("mfid")%>_MFData.keys.length ;index ++){
			var existing = <%= request.getParameter("mfid")%>_MFData.keys[index];
			if(name == existing.key){
				newKey = false;
				var tmpAlertArrange = {
					"from" : from,
					"to" : to
				}
				<%= request.getParameter("mfid")%>_MFData.keys[index].alertRanges.push(tmpAlertArrange);
			}
		}
		// 已经存在keys中
		if (newKey) {
			var key =  {
				"key": name,
				"alertRanges": [
					{
						"from": from,
						"to": to
					}
				]
			};
			<%= request.getParameter("mfid")%>_MFData.keys.push(key);
		}
	}

	/**
	 * init mfdata view
	 */
	function initMFDataView_<%= request.getParameter("mfid")%>(){
		var mfview = '';
		for (var index = 0; index < <%= request.getParameter("mfid")%>_MFData.keys.length ; index ++){
			var exist = <%= request.getParameter("mfid")%>_MFData.keys[index];

			if (<%= request.getParameter("mfid")%>_MFData.keys[index].alertRanges.length == 1){
				mfview += glueTemlateItem_<%= request.getParameter("mfid")%>(exist.key, exist.alertRanges[0].from, exist.alertRanges[0].to, true);
			} else {
				for (var j = 0; j < exist.alertRanges.length ;j++){
					var e = exist.alertRanges[j];
					if(j == 0){
						mfview += glueTemlateItem_<%= request.getParameter("mfid")%>(exist.key, e.from, e.to, true);
					} else {
						mfview += glueTemlateItem_<%= request.getParameter("mfid")%>(exist.key, e.from, e.to, false);
					}
				}
			}

		}
		$("#sw-metadata-<%= request.getParameter("mfid")%>").html(mfview);
	}

</script>
