<!-- Template for device interface row -->
<script type="text/x-kendo-tmpl" id="tpl-devcie-interface-entry-no-edit">
	<tr class="sw-list-entry">
		<td class="ellipsis">#:methodname# </td>
		<td class="ellipsis" style="max-width: 90px;" >
			<div>
				<i class="fa fa-chevron-right fa-white sw-action-glyph sw-edit-glyph"
				title="#= i18next("includes.templateDeviceInterfaceEntry.ViewInterfaceOperate") #"
				style="vertical-align: middle;"
					onclick="onViewInterfaceOperate('#:hardwareid#','#:methodname#')"></i>
			</div>
		</td>
	</tr>
</script>
