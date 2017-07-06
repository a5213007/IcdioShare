function getTextAreaRow(){
	isLogin();
	var id = GetRequest();
	
	if(id['id'] != undefined){
		$.ajax({
			type:'post',
			url:'../servlet/TechnologyServlet',
			async:false,
			data:{
				'id':id['id'], 'info':'withQuestionAndAnswer'
			},
			success:function(data){
				var info = eval(data);
				var ques = 0;
				
				if(info != undefined && info != ""){
					for(var i = 0; i < info.length; i ++){
						if(i == 0){
							$("#title").html(info[i]['title']);
							$("#user").html("分享者：" + info[i]['user']);
							$("#releaseDate").html("分享时间：" + info[i]['releaseDate']);
							$("#content").val(info[i]['content']);
						}else{
							if(info[i]['questionContent'] != undefined){
								var display = 
									'<div><div  id="question_' + info[i]['id'] + '" class="quesAndAnsw">'+
									'<div class="separate"></div><div class="question"><div class="theQuestion">'+
									'<span id="ques_' + info[i]['id'] + '" class="quesSpan">&nbsp;&nbsp;<span class="redTip">'+
									'★</span>提问：' + info[i]['questionContent'] + '</span></div><div class="theUser">'+
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

function submitQuestion(){
	var technologyId = GetRequest();
	var object = {};
	object['id'] = getId();
	object['telAndActID'] = technologyId['id'];
	object['questionContent'] = $("#questionTextarea").val();
	object['askDate'] = getNowTime();
	object['userID'] = eval(sessionStorage.user)[0]['id'];
	
	if($("#questionTextarea").val().length != 0){
		$.ajax({
			type:'post',
			async:false,
			url:'../servlet/QuesAndAnswServlet',
			data:{
				'type' : 'addQuestion', 'object':JSON.stringify(object) 
				
			},
			success:function(data){
				exitQuestion();
				alert("提问成功！");
				window.location.reload(); 
			},
			error:function(data){
				alert("服务器出错！");
			},
		});
	}else {
		alert("问题不能为空！");
	}
}

function submitAnswer(){
	var object = {};
	object['id'] = getId();
	object['questionID'] = sessionStorage.questionId;
	object['answerContent'] = $("#answerTextarea").val();
	object['answerDate'] = getNowTime();
	object['userID'] = eval(sessionStorage.user)[0]['id'];
	
	if($("#answerTextarea").val().length != 0){
		$.ajax({
			type:'post',
			async:false,
			url:'../servlet/QuesAndAnswServlet',
			data:{
				'type' : 'addAnswer', 'object':JSON.stringify(object) 
				
			},
			success:function(data){
				exitAnswer();
				alert("回答成功！");
				sessionStorage.removeItem('questionId');
				window.location.reload(); 
			},
			error:function(data){
				alert("服务器出错！");
			},
		});
	}else {
		alert("答案不能为空！");
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
	sessionStorage.questionId = id;
	$("#answersQues").html($("#ques_" + id).html().substring(44));
	$("#answersQues").attr('title', $("#ques_" + id).html().substring(44));
	$("#cover").css("display", "block");
	$("#addAnswer").css("display", "block");
	$("#answerTextarea").val("");
}

function exitAnswer(){
	$("#cover").css("display", "none");
	$("#addAnswer").css("display", "none");
}

function resizeContent(){
	var width = $(window).width();
	
	if(width < 1000 && width >= 870){
		var row = /*$("#content").val().toString().length / 30 + 1 +*/ getCount($("#content").val());
		if(row < 1)
			row = 1;
		$("#content").css("height",row * 32);
	}else if(width >= 1300 && width < 1600){
		var row = $("#content").val().toString().length / 80 + 2 + getCount($("#content").val()) * 0.9;
		if(row < 1)
			row = 1;
		$("#content").css("height",row * 32);
	}else if(width < 870 && width >= 770){
		var row = $("#content").val().toString().length / 50 + getCount($("#content").val());
		if(row < 1)
			row = 1;
		$("#content").css("height",row * 32);
	}else if(width < 1300 && width > 1000){
		var row = $("#content").val().toString().length / 65 + 2 + getCount($("#content").val()) * 0.86;
		if(row < 1)
			row = 1;
		$("#content").css("height",row * 35);
	}else if(width < 770 &&width >= 500 ){
		var row = /*$("#content").val().toString().length / 24 + 1 + */getCount($("#content").val());
		$("#content").css("height",row * 35);
	}else if(width > 320 && width < 440){
		var row = /*$("#content").val().toString().length / 12 + 1 + */getCount($("#content").val());
		if(row < 1)
			row = 1;
		$("#content").css("height",row * 35);
	}else if(width <= 1800 && width > 1600){
		var row = $("#content").val().toString().length / 90 + 2 +  getCount($("#content").val()) * 0.75;
		if(row < 1)
			row = 1;
		$("#content").css("height",row * 35);
	}else if( width > 1800){
		var row = $("#content").val().toString().length / 160 + 2 +  getCount($("#content").val()) * 0.85;
		if(row < 1)
			row = 1;
		$("#content").css("height",row * 35);
	}
}

