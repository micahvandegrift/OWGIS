/* 
 * This file contains all the JavaScript functions
 * that are related with the hover 'help' texts
 * or the 'help' main div.
 */

function getop(x) {
	o = document.getElementById(x);
	var t = o.offsetTop;
	while (o = o.offsetParent)
		t += o.offsetTop;
	return t;
}
function getleft(x) {
	o = document.getElementById(x);
	var l = o.offsetLeft;
	while (o = o.offsetParent)
		l += o.offsetLeft;

	return l;
}
function getwidth(x) {
	o = document.getElementById(x);
	var l = o.offsetWidth;

	return l;
}

function resizeHelpInstructions() {
	border = .70; //What is the border percentage we want (equal from top than sides)
	$("#helpInstructions").draggable();//Make helpInstructions draggable

	//Sets the size of the main help instructions container
	$("#helpInstructions").css("width", widthNum * border + "px");
	$("#helpInstructions").css("height", heightNum * border + "px");

	//Sets the size of the content for help instructions 
	$("#helpInstructionsContent").css("width", widthNum * border - 20 + "px");
	$("#helpInstructionsContent").css("height", heightNum * border - 75 + "px");

}

/**
 this function handles the instruction messages that apears when the user hovers over a button. 
 pass in id of element and pass in option 1 to make it apear and 2 to disapear
 @params id_name - could be any named passed in as long as it matches an if statment inside this function. 
 @params option - 1 for apear and 2 for disappear. 
 */
function hoverInstructions(id_name, option)
{
//if mobile or hover disabled don't display the hover help instructions
	if (mobile || hoverDisabled)
		return;

	if (option == "2")
	{
		if (document.getElementById(id_name) != null) {
			document.getElementById(id_name).style.display = "none";
		}
	}
	else
	{
		if (document.getElementById(id_name) != null) {
			document.getElementById(id_name).style.display = "block";
		}
	}

}


/**
 * This function initializes the positions of the
 * help texts depending on the menuDesign selected.
 */
function initHelpTxtPos() {
	design = mapConfig['menuDesign'];

	if (design == "topMenu") {
		initHelpTextTopMenu();
	} else {
		initHelpTextSideMenu();
	}
}

/**
 * This function is a helper function specifically for the function initHelpTextTopMenu
 * It only checks if an id exists and is visible
 */
function testVisibility(id) {
	if (document.getElementById(id) != null &&
			document.getElementById(id).style.display != 'none')
		return true;
	else
		return false;
}
/**
 *  This function initializes the help text positions
 *  for the specific case of the 'topMenu'
 */
function initHelpTextTopMenu() {

	//Defines the sizes for each button on the
	// top menu (we need something different for the side menu).

	hideCalSize = 120;
	googleLinkSize = 140;//Google link
	transSize = 130;// Transparency button
	elevSize = 90;// Elevation options
	paletteSize = 115;// Pallete button
	transectSize = 110;// Transect tool button
	downloadDataSize = 110;// Download data button
	helpSize = 60;// Help button

	// Find how much space is being used on the menu
	totalSize = 160;//Extra size to start more or less on the left

	if (testVisibility("hideCalendarButtonParent")) {
		totalSize += hideCalSize; //Size of 'transparency' button
	}
	if (testVisibility("transParent")) {
		totalSize += transSize; //Size of 'transparency' button
	}

	if (testVisibility("elevationParent")) {
		totalSize += elevSize; //Size of 'depth' button
	}
	if (testVisibility("palettesMenuParent")) {
		totalSize += paletteSize; //Size of 'palettes' button
	}
	if (testVisibility("transectParent")) {
		totalSize += transectSize; //Size of 'transect' button
	}
	if (testVisibility("downloadDataParent")) {
		totalSize += downloadDataSize; //Size of 'transect' button
	}
	totalSize += helpSize;

	firstLeftPos = widthNum / 2 - totalSize / 2;

	offset = firstLeftPos;

	if (testVisibility("hideCalendarButtonParent")) {
		offset += hideCalSize; //Size of 'transparency' button
	}

	$("#mainKmlParentHover").css("left", offset + "px");
	offset += googleLinkSize; //Size of 'Google' button

	if (testVisibility("transParent")) {
		$("#transParentHover").css("left", offset + "px");
		offset += transSize; //Size of 'transparency' button
	}

	if (testVisibility("elevationParent")) {
		$("#elevationParentHover").css("left", offset + "px");
		offset += elevSize; //Size of 'depth' button
	}
	if (testVisibility("palettesMenuParent")) {
		$("#palettesHover").css("left", offset + "px");
		offset += paletteSize; //Size of 'palettes' button
	}
	if (testVisibility("transectParent")) {
		$("#transectParentHover").css("left", offset + "px");
		offset += transectSize; //Size of 'transect' button
	}

	if (testVisibility("downloadDataParent")) {
		$("#downloadDataParentHover").css("left", offset + "px");
		offset += downloadDataSize; //Size of 'transect' button
	}

	$("#mapInstrucParentHover").css("left", offset + "px");
	offset += helpSize; //Size of 'transect' button

}

