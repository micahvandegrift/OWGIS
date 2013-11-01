<%-- 
    Document   : OptionalLayers
    Created on : Aug 3, 2012, 6:38:05 PM
    Author     : olmozavala
--%>

<%@ taglib prefix="menuHelper" uri="/WEB-INF/TLD/htmlStaticFunctions.tld" %>
<!-- This sections generate the menu of optional layers, normally vector layers -->
<table class="vectorLayer" border="0">
    <tr style="padding: 0px">
		<td class="vectorLayer">
			<p class="noShadowDarkBack optionalTitle noMargin" style="text-align: center; cursor:move;">
				<fmt:message key="main.optional" /></p>
		</td>
		<td class="vectorLayer">
			<span class="minimizeButton" onclick="minimizeWindow('optionalsMinimize', 'optionalMenuParent')" >&ndash;</span>
		</td>
	<tr>
	<tr>
		<td colspan="2" class="vectorLayer">
			${menuHelper:createOptionalLayersMenu(vectorLayers,language, basepath)}
		</td>
	</tr>
</table>
