<!-- Template for device field alerts row -->
<script type="text/x-kendo-tmpl" id="tpl-field-alerts-entry">
	<tr class="sw-list-entry row-gradient">

		<td class="ellipsis">#= type # </td>
		<td class="ellipsis" >#= range # </td>
		<td class="ellipsis" >#= value # </td>
		<td class="ellipsis" >#= formattedDate(formattedTimestamp2Date(createddate)) #</td>

	</tr>
</script>