<%-- 
    Document   : Palettes
    Created on : Aug 3, 2012, 6:26:56 PM
    Author     : olmozavala
--%>
<!-- This tables displayes the current palette and the color range used on it -->
<c:if test='${netcdf}'>

    <!-- This tables displayes the current palette and the color range used on it -->
    <table border="0" id="palette" class="draggableWindow" >
        <tr>
            <td rowspan="3"> 
                <img id="imgPalette" src="${paletteUrl}" onclick="displayOptionalPalettes();" 
                     onmouseover="this.style.cursor = 'pointer';"
                     onmouseout="this.style.cursor = 'crosshair';" onload="getDefault();"/>
            </td>
            <td >
                <p class="palMinMax">
                    Max:<input onblur="UpdatePalette(mappalette);" onfocus="keyboardnav.deactivate();" 
                               onkeydown="if (event.keyCode == 13)
                                            UpdatePalette(mappalette);"
                               id="maxPal" name="maxPal" type="text" size="4" />
                    <span onclick="showPalettes();" ><img class="closeButton" src="${basepath}/common/images/close.gif" /></span>
                    <br>
                    <img class="plusButton" id="plusButtonElevation" onclick="increaseMaxColorRange(1);" 
                         onmouseover="rollImage(this, '${basepath}/common/images/Menu/PlusInv.png')"
                         onmouseout="rollImage(this, '${basepath}/common/images/Menu/Plus.png')"
                         onmousedown="rollImage(this, '${basepath}/common/images/Menu/PlusPressed.png')"
                         onmouseup="rollImage(this, '${basepath}/common/images/Menu/PlusInv.png')"
                         src="${basepath}/common/images/Menu/Plus.png" alt="IncTrans" width="15" height="15" />

                    <img class="minusButton" id="minusButtonElevation" onclick="increaseMaxColorRange(-1);" 
                         onmouseover="rollImage(this, '${basepath}/common/images/Menu/MinusInv.png')"
                         onmouseout="rollImage(this, '${basepath}/common/images/Menu/Minus.png')"
                         onmousedown="rollImage(this, '${basepath}/common/images/Menu/MinusPressed.png')"
                         onmouseup="rollImage(this, '${basepath}/common/images/Menu/MinusInv.png')"
                         src="${basepath}/common/images/Menu/Minus.png" alt="DecTrans" width="15" height="15" />
                </p>
            </td>
        </tr>
        <tr >
            <td >
                <p class="buttonStyle reducedPadding" id="updateColorRangeButton" onclick="UpdatePalette(mappalette);" > <fmt:message key="ncwms.update" /></p>
        <p class="buttonStyle reducedPadding" id="autoColorRangeButton"  onclick="setColorRangeFromMinMax();" > <fmt:message key="ncwms.auto" /></p>	
        </td>
        </tr>
        <tr>
            <td >
                <p class="palMinMax">
                    <img class="plusButton" id="plusButtonElevation" onclick="decreaseMinColorRange(-1);" 
                         onmouseover="rollImage(this, '${basepath}/common/images/Menu/PlusInv.png')"
                         onmouseout="rollImage(this, '${basepath}/common/images/Menu/Plus.png')"
                         onmousedown="rollImage(this, '${basepath}/common/images/Menu/PlusPressed.png')"
                         onmouseup="rollImage(this, '${basepath}/common/images/Menu/PlusInv.png')"
                         src="${basepath}/common/images/Menu/Plus.png" alt="IncTrans" width="15" height="15" />

                    <img class="minusButton" id="minusButtonElevation" onclick="decreaseMinColorRange(1);" 
                         onmouseover="rollImage(this, '${basepath}/common/images/Menu/MinusInv.png')"
                         onmouseout="rollImage(this, '${basepath}/common/images/Menu/Minus.png')"
                         onmousedown="rollImage(this, '${basepath}/common/images/Menu/MinusPressed.png')"
                         onmouseup="rollImage(this, '${basepath}/common/images/Menu/MinusInv.png')"
                         src="${basepath}/common/images/Menu/Minus.png" alt="DecTrans" width="15" height="15" />
                    <br>
                    Min: <input onblur="UpdatePalette(mappalette);" onfocus="keyboardnav.deactivate();" 
                                onkeydown="if (event.keyCode == 13)
                                            UpdatePalette(mappalette);"
                                id="minPal" name="minPal" type="text" size="4" />
                </p>
            </td>
        </tr>
    </table>
    <!-- Holds the optional palettes -->
    <div class="draggableWindow" id="palettes-div">
        <table id="optionalPalettesHeader">
            <tr>
                <td> <p class="defShadow"><fmt:message key="ncwms.newpal" /></p> </td>
            <td>
                <p class="buttonStyle reducedPadding" 
                   id="defaultColorRangeButton" onclick="DefaultPalette();"> Default</p>	
            </td>
            <td>
                <span onclick="displayOptionalPalettes();" ><img class="closeButton" src="${basepath}/common/images/close.gif" /></span>
            </td>
            </tr>
        </table>
        <table id="palettesTable"></table>
    </div>
</c:if>