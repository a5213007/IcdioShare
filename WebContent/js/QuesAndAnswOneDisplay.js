function getTextAreaRow(){
	resizeContent();
}

$(window).resize(function(){
	resizeContent();
		
});

function resizeContent(){
	var width = $(window).width();
	if(width < 1000 && width >= 870){
		var row = $("#content").val().toString().length / 30 + 1;
		$("#content").css("height",row * 32);
	}else if(width >= 1300 && width < 1600){
		var row = $("#content").val().toString().length / 40 + 1;
		$("#content").css("height",row * 32);
	}else if(width < 870 && width >= 770){
		var row = $("#content").val().toString().length / 26 + 1;
		$("#content").css("height",row * 32);
	}else if(width < 1300 && width > 1000){
		var row = $("#content").val().toString().length / 38 + 1;
		$("#content").css("height",row * 35);
	}else if(width < 770 &&width >= 500 ){
		var row = $("#content").val().toString().length / 24 + 1;
		$("#content").css("height",row * 35);
	}else if(width > 320 && width < 440){
		var row = $("#content").val().toString().length / 12 + 1;
		$("#content").css("height",row * 35);
	}else if(width <= 1920 && width > 1800){
		var row = $("#content").val().toString().length / 64 + 1;
		$("#content").css("height",row * 35);
	}else if(width <= 1800 && width > 1600){
		var row = $("#content").val().toString().length / 54 + 1;
		$("#content").css("height",row * 35);
	}
}