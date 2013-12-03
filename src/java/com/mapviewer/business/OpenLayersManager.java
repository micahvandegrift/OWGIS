/*
* Copyright (c) 2013 Olmo Zavala
* Permission is hereby granted, free of charge, to any person obtaining a copy of 
* this software and associated documentation files (the "Software"), to deal in the 
* Software without restriction, including without limitation the rights to use, copy, 
* modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and 
* to permit persons to whom the Software is furnished to do so, subject to the following conditions: 
* The above copyright notice and this permission notice shall be included in all copies or substantial 
* portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
* INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR 
* PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE 
* FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, 
* ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
*/
package com.mapviewer.business;

import com.mapviewer.conf.UserConfig;
import com.mapviewer.exceptions.XMLFilesException;
import com.mapviewer.model.Layer;
import com.mapviewer.model.menu.MenuEntry;
import com.mapviewer.tools.ConvertionTools;
import com.mapviewer.tools.HtmlTools;
import com.mapviewer.tools.StringAndNumbers;
import java.util.ArrayList;

/**
 * This class is in charge of configuring all the visualization for OpenLayers Its the
 * main class of the MapViewer and its in charge of administrating all the layers coming
 * from the different servers.
 *
 * @author Olmo Zavala Romero
 */
public class OpenLayersManager {

	LayerMenuManagerSingleton layersManager;
	String language;
	String basepath;

	/**
	 * Obtains the index of the list of vector layers or raster depending on the
	 * selection.
	 *
	 * @param {MenuEntry[]} menuEntry User selection array
	 * @param {boolean} layerType layer type checking it can be HtmlTools.RASTER_LAYERS
	 * @return int[] we return an array with the index of the layers.
	 */
	public int[] obtainArrayIndexOfLayers(MenuEntry[] menuEntry) {
		int index = 0;
		if (menuEntry.length == 0) {//if not entry return null
			return null;
		}

		ArrayList<Integer> resultado = new ArrayList();//temporal list to hold values
		Layer tempLayer = null;//temp layer used to check 
		//loop alentries until find the one selected
		for (index = 0; index < layersManager.getMainLayers().size(); index++) {
			tempLayer = layersManager.getMainLayers().get(index);//get index of this layer
			if (tempLayer.isThisLayer(menuEntry)) {//if this is the layer we are looking for
				resultado.add(Integer.valueOf(index));//add layer to list of index
				break;//if found then break from for loop. 
			}
		}

		//convert into array of ints and then return. 
		return ConvertionTools.convertObjectArrayToIntArray(resultado.toArray());
	}

	public int[] obtainIndexForOptionalLayers(String[] menuSelected) {

		if (menuSelected.length == 0) {//if not entry return null
			return null;
		}

		ArrayList<Integer> result = new ArrayList();//temporal list to hold values
		Layer tempLayer = null;
		for (int index = 0; index < layersManager.getVectorLayers().size(); index++) {//loop each vector layer
			tempLayer = layersManager.getVectorLayers().get(index);//temp variable 

			//each tree value has to be send separately. 
			//becuase the vector layers are all on one level of the tree
			for (int menuNumber = 0; menuNumber < menuSelected.length; menuNumber++) {
				if (tempLayer.isThisLayer(StringAndNumbers.strArrayFromStringColon(menuSelected[menuNumber]))) {
					//check to see if the layer is selected
					result.add(Integer.valueOf(index));
					break;
				}
			}
		}
		//convert into array of ints and then return. 
		return ConvertionTools.convertObjectArrayToIntArray(result.toArray());
	}

	/**
	 * Class constructor, it defines the file that will store the layers info
	 *
	 * @params {String} basepath - The base path of the site (DeepCProject, ACDM, etc.)
	 */
	public OpenLayersManager(String basePath) throws XMLFilesException {
		this.layersManager = LayerMenuManagerSingleton.getInstance();
		this.basepath = basePath;
	}

