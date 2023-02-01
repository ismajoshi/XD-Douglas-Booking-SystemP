"use strict";
/*
  Room Detaild and Schedule Report Feature JavaScript Functions
  @author: Ricardo dos Santos Alves de Souza
 */

function printRoomDetailScheduleReport() {
	
	//Set the Div ID
	var divName = "roomDetailScheduleReport_Report_Section";
	
	//Open a new Window
	var newWin = window.open("","DBS - Room Detail and Schedule Report", "width=800,height=500,scrollbars=1,resizable=1");

	//Get the current page entire code
    var originalContents = document.documentElement.innerHTML;
	
	//Get the only the section code
    var divContents = document.getElementById(divName).innerHTML;

	//Set the complete code to new Window document
	newWin.document.documentElement.innerHTML = originalContents;
	
	//Replace the div content in the new Window body
	newWin.document.body.innerHTML = divContents;
	
	//Change focus and call print
	newWin.focus();
	newWin.print();
	//Close New Window
	//newWin.close();


/*

	//Change the page content to have only the desired section
    document.body.innerHTML = divContents;

	//Open a new window with the section content

	//Call print function
	window.print();

	//Return the complete content to the page
	document.body.innerHTML = originalContents;
	
	*/
};