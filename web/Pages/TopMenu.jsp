<!-- Menu for Users -->
<div id="layersMenu" class="layersMenu">
    <table name="menuTable" border="0" align="center" cellpadding="2" >
        <tr>
			<!--
		<c:choose>
			<c:when test='${netcdf}'>
				<td id="hideCalendarButtonParent" valign="center" >
					<button type="button" class="buttonStyle"   id="hideCalendar" onclick="hideCalendarFunc();"  >Hide Calendar</button>
				</td>
			</c:when>
		</c:choose>
			-->
			<td valign="middle">
				<!-- Link to download kml files-->
				<span id="kmlLinkParent" onmouseover="hoverInstructions('mainKmlParentHover', '1')" 
					  onmouseout="hoverInstructions('mainKmlParentHover', '2')"  > 
					<%@include file="Options/KmlLink.jsp" %>
				</span>
			</td>
			<td valign="center" border="1">
				<!-- Transparency -->
				<span id="transParent" onmouseover="hoverInstructions('transParentHover', '1')" 
					  onmouseout="hoverInstructions('transParentHover', '2')"  ><%@include file="Options/Transparency.jsp" %>
				</span>
			</td>
		<c:choose>
			<c:when test='${netcdf}'>
				<td valign="middle">
					<span id="elevationParent" onmouseover="hoverInstructions('elevationParentHover', '1')" 
						  onmouseout="hoverInstructions('elevationParentHover', '2')"  ><%@include file="Options/Elevation.jsp" %>
					</span>
				</td>
				<td valign="middle" id="palettesMenuParent" onmouseover="hoverInstructions('palettesHover', '1')" onmouseout="hoverInstructions('palettesHover', '2')">
					<button type="button" class="buttonStyle" onclick="showPalettes()" id="dynamicFont_color"  >
						<fmt:message key="ncwms.pal" />
					</button>
				</td>
				<td valign="middle" onmouseover="hoverInstructions('transectParentHover', '1')" onmouseout="hoverInstructions('transectParentHover', '2')">
					<span id="transectParent">
						<button type="button" name="type" value="line" id="lineToggle" class="buttonStyle" 
								onclick="toggleControl(this);" ><fmt:message key="ncwms.transect" /></button>
					</span>
				</td>
			</c:when>
			<c:when test='${!netcdf}'>
				<!-- Download data-->
				<td id="downloadDataParent" valign="middle" onmouseover="hoverInstructions('downloadDataParentHover', '1')" 
					onmouseout="hoverInstructions('downloadDataParentHover', '2')">
					<%@include file="Options/DownloadData.jsp" %>
				</td>
			</c:when>
		</c:choose>
		<td id="helpParent" valign="middle">
			<!-- Map Instructions-->
			<button type="button" class="buttonStyle" id="helpText" onmouseover="hoverInstructions('mapInstrucParentHover', '1');"
					onmouseout="hoverInstructions('mapInstrucParentHover', '2')"
					onclick="displayHelp();" atl="Help" /><fmt:message key="main.help" /></button>
		</td>
        </tr>
    </table>
</div>

<span id="helpHoverSpan" >
	<img onmouseover="hoverInstructions('helpIconHover', '1')" onmouseout="hoverInstructions('helpIconHover', '2')"
		 onclick="displayHoverHelp(this, '${basepath}');" id="helpHoverImg" src="${basepath}/common/images/Help/Help1.png">
	<span id="helpIconHover" class="commonHover">
		<fmt:message key="help.helpicon" />
	</span>
</span>