	/**
	 * Main method in charge of creating the OpenLayers dynamic configuration
	 *
	 * @param {int []} idsBaseLayers Arreglo que contiene los ids de las capas principales
	 * que se muestran/array with ids of the main layers
	 * @param {int[]} extraLayers Arreglo de las capas extras o vectoriales/array of
	 * vector layers
	 * @param {UserConfig} opConfig Objeto de configuracion de OpenLayers/configuration
	 * object of openlayers
	 * @return String Regresa toda la configuracion de OpenLayers como una cadena/returns
	 * the configuration in a string form.
	 */
	public String createOpenLayConfig(int[] idsBaseLayers, int[] extraLayers, UserConfig opConfig, String language) {
		this.language = language;//Sets the language of the configuration
		if (extraLayers == null)//Evitamos que extraLayers sea nulo
		{
			extraLayers = new int[0];
		}
		if (idsBaseLayers == null)//Evitamos que baseLayers sea nulo
		{
			idsBaseLayers = new int[0];
		}
		String result = "";//Esta variable contiene toda la configuracion de OpenLayers
		result += this.createInitFunction(idsBaseLayers, extraLayers, opConfig) + "\n";//Esta funcion crea la configuracion central de OpenLayers
		return result;//Regresamos la configuracion
	}

	/**
	 * Generates the text of the javascript function Init() Genera el texto en javaScript
	 * de la funcion Init() que utiliza OpenLayers
	 *
	 * @param idsBaseLayers int[] Arreglo con los identificadores de las capas raster
	 * seleccionadas
	 * @param extraLayers int[] Arreglo de capas extras o vectoriales
	 * @param opConfig OpenLayerConfig Configuracion de OpenLayers
	 * @return String Cadena que contiene al configuracion de OpenLayers en la funcion
	 * init()
	 */
	private String createInitFunction(int[] idsBaseLayers, int[] extraLayers, UserConfig opConfig) {
		String initFunction = "\n";

		//Configura las capas que va a ver el usuario en el formato necesario para OpenLayers
		initFunction += this.createSeparateLayerScript(idsBaseLayers, extraLayers, opConfig);
		initFunction += this.createMapAddLayers(idsBaseLayers, extraLayers);//Agrega las capas anteriores a un mapa compuesto
		//Dependiendo de los controles que se definan en el objeto de OpenLayerConfig
		//se agregan controles a OpenLayers. Estos controles pueden ser, barra de zoom, mini map, etc.

		initFunction += this.agregaURLparaTraerDatos(idsBaseLayers[0]);
//		initFunction += "map.zoomToMaxExtent();\n";
		if ((opConfig.getCenter() != null) && (opConfig.getZoom() != null)) {
			initFunction += this.setCenterToMap(opConfig.getCenter(), opConfig.getZoom());
		}

		return initFunction;
	}

	private String setCenterToMap(String centerValue, String zoom) {
		String reCenterZoom = "//The zoom needs to be updated first, otherwhise the center doesn't get updated\n";
		reCenterZoom += "\tmap.zoomTo(" + zoom + ");\n";
		reCenterZoom += "\tmap.setCenter(new OpenLayers.LonLat(" + centerValue + "));\n";
		return reCenterZoom;
	}

	/**
	 * This function creates the url to bring the data when the user clicks somewhere on
	 * the map.
	 *
	 * @param {int} baseLayer
	 *
	 */
	private String agregaURLparaTraerDatos(int baseLayer) {
		String layersScript = "";
		Layer actualLayer = null;
		int layerCount = 0;
		layersScript = "\n\tmap.events.register('click', map, function (e) {\n";//Se agrega al evento click del div map la siguiente funcion

		//En este for agrega las capas que son de fondo
		for (int i = 0; i < layersManager.getBackgroundLayers().size(); i++) {
			actualLayer = layersManager.getBackgroundLayers().get(i);
			if (actualLayer.getFeatureInfoLayer() != null) {
				layersScript += generateURLhelper(actualLayer, layerCount);
			}//If layer featureInfo not null
			layerCount++;
		}
		//Se agrega el URL de la capa base
		actualLayer = layersManager.getMainLayers().get(baseLayer);
		if (actualLayer.getFeatureInfoLayer() != null) {
			layersScript += generateURLhelper(actualLayer, layerCount);
		}//If layer  featureInfo  not null

		layerCount++;//Increment current layer (for  javascript, 'layer0' or 'layer1'....

		for (int i = 0; i < layersManager.getVectorLayers().size(); i++) {
			actualLayer = layersManager.getVectorLayers().get(i);
			if (actualLayer.getFeatureInfoLayer() != null) {
				layersScript += generateURLhelper(actualLayer, layerCount);
			}//If layer  featureInfo not null
			layerCount++;
		}

		layersScript += //"\t\tMapViewersubmitForm();\n" +
				"\t});\n";
		return layersScript;
	}

