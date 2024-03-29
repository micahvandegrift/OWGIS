<script type="text/javascript"  >
    var width;
    var height;
    // TODO verify how this variable works for other layers and see
    // from where we can obtain the current elevation.
    var elevation = 0;

    //Should contain the selected start date and is used to obtain punctual
    // data from the temporal data.
    var startDate;

    //Creates 100 layer objects
    for(var i = 0; i<100; i++) {
        eval("var layer"+i);
    }

    OpenLayers.IMAGE_RELOAD_ATTEMPTS=5;
    OpenLayers.DOTS_PER_INCH=90.7142857142857;
    OpenLayers.Util.onImageLoadErrorColor = 'transparent';
    function init(){

            var bounds = new OpenLayers.Bounds.fromString(mapConfig.mapBounds);

        var extent = new OpenLayers.Bounds.fromString(mapConfig.restrictedExtent);
        var options = { controls: [],maxExtent: bounds,maxResolution: mapConfig.maxResolution,
            minResolution: mapConfig.minResolution,projection: 'EPSG:4326',units: 'degrees',
            restrictedExtent: extent,numZoomLevels:mapConfig.numZoomLevels};

        map = new OpenLayers.Map('map', options);
        //        map.addControl(new OpenLayers.Control.PanZoomBar({div: $('#panZoom')}));//Old
        map.addControl(new OpenLayers.Control.PanPanel()); // The 4 arrows that move the map
        map.addControl(new OpenLayers.Control.ZoomPanel());// The marks with the zoom
        map.addControl(new OpenLayers.Control.Navigation());
        //		map.addControl(new OpenLayers.Control.LayerSwitcher({allowSelection:true});
        //		map.addControl(new OpenLayers.Control.OverviewMap());
        keyboardnav = new OpenLayers.Control.KeyboardDefaults();
        map.addControl(keyboardnav);
        map.addControl(new OpenLayers.Control.ScaleLine({topOutUnits: 'km'}));
        map.addControl(new OpenLayers.Control.MousePosition({element: document.getElementById('location')}));

				
		initializeElevation();//checks to see if there is elevation and also changes the text to precipitation or elevation
		resizeMap();

		//create layer for line drawing, this is transect tool	 

		// Adding if creates the transect tool  
		//	Once the line creation is done it is passed to the fucntion handleDrawing(line) located in
		//OpenLayersRelated.js
		if(netcdf){
			//create layer for line drawing, this is transect tool	 
			var lineLayer = new OpenLayers.Layer.Vector("Line Layer");
			//temporary layer to redraw the user's drawing
			dlayer = new OpenLayers.Layer.Vector( "Drawing" );

			map.addLayers(lineLayer);//add drawing layer
							
			//controls for drawing, the reason it is an array is incase we want to add 
			//function for the polygon draw, point draw etc..	
			drawControls = {                   
				line: new OpenLayers.Control.DrawFeature(lineLayer,
				OpenLayers.Handler.Path)                    
			};
			
			//we add all the controls to the map
			for(var key in drawControls) {
				map.addControl(drawControls[key]);
			}

			//register function to be called when drawing finishes
			lineLayer.events.register('featureadded', lineLayer, function(event){
				dlayer.destroy();
				//if previos drawing existed then eliminated
				if (lineLayer.features.length > 1) {
					lineLayer.destroyFeatures(lineLayer.features[0]);
				}
						   
				// Get the linestring specification
				var line = event.feature.geometry.toString();
				dlayer = lineLayer.clone();
				handleDrawing(line);
			});
		}

		//This openLayerConfig is generated by the java file OpenLayersManager.java 
		//it loads all the layers that are needed for the map. 
		${openLayerConfig}
		}
</script>