/**
 *  This function initializes the help text positions
 *  for the specific case of the 'sideMenu'
 */
function initHelpTextSideMenu() {

	//Defines the sizes for each button on the
	// side menu 
	hideCalSize = 45;
	googleLinkSize = 45;//Google link
	transSize = 45;// Transparency button
	elevSize = 45;// Elevation options
	paletteSize = 130;// Pallete button
	transectSize = 45;// Transect tool button
	downloadDataSize = 45;// Download data button
	helpSize = 60;// Help button
	mainmenuSize = 120; //Size of main menu TODO change depending on size
	optMenuSize = 140; //Size of optional menu TODO cahnge depending on size

	firstTopPos = 70;//Initial position for main menu

	offset = firstTopPos;
	var l1;
	var t1;

	l1 = getleft("mainMenuParent") - 215;
	t1 = getop("mainMenuParent");
	$("#mainMenuParentHover").css("top", t1 + "px");
	$("#mainMenuParentHover").css("left", l1 + "px");


	if (testVisibility("transParent")) {

		l1 = getleft("transParent") - 215;
		t1 = getop("transParent");
		$("#transParentHover").css("top", t1 + "px");
		$("#transParentHover").css("left", l1 + "px");
	}


	l1 = getleft("optionalMenuParent") - 220;
	t1 = getop("optionalMenuParent");
	$("#optionalMenuParentHover").css("top", t1 + "px");
	$("#optionalMenuParentHover").css("left", l1 + "px");

	if (testVisibility("elevationParent")) {
		var l, w, t;
		w = getwidth("elevationParent");
		l = getleft("elevationParent");
		l = l + w;
		t = getop("elevationParent");

		$("#elevationParentHover").css("top", t + "px");
		$("#elevationParentHover").css("left", l + "px");

	}
	if (testVisibility("transectParent")) {
		var l, w, t;
		w = getwidth("transectParent");
		l = getleft("transectParent");

		l = l + w;
		t = getop("transectParent");

		$("#transectParentHover").css("top", t + "px");
		$("#transectParentHover").css("left", l + "px");

	}
	if (testVisibility("palettesMenuParent")) {

		var l, w, t;

		w = getwidth("palettesMenuParent");
		l = getleft("palettesMenuParent");

		l = l + w;
		t = getop("palettesMenuParent");

		$("#palettesHover").css("top", t + "px");
		$("#palettesHover").css("left", l + "px");

	}
	if (testVisibility("hideCalendarButtonParent")) {
		var l, w, t;

		l = getleft("hideCalendarButtonParent");
		w = getwidth("hideCalendarButtonParent");

		l = l + w;
		t = getop("hideCalendarButtonParent");

		$("#hideCalendarHover").css("top", t + "px");
		$("#hideCalendarHover").css("left", l + "px");

	}
}

/**
 * This function enables or disables displaying the hover txts 
 * @param {img} img This is the image object that was pressed.
 * @param {string} path Is the path of the current application. 
 * @returns {undefined}
 */
function displayHoverHelp(img, path) {

	hoverInstructions('helpIconHover', '2');//Hides the text of the helpIcon
	hoverDisabled = !hoverDisabled;
	if (hoverDisabled) {
		img.src = path + "/common/images/Help/Help1Disabled.png";
	} else {
		img.src = path + "/common/images/Help/Help1.png";
	}
}