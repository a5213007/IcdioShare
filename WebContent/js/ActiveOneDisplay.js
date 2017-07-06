function loadActive(){
	isLogin();
	var activeId = GetRequest();
	
	if(activeId['id'] != undefined){
	
		$.ajax({
			type:'post',
			async:false,
			url:'../servlet/ActiveServlet',
			data:{
				'activeId' : activeId['id'] , 'info':'withEvaluation'
			},
			success:function(data){
				var info = eval(data);
				if(info != undefined && info != ""){
					for(var i = 0; i < info.length; i++){
						if(i == 0){
							$("#title").html(info[i]['title']);
							$("#type").html(info[i]['type']);
							$("#user").html("发布人：" + info[i]['name']);
							$("#releaseDate").html("发布时间：" + info[i]['releaseDate']);
							$("#content").html(info[i]['content']);
							$("#activePlace").html("活动地点：" + info[i]['activePlace']);
							$("#activeDate").html("活动时间：" + info[i]['activeDate']);
							
						}else {
							var display = '<div id="evaluation_' + info[i]['id'] + 
							'"><div class="evaluationBody"><div class="separate"></div>'+
							'<div class="evaluation"><div class="theEvaluation"><span'+
							' class="evaSpan">&nbsp;&nbsp;<span class="redTip">★</span>' +
							info[i]['evaluationContent'] + '</span></div><div class="theUser">'+
							'<span class="UserSpanFr">'+ info[i]['evalutionDate'] +
							'</span><span class="UserSpan">'+ info[i]['name'] + 
							'</span></div></div></div></div></div>';
													
							$("#allEvaluation").append(display);
						}
					}
					
				}
			},
			error:function(data){
				alert("服务器访问失败！");
			},
		});
	}else {
		alert("访问页面出错！");
	}
	
	resizeContent();
}

function submitEvaluation(){
	var activeId = GetRequest();
	var object = {};
	object['id'] = getId();
	object['telAndActID'] = activeId['id'];
	object['userID'] = eval(sessionStorage.user)[0]['id'];
	object['evaluationContent'] = $("#evaluationTextarea").val();
	object['evalutionDate'] = getNowTime();
	object['Type'] = 'active';
	
	if($("#evaluationTextarea").val().length != 0){
		$.ajax({
			type:'post',
			async:'false',
			url:'../servlet/EvaluationServlet',
			data:{
				'object': JSON.stringify(object),'info':'addEvaluation'
			},
			success:function(data){
				exitEvaluation();
				alert("评价成功！");
				window.location.reload();
				
			},
			error:function(data){
				alert("服务器访问失败！");
			},
		});
	}
	
}

function addEvaluation(){
	$("#cover").css("display", "block");
	$("#addEvaluation").css("display", "block");
	$("#addTextarea").val("");
}

function exitEvaluation(){
	$("#cover").css("display", "none");
	$("#addEvaluation").css("display", "none");
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
