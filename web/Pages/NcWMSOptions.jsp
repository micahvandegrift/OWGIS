<%-- 
    Document   : Calendars
    Created on : Aug 3, 2012, 5:53:19 PM
    Author     : olmozavala
--%>
<!-- Hold the gif of the animation, it is called by OpenLayers -->
<div id="mapOverlayDiv" ><img id="mapOverlay" alt="map overlay" onload="javascript:animationLoaded();" /></div>

<c:if test='${netcdf}' >


    <!-- Divs that hold the start and end calendar plus the related texts -->
    <div class="draggableWindow" id="CalendarsAndStopContainer">
        <table id="CalendarParent" border="0">
            <!--<tr><th colspan="2"><p class="defShadow">Dates range for Base layer</p></th></tr>-->
            <tr>
                <td><span id="hideOneDay" class="calTitle ie_shadow"> <fmt:message key="ncwms.cal.start" /></span></td>
                <td><span id="hideOneDayEnd" class="calTitle ie_shadow" style="padding-right:0px;"><fmt:message key="ncwms.cal.end" /></span>
					<span class="minimizeButton" onclick="minimizeWindow('calendarsMinimize', 'CalendarsAndStopContainer')" >
						&ndash;</span>
					</td>
            <tr>
                <td> <div class="startDateCal" id="cal-start"> </div> </td>
                <td> <div class="endDateCal" id="cal-end"> </div> </td>
            </tr>
			<tr>
				<td colspan="2">
					 <p class="middleText noMargin"> <fmt:message key='ncwms.resolution'/>
                                          <input type="radio" value="high" name="video_res" ><fmt:message key='ncwms.resolutionHigh'/>
						 <input type="radio" value="normal" name="video_res" checked><fmt:message key='ncwms.resolutionMiddle'/>
						<input type="radio" value="low" name="video_res" ><fmt:message key='ncwms.resolutionLow'/>
					 </p>
				</td>
			</tr>
            <tr>
                <td  style="text-align: center">
                    <div class="dispAnimation" id="p-animation">
                        <button type="button" style="display:inline" 
                                class="buttonStyle" onclick="dispAnimation();" > 
                           <fmt:message key="ncwms.dispanim" />
					    </button> 
					</div>
                </td>
				<td>
                    <!-- This select is filled in by javascript  -->
                    <select id="timeSelect" name="timeSelect">
                    </select>
                    </div>
                </td>
            </tr>
        </table>

        <!-- The stop animation button appears when an animation is playing or loading -->

        <div id="s-animation" > 
            <button type="button" 
                    onclick="stopAnimation();" 
                    class="buttonStyle" ><fmt:message key="ncwms.stopanim" /></button>
        </div> 
    </div>
</c:if>
