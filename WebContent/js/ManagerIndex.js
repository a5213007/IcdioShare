function loadManager(){
	if(sessionStorage.user == undefined){
		
	}
	
	var user = eval(sessionStorage.user);
}

function closeTechnology(){
	$("#cover").css('display','none');
	$("#technologyCover").css('display','none');
	$("#technology").css('display','none');
}

function openTechnology(){
	ini('tey');
	$("#cover").css('display','block');
	$("#technologyCover").css('display','block');
	$("#technology").css('display','block');
}

function closeActive(){
	$("#cover").css('display','none');
	$("#activeCover").css('display','none');
	$("#active").css('display','none');
}

function openActive(){
	ini('active');
	$("#cover").css('display','block');
	$("#activeCover").css('display','block');
	$("#active").css('display','block');
}

function ini(type){
	if(type == 'tey'){
		$("#teyFile").val(""); 
		$("#teyTitle").val("");
		$("#teyContent").val("");
		$("#fileTip").html("");
	}else {
		$("#actTitle").val("");
		$("#actContent").val("");
		$("#actTime").val("");
		$("#actPlace").val("");
		$("#actType option:first").prop("selected", 'selected');
	}	
}

function submitTechnology(){
	
}

