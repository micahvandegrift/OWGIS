<%-- 
    Document   : RequiredDivs
    Created on : Aug 3, 2012, 5:58:26 PM
    Author     : olmozavala
--%>
<!-- Map title -->
<div class="mapTitle" id="layerTitle"> <p id="pTitleText"> ${layerTitle} </p> </div>

<!-- This div contains all the map --> 
<div id="map"> </div>      
<div class="layersLongLat">
	<!-- This div displays the specific data of the map -->
	<b><fmt:message key="main.lon" />:&nbsp;&nbsp;<fmt:message key="main.lat" />: &nbsp;</b>
	<div id="location" ></div>
</div>    
