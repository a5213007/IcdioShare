function loadBackManagement(){
	isLogin();
	var url = GetRequest();
	
	if(url['block'] != undefined){
		if(url['block'] == 'main')
			return;
		
		$('#displayModel').css('display','block');
		$('#title').html(control2[url['block']]);
		$('#title').css('display','block');
		
		if(url['block'] == "Permissions" || url['block'] == "Role" || url['block'] == "User" ){
			$("#btAddInfo").css('display','block');
		}else {
			$("#btAddInfo").css('display','none');
		}
		setFindBt(url['block']);
		getInfo();
		
	}else {
		alert("路径出错！");
		reManager();
	}
	
}
function setFindBt(type){
	for(var i = 0; i < tableTh2[type].length - 1;i++)
		$('#findSel').append('<option value="'+tableTh[type][i] + '">'+ tableTh2[type][i] +'</option>');
}

function getInfo(){
	var url = GetRequest();
	
	if(url['page'] != undefined && url['block'] != undefined){
		$.ajax({
			type:'post',
			async:false,
			url:'../servlet/'+ url['block'] + 'Servlet',
			data:{
				'info':'display', 'page': url['page'], 'userId' : eval(sessionStorage.user)[0]['id'],
			},
			success:function(data){
				var info = eval(data);				
				if(info != undefined && info != ""){
					$("#table").remove();				
					displayTable(info);				
				}
			},
			error:function(data){
				alert("服务器访问失败！");
			},
		})
	}
	
}

function displayTable(info){
	var url = GetRequest();
	var display = '<table id="table" class="table" cellspacing="0" cellpadding="5"><tr class="controlTh controlTh2">';
	
	for(var j = 0; j < tableTh[url['block']].length; j++)
		display += '<td>'+tableTh2[url['block']][j]+'</td>';
	
	display +='</tr>';
	for(var i = 0; i < info.length; i++){
		
		if(info[i]['id'] != undefined){
			if(i % 2 == 1)
				display += "<tr class='otherTr'>";
			else 
				display += "<tr>";
			
			for(var j = 0; j < tableTh[url['block']].length; j++){
				if(j == (tableTh[url['block']].length - 1)){
					display += '<td class="controlTh">' + getOperate(url['block'] + 'Ctr', info[i]) + '</td>';
				}else {
					display += "<td>" + isNull(info[i][tableTh[url['block']][j]]) + '</td>';
				}
			}
			
			display += "</tr>";
		}else {
			$('#nowPage').html(url['page']);
			$('#allPage').html(Math.ceil(info[i]['page']));
		}
	}
	
	display += '</table>'
	$('#displayTable').append(display);
}

function isNull(info){
	if(info == null)
		return "";
	return info;
}

function getOperate(type, info){
	var display = "";
		
	if(type == "ProcessCtr" && hasPerssions(type)){
		if(info['state'] == "已提交"){
			display += '<span class="control">同意</span>';
			display += '<span class="control">驳回</span>';
		}else {
			display += '<span class="control">删除</span>';
		}
		return display;
	}
	
	if(hasPerssions(type) || hasPerssions(type +"_display"))
		display += '<span class="control">查看</span>';
	if(type != "QuestionCtr" && type != "AnswerCtr" && type != "Evaluation"){
		if(hasPerssions(type) || hasPerssions(type +"_edit"))
			display += '<span class="control">编辑</span>';
	}
	
	if(hasPerssions(type) || hasPerssions(type +"_delete"))
		display += '<span class="control">删除</span>';
	
	if(type == "TechnologyCtr" || type == "ActiveCtr"){ 
		if((hasPerssions(type) || (eval(sessionStorage.user)[0]['id'] == info['userID']))
				&& info['state'] == '已提交'){
			display += '<span class="control">撤回</span>';
		}else if((hasPerssions(type) || (eval(sessionStorage.user)[0]['id'] == info['userID']))
				&& (info['state'] == '已撤回' || info['state'] == '已驳回')){
			display += '<span class="control">提交</span>';
		}
	}
	return display;
}

function goBlock(tip){
	window.location.href = "BackManagement.html?page=1&block=" + control[tip];
}
function Return(){
	window.location.href = "ManagerIndex.html?activeIndex=1&technologyIndex=1";
}
function goMain() {
	window.location.href = "BackManagement.html?block=main";
}








