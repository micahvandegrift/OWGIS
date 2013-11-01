/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var firstCallToFilter = true;
var defaultCQLfilter = "";
var format = new OpenLayers.Format.CQL();

/**
 * This functions hides and shows the text box to input a custom CQL filter
 */
function toggleCustomFilterTextBox(){
		$('#ocqlFilterInputTextParent').css("display","block");
}

function closeCustomFilterTextBox(){
    
    $('#ocqlFilterInputTextParent').css("display","none");

}




/**
 * When the user hits enter on the cqlfilter text box, the filter
 * gets applied into the base layer.
 */
function applyFilterOnEnter(){
	if(event.keyCode == 13){
		newcql_filter = applyCqlFilter();
	}
	return false;
}

/**
 * Applies the cql filter into the base layer.
* @return String currFilter Returns the current filter been applied into the base layer
 */
function applyCqlFilter(){

	//Obtain OL main layer
	currMainLayer = getMainLayer();

	// It initializes the defaultCQLfilter variable (only the firt time
	// the user applies a filter)
	if(firstCallToFilter){
		firstCallToFilter = false;
		defaultCQLfilter = currMainLayer.params.CQL_FILTER;
	}

	currFilter = $('#idOcqlFilterInputText').val();
	
	$('#ocqlErrorParent').css('display','none');

	//In case of empty text box or not defined (layers with CQL_FILTER
	// but without custom filter option.
	if(currFilter == "" ||currFilter == undefined ){
		currMainLayer.params.CQL_FILTER = defaultCQLfilter; 
	}else{

		if(defaultCQLfilter == "" || defaultCQLfilter == undefined){
			currMainLayer.params.CQL_FILTER = currFilter;
		} else{
			/* The error mesasge is being to restrictive
			try{
				format.read(currFilter)
			}catch(err){
				$('#ocqlErrorParent').css('display','block');
				$('#ocqlErrorText').text(err.message);
				return;
			}*/
			currMainLayer.params.CQL_FILTER = defaultCQLfilter +" AND "+ currFilter;
		}
	}

	// The next line, should be enough to update the filter layer but is not.
	currMainLayer.setVisibility(false);
	currMainLayer.redraw();
	// Trying other options
	map.updateSize();
	cent = map.getCenter();
	map.setCenter(cent);
	currMainLayer.setVisibility(true);

	//Returns the actual filter been used
	updateKmlLink('','',currMainLayer.params.CQL_FILTER);
	return currMainLayer.params.CQL_FILTER;
}


function addCQLFilterToPunctualData(currLayer){

	if(currLayer.params.CQL_FILTER != "" && currLayer.params.CQL_FILTER != undefined ){
		cqlfilterloc= "CQL_FILTER="+currLayer.params.CQL_FILTER+"&";
		cqlfilterloc= cqlfilterloc.replace(new RegExp("%", 'g'), "^");
		return cqlfilterloc;
	}else{
		return "";
	}
}