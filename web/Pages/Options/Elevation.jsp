<%-- 
    Document   : Elevation
    Created on : Aug 3, 2012, 6:23:54 PM
    Author     : olmozavala
--%>


<%-- 
This page controls the elevation of the displayed layer, it can be height or precipitation as well. 
There is 3 options. Either '-' or '+' or click the button to choose the disired height or precipitation level. 
--%>



<span class="defaultOptions" style="cursor: pointer;"> 
    <table cellpadding="0" border="0">
        <tr>
            <td><button type="button" id="minusButtonElevation" style="cursor: pointer;"  class="minusButton" onclick="changeElevation('-', layer1);">
                    -
                </button></td>
            <td>	
                <span style="cursor: pointer;" id="elevationText"  class ="middleText" onclick ="displayElevationSelector() " 
					  onmouseover="hoverInstructions('elevationParentHover', '1')" onmouseout="hoverInstructions('elevationParentHover', '2')" >
					<fmt:message key="ncwms.depth" /></span>
            </td>
            <td>
                <button class="plusButton" disabled="disabled" id="plusButtonElevation" style="cursor: pointer;" type="button"  onclick="changeElevation('+', layer1);">
                    +
                </button>
            </td>
        </tr>		       
    </table>
</span>