	/**
	 * Creates the url script for one layer. It is used to aquire the specific data of one
	 * layer. Its when the user clicks on the map
	 *
	 * @param actualLayer Layer Object of the layer we are working on.
	 * @param layerNumber int Consecutive number of the layer we are generating the url
	 * @param mainLayer boolean Indicates if the layer is the main layer or not.
	 * @return
	 */
//	private String generateURLhelper(Layer actualLayer, int ajaxCallNumber, int layerNumber) {
	private String generateURLhelper(Layer actualLayer, int layerNumber) {

		String URLscript = "";

		URLscript += "\t\tif(layer" + layerNumber + ".getVisibility()){\n";

		URLscript += "\t\t\tvar url" + layerNumber + " =\"" + basepath + "/redirect?server=" + actualLayer.getServer() + "&";
		URLscript += "LAYERS=" + actualLayer.getFeatureInfoLayer() + "&";
		URLscript += "STYLES=&"
				+ "WIDTH=\"+map.size.w+\"&"
				+ "HEIGHT=\"+map.size.h+\"&"
				+ "SRS=" + actualLayer.getProjection() + "&"
				+ "FORMAT=" + actualLayer.getFormat() + "&"
				+ "SERVICE=WMS&"
				+ "VERSION=1.1.1&"
				+ "REQUEST=GetFeatureInfo&"
				+ "EXCEPTIONS=application/vnd.ogc.se_xml&"
				+ "BBOX=\"+map.getExtent().toBBOX()+\"&"
				+ "x=\"+parseInt(e.xy.x)+\"&"
				+ "y=\"+parseInt(e.xy.y)+\"&";

		// For layers with CQL filtering
		URLscript += "\"+addCQLFilterToPunctualData(layer"+layerNumber+")+\"";

		//In this case we also need the time information
		if (actualLayer.isNetCDF()) {
			// The two variables: elevation and startDate have to match
			// javascript variable names. 
//			URLscript += "ELEVATION=\"+layerDetails.zaxis.values[elev_glob_counter]+\"&" 
			URLscript += "\"+addElevationText()+\""
					+ "TIME=\"+getCurrentlySelectedDate(\"%Y-%m-%d\")+\"&"
					+ "BOTHTIMES=\"+getUserSelectedTimeFrame()+\"&"
					+ "INFO_FORMAT=text/xml&"
					+ "NETCDF=true&";
		} else {
			URLscript += "INFO_FORMAT=text/html&"
					+ "NETCDF=false&";
		}


		URLscript += "QUERY_LAYERS=" + actualLayer.getFeatureInfoLayer() + "&";
		URLscript += "FEATURE_COUNT=50\";\n";
		URLscript += "\t\t\t setPopUp(e);\n"
				//					+ "\t\t\t loadFeatureInfo(url" + layerNumber + ");\n"
				+ "\t\t\t var asynchronous" + layerNumber + " = new Asynchronous();\n"
				+ "\t\t\t asynchronous" + layerNumber + ".complete = AsyncPunctualData;\n"
				+ "\t\t\t asynchronous" + layerNumber + ".call(url" + layerNumber + ");\n"
				+ "\t\t}\n";
		return URLscript;
	}

