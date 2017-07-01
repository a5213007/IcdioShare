function getTextAreaRow(){
	var id = GetRequest();
	
	if(id['id'] != undefined){
		$.ajax({
			type:'post',
			url:'../servlet/QuesAndAnswServlet',
			async:false,
			data:{
				'id':id['id']
			},
			success:function(data){
				var info = eval(data);
				var ques = 0;
				
				if(info != undefined || info != ""){
					for(var i = 0; i < info.length; i ++){
						if(i == 0){
							$("#title").html(info[i]['title']);
							$("#user").html(info[i]['user']);
							$("#releaseDate").html(info[i]['releaseDate']);
							$("#content").val(info[i]['content']);
						}else{
							if(info[i]['questionContent'] != undefined){
								var display = 
									'<div><div  id="question_' + info[i]['id'] + '" class="quesAndAnsw">'+
									'<div class="separate"></div><div class="question"><div class="theQuestion">'+
									'<span  class="quesSpan">&nbsp;&nbsp;<span class="redTip">★</span>提问：' + 
									info[i]['questionContent'] + '</span></div><div class="theUser">'+
									'<span class="quesUserSpanFr">' + info[i]['askDate'] + '</span><span class="quesUserSpan">'+
									info[i]['name'] + '</span></div><div class="btAnswer"><span class="answer" ' + 
									'onclick="addAnswer(\'' + info[i]['id'] + '\')">我要回答</span></div></div></div></div>';
								
								$("#questionAndAnswer").append(display);								
							}else {
								var display = '<div class="question"><div class="theQuestion"><span class="quesSpan">'+
								'&nbsp;&nbsp;<span class="redTip">☆</span>答案：' + info[i]['answerContent'] + '</div>'+
								'<div class="theUser"><span class="quesUserSpanFr">' + info[i]['answerDate'] + '</span>'+
								'<span class="quesUserSpan">' + info[i]['name'] + '</span></div></div>';
								
								$("#question_" + info[i]['questionID']).append(display);
							}
						}
					}
				}
				
			},
			error:function(data){
				
			},
		});
		resizeContent();
	}	
}

$(window).resize(function(){
	resizeContent();
		
});

function addQuestion(){
	$("#cover").css("display", "block");
	$("#addQuestion").css("display", "block");
	$("#questionTextarea").val("");
}

function exitQuestion(){
	$("#cover").css("display", "none");
	$("#addQuestion").css("display", "none");
}

function addAnswer(id){
	$("#cover").css("display", "block");
	$("#addAnswer").css("display", "block");
	$("#answerTextarea").val("");
}

function exitAnswer(){
	$("#cover").css("display", "none");
	$("#addAnswer").css("display", "none");
}

function reManager(){
	window.location.href = "ManagerIndex.html?activeIndex=1&technologyIndex=1";
}

function exit(){
	window.location.href = "index.html";
}

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