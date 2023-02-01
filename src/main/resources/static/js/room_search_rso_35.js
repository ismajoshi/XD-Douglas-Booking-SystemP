"use strict";
/*
  Room Search Feature JavaScript Functions
  @author: Ricardo dos Santos Alves de Souza
 */
function enableDatetime () {
	$("#onlyAvailableChkBx").change(function() {
		
		if($("#onlyAvailableChkBx").prop("checked") == true) {
			
			$("#desiredStartDatetime").attr("readonly", false);
			$("#desiredEndDatetime").attr("readonly", false);					
		}
		else {
			
			$("#desiredStartDatetime").attr("readonly", true);
			$("#desiredEndDatetime").attr("readonly", true);				
		}
	});
};