	/**
	 * Genera el script para agregar las capas base al mapa de OL
	 *
	 * @param idsBaseLayers
	 * @return String texto de javascript que agrega las capas existentes al mapa de
	 * openlayers
	 */
	private String createMapAddLayers(int[] idsBaseLayers, int[] idsExtraLayers) {
		String layerName = "";
		String genMap = "\tmap.addLayers([";
		//for(int i=0;i<(idsBaseLayers.length+idsExtraLayers.length+layersManager.getBackgroundLayers().size()-1);i++){
		for (int i = 0; i < (idsBaseLayers.length + layersManager.getVectorLayers().size() + layersManager.getBackgroundLayers().size()); i++) {
			layerName = "layer" + i;
			if (i != 0) {
				genMap += ",";
			}
			genMap += layerName;
		}
		genMap += "]);\n";
		return genMap;
	}

	/**
	 * It helps to create each layer in a open layer script
	 *
	 * @param actualLayer
	 * @param layerCount
	 * @param visible Boolean indicates if the layer should be visible
	 * @return
	 */
	private String layerHelper(Layer actualLayer, int layerCount, boolean visible, UserConfig opConfig) {
		String layersScript = "";
		layersScript += "\tlayer" + layerCount + " = new OpenLayers.Layer.WMS('"
				+ actualLayer.getDisplayName(language) + "','"
				+ actualLayer.getServer() + "', {"
				+ "height: " + actualLayer.getHeight() + ","
				+ "width: " + actualLayer.getWidth() + ","
				//				+ "height: heightNum,"
				//				+ "width: widthNum,"
				+ "layers: '" + actualLayer.getName() + "',";


		if (actualLayer.isNetCDF()) {
			if (actualLayer.getMaxColor() != -1 && actualLayer.getMinColor() != -1) {
				layersScript += "colorscalerange: '" + actualLayer.getMinColor() + "," + actualLayer.getMaxColor() + "',";
			}
			if (opConfig.getPalette() != null) {
				layersScript += "styles: '" + actualLayer.getStyle() + "/" + opConfig.getPalette() + "',";
				// In this case there is no color asigned

			} else {
				if (actualLayer.getPalette().equals("")) {
					layersScript += "styles: '',";
				} else {
					layersScript += "styles: '" + actualLayer.getStyle() + "/" + actualLayer.getPalette() + "',";
				}
			}

		} else {
			layersScript += "styles: '" + actualLayer.getStyle() + "',";
		}

		layersScript += "srs: '" + actualLayer.getProjection() + "',";

		if (layerCount > 0) {//If it is not the first layer then we make it transparent. 
			layersScript += "transparent: true,";
		}
		if (actualLayer.getLayout() != null) {
			layersScript += "format_options: '" + actualLayer.getLayout() + "',";
		}

		layersScript += "format: '" + actualLayer.getFormat() + "'";
		if (!actualLayer.isConfGeoWebCache()) {
			if (actualLayer.isTiled()) {

				layersScript += ",tiled: true,";
				layersScript += "tilesOrigin: '" + actualLayer.getTilesOrigin() + "'},\n\t\t\t";

				//Verify we are using the transition effect
				if (!actualLayer.getTransEffect().equalsIgnoreCase("none")) {
					layersScript += "{transitionEffect: '" + actualLayer.getTransEffect() + "'},";
				}

				layersScript += "{buffer: 1, displayOutsideMaxExtent: true";

				if (layerCount == 0) {//If is the first layer then we put it as baseLayer
					layersScript += ", isBaseLayer: true";
				}

				layersScript += "}";

			} else {
				layersScript += "},\n\t\t\t";

				//Verify we are using the transition effect
				if (!actualLayer.getTransEffect().equalsIgnoreCase("none")) {
					layersScript += "{transitionEffect: '" + actualLayer.getTransEffect() + "'},";
				}

				layersScript += "   {singleTile: true, ratio: 1";

				if (layerCount == 0) {//If is the first layer then we put it as baseLayer
					layersScript += ", isBaseLayer: true";
				}

				layersScript += "}";

			}
		}
		layersScript += ");\n";

		//In this case the layer has some CQL that we need to add in its configuration
		if (!actualLayer.getCql().equals("")) {
			layersScript += "\tlayer" + layerCount + ".params.CQL_FILTER= \"" + actualLayer.getCql() + "\";\n";
		}

		if (!visible) {
			layersScript += "\tlayer" + layerCount + ".setVisibility(false);\n";//we make the layer not visible. 
		}
		return layersScript;
	}

