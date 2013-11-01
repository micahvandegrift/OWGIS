<%-- 
    Document   : KmlLink
    Created on : Aug 3, 2012, 6:34:03 PM
    Author     : olmozavala
--%>

<%-- 
Download the link to view in Google Earth
--%>




<a  id="kmlLink" href="${linkKML}" style="text-align:center;" onclick="KMZDownAlert()">
	<button type="button" class="buttonStyle" id="dynamicFont_kml" style="padding:0px 6px">
            <table><tr><td>
                        <img  border="0" src="${basepath}/common/images/kmz/google_earth_logo_topMenu.png" alt="Descargar en KML" /></td><td>
		Google Earth</td></tr></table></button>
</a>
