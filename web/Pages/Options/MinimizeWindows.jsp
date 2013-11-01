<%-- 
    Document   : MinimizeWindows
    Created on : Feb 19, 2013, 4:32:14 PM
    Author     : khan
--%>
<table align="center" border="0"   width="400" cellpadding="5" style="position:absolute;bottom:0;left:0px;">
    <tr>
		<td  align="left" width="105">
            <span id="mainMenuMinimize" class="draggableWindow">
                <table >
					<tr>
						<td> <p  class="invShadow" style="text-align: center; font-size: 12px;padding-right:5px;"><fmt:message key="main.base" /></p></td>
					<td> <span class="maximizeButton" onclick="minimizeWindow('mainMenuParent','mainMenuMinimize')" 
							   style="margin-bottom: 10px;" >+</span></td>
					</tr>
				</table>
			</span>                
        </td>
        <td  align="left" width="90">
            <span id="calendarsMinimize" class="draggableWindow">
                <table >
					<tr>
						<td><p  class="invShadow" style="text-align: center; font-size: 12px;padding-right:5px;"><fmt:message key="ncwms.cal.title" /></p></td>
						<td><span class="maximizeButton" onclick="minimizeWindow('CalendarsAndStopContainer','calendarsMinimize')" 
							  style="margin-bottom: 10px;" >+</span></td>
					</tr>
				</table>
			</span>                
        </td>
		<td  align="left">
            <span id="optionalsMinimize" class="draggableWindow">
                <table >
					<tr>
                        <td> <p  class="invShadow" style="text-align: center; font-size: 12px;padding-right:5px;"><fmt:message key="main.optional" /></p></td>
						<td><span class="maximizeButton" onclick="minimizeWindow('optionalMenuParent','optionalsMinimize')" 
							  style="margin-bottom: 10px;" >+</span></td>
					</tr>
				</table>
			</span>                
        </td>
    </tr>
</table> 