	/**
	 * Creates the javascript of the layers that are going to be shown separatly. Each
	 * layer is an object first is the raster layers then the vector layers.
	 *
	 * @param idsBaseLayers
	 * @param idsExtraLayers
	 * @param opConfig
	 * @return
	 */
	private String createSeparateLayerScript(int[] idsBaseLayers, int[] idsExtraLayers, UserConfig opConfig) {
		String layersScript = "";
		Layer actualLayer = null;
		int layerCount = 0;
		//add the layers that are the background. 
		for (int i = 0; i < layersManager.getBackgroundLayers().size(); i++) {
			actualLayer = layersManager.getBackgroundLayers().get(i);
			if (actualLayer.getName() != null) {
				layersScript += layerHelper(actualLayer, layerCount, true, opConfig);
				layerCount++;
			}//If layer not null            
		}

		//Generates the layer configuration for OpenLayers
		// The name of the layer variable inside OpenLayers is of the form layer'number of layer'
		for (int i = 0; i < idsBaseLayers.length; i++) {
			actualLayer = layersManager.getMainLayers().get(idsBaseLayers[i]);
			if (actualLayer.getName() != null) {
				layersScript += layerHelper(actualLayer, layerCount, true, opConfig);
				layerCount++;
			}//If layer not null
		}
//        for(int i =0;i<idsExtraLayers.length;i++){
//            actualLayer = layersManager.getVectorLayers().get(idsExtraLayers[i]);
//            layersScript+=layerHelper(actualLayer, layerCount);
//            layerCount++;
//        }
		for (int i = 0; i < layersManager.getVectorLayers().size(); i++) {
			actualLayer = layersManager.getVectorLayers().get(i);
			if (StringAndNumbers.intArrayContains(idsExtraLayers, i)) {//Si esta en los seleccionados lo mostramos
				//Si no no
				layersScript += layerHelper(actualLayer, layerCount, true, opConfig);
			} else {
				layersScript += layerHelper(actualLayer, layerCount, false, opConfig);
			}
			layerCount++;
		}
		return layersScript;
	}

	/**
	 * Gets the list of Background layers
	 *
	 * @return ArrayList<Layer>
	 */
	public ArrayList<Layer> getBackgroundLayers() {
		return layersManager.getBackgroundLayers();
	}

	/**
	 * Obtains e list of raster layers
	 *
	 * @return ArrayList<Layer>
	 */
	public ArrayList<Layer> getRasterLayers() {
		return layersManager.getMainLayers();
	}

	/**
	 * Obtains a list of vector layers.
	 *
	 * @return ArrayList<Layer>
	 */
	public ArrayList<Layer> getVectorLayers() {
		return layersManager.getVectorLayers();
	}

	public int getTotalLayers() {
		return layersManager.getBackgroundLayers().size() + layersManager.getMainLayers().size() + layersManager.getVectorLayers().size();

	}

	public int getTotalVisibleLayers() {
		int layerCount = 0;
		//loop incharge of the layers that are background. 
		for (int i = 0; i < layersManager.getBackgroundLayers().size(); i++) {
			if (layersManager.getBackgroundLayers().get(i).getName() != null) {
				layerCount++;
			}
		}
		layerCount += 1;//represents the base layer. 
		for (int i = 0; i < layersManager.getVectorLayers().size(); i++) {
			if (layersManager.getVectorLayers().get(i).getName() != null) {
				layerCount++;
			}//If layer not null
		}
		return layerCount;
	}
}
