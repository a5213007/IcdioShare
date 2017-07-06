function loadBackManagement(){
	isLogin();
	var url = GetRequest();
	
	$('#userName').html(eval(sessionStorage.user)[0]['name']);

	if(url['block'] != undefined){
		getPermissions();
		setItem();
		
				
		if(url['block'] == 'main')
			return;
		else {
			if(!canOpenThisItem(url['block'])){
				alert("您无打开的"+control2[url['block']]+"的权限！");
				return ;
			}
		}
		
		sessionStorage.removeItem('permissions');
		$('#title').html(control2[url['block']]);
		$('#title').css('display','block');
		setFindBt(url['block']);
		if(url['block'] == "Permissions" || url['block'] == "Role" || url['block'] == "User" ){
			$("#btAddInfo").css('display','block');
		}else {
			$("#btAddInfo").css('display','none');
		}
		
		if (url['type'] == "add") {
			$('#addEditModel').css('display','block');
			$('#displayModel').css('display','none');
			getColumnInfo(url['block']);
			setAddModel(url['block']);
			return;
		}else if(url['type'] == 'edit'){
			$('#addEditModel').css('display','block');
			$('#displayModel').css('display','none');
			getColumnInfo(url['block']);
			setEditModel(url['block'], url['id']);
			return ;
		}else if(url['type'] == 'find'){
			find();
			$('#displayModel').css('display','block');
			$('#addEditModel').css('display','none');
			return;
		}else if(url['type'] == 'display') {
			$('#addEditModel').css('display','block');
			$('#displayModel').css('display','none');
			getColumnInfo(url['block']);
			setDisplaytModel(url['block'], url['id']);
			return ;
		}

		$('#displayModel').css('display','block');
		displayInfo();
		
	}else {
		alert("路径出错！");
		reManager();
	}	
}

/**
 * 
 * */
function canOpenThisItem(type){
	if(hasPerssions(type + 'Ctr') || hasPerssions(type + '_display')){
		return true;
	}else if(type == "Evaluation" || type == "Answer" || type == "Technology" || type == "Question"){
		return true;
	}
	return false;
}

/**
 * 根据权限展示左边模块按钮
 * */
function setItem(){
	if(isDisplay('PermissionsCtr') && isDisplay('PermissionsCtr_display'))
		$("#permissionsCtr").remove();
	if(isDisplay('UserCtr') && isDisplay('UserCtr_display'))
		$("#userCtr").remove();
	if(isDisplay('RoleCtr') && isDisplay('RoleCtr_display'))
		$("#roleCtr").remove();
	if(isDisplay('ProcessCtr') && isDisplay('ProcessCtr_display'))
		$("#processCtr").remove();
	if(isDisplay('ActiveCtr') && isDisplay('ActiveCtr_display'))
		$("#activeCtr").remove();
	$("div.item").css('display','block');
}

/**
 * 是否展示
 * */
function isDisplay(type){
	if(eval(sessionStorage.permissions) == undefined || sessionStorage.permissions == "[]")
		return true;
	
	var perssions = eval(sessionStorage.permissions)[0]['sign'];
	
	for(var i = 0; i < perssions.length; i++){
		if(type == perssions[i])
			return false;
	}
	return true;
}

/**
 * 设置查找下拉菜单的值
 * */
function setFindBt(type){
	for(var i = 0; i < tableTh2[type].length - 1;i++)
		$('#findSel').append('<option value="'+tableTh[type][i] + '">'+ tableTh2[type][i] +'</option>');
}

/**
 * 初始化新增模板
 * */
function setAddModel(type){
	var column = eval(sessionStorage.column);
	
	for(var i = 0, j = 0; i < column.length; i++){
		if(column[i]['name'] == 'id')
			continue;
		else{
			if(column[i]['length'] < 100){
				var index = (j % 2 == 0 ? 1 : 2);
				j++;
				var display = '<div class="addInput'+index+'"><label class="lab'+index+'">'+column[i]['chineseName']+'</label>'+
				'<input class="input'+index+'" type="text" id="addModel_'+column[i]['name']+'" />'+
				'<div class="tip'+index+'"></div></div>';
				$("#addEdit").append(display);
			}
		} 
			
	}
	var dis = '<div class="tail"><button class="btSure" onclick="submitAdd()">确定</button><button class="btRe" onclick="addReBt(\''+type+'\')">返回</button>'+
			'</div>'
	$("#addEdit").append(dis);
	
}

/**
 * 初始化编辑模板
 * */
function setEditModel(type, infoId){
	var column = eval(sessionStorage.column);
	
	for(var i = 0; i < column.length; i++){
		var index = (i % 2 == 0 ? 1 : 2);
		if(column[i]['name'].endsWith('content') || column[i]['name'].endsWith('Content')){
			var display = '<div class="TextDiv"><label class="labText">'+column[i]['chineseName']+'</label>'+
			'<textarea class="inputText" id="addModel_'+column[i]['name']+'" ></textarea>'+
			'<div class="tip'+index+'"></div></div>';
			$("#addEdit").append(display);
					
		} else {
			var date = "";
			
			if(column[i]['name'].endsWith('Date'))
				date = 'onclick="laydate({elem: \'#addModel_' +column[i]['name'] + '\'});"'
				
			var display = '<div class="addInput'+index+'"><label class="lab'+index+'">'+column[i]['chineseName']+'</label>'+
			'<input class="input'+index+'" type="text" id="addModel_'+column[i]['name']+'" '+date+'/>'+
			'<div class="tip'+index+'"></div></div>';
			$("#addEdit").append(display);
			
		}
			
	}
	var dis = '<div class="tail"><button class="btSure" onclick="submitEdit()">确定</button><button class="btRe" onclick="addReBt(\''+type+'\')">返回</button>'+
			'</div>'
	$("#addEdit").append(dis);
	restoreEdit(type, infoId);
	
}

/**
 * 还原用于编辑的数据
 * */
function restoreEdit(type, infoId){
	var column = eval(sessionStorage.column);
	if(type == "Active" || type == "Technology")
		type = "Tel_And_Act";
	
	$.ajax({
		type:'post',
		url:'../servlet/CommonOperateServlet',
		async:false,
		data:{
			'info':'restore','id': infoId, 'className':type,
		},
		success:function(data){
			var info = eval(data);
			
			if(data != undefined){
				for(var i = 0; i < column.length; i++){
					if(column[i]['editAble'] == undefined || column[i]['editAble'] == true);
					else {
						$('#addModel_' + column[i]['name']).attr("disabled","false");
						$('#addModel_' + column[i]['name']).attr("onclick","");
					}
					$('#addModel_' + column[i]['name']).val(info[0][column[i]['name']]);
				}
			}
		},
		error:function(data){
			
		},
	})
}

/**
 * 初始化查看模板
 * */
function setDisplaytModel(type, infoId){
	var column = eval(sessionStorage.column);
	
	for(var i = 0; i < column.length; i++){
		var index = (i % 2 == 0 ? 1 : 2);
		if(column[i]['name'].endsWith('content') || column[i]['name'].endsWith('Content')){
			var display = '<div class="TextDiv"><label class="labText">'+column[i]['chineseName']+'</label>'+
			'<textarea class="inputText" id="addModel_'+column[i]['name']+'" ></textarea>'+
			'<div class="tip'+index+'"></div></div>';
			$("#addEdit").append(display);
					
		} else {
			var display = '<div class="addInput'+index+'"><label class="lab'+index+'">'+column[i]['chineseName']+'</label>'+
			'<input class="input'+index+'" type="text" id="addModel_'+column[i]['name']+'" />'+
			'<div class="tip'+index+'"></div></div>';
			$("#addEdit").append(display);
			
		}
			
	}
	var dis = '<div class="tail"><button class="btRe" onclick="addReBt(\''+type+'\')">返回</button>'+
			'</div>'
	$("#addEdit").append(dis);
	restoreDisplay(type, infoId);
	
}

/**
 * 还原用于查看的数据
 * */
function restoreDisplay(type, infoId){
	var column = eval(sessionStorage.column);
	if(type == "Active" || type == "Technology")
		type = "Tel_And_Act";
	
	$.ajax({
		type:'post',
		url:'../servlet/CommonOperateServlet',
		async:false,
		data:{
			'info':'restore','id': infoId, 'className':type,
		},
		success:function(data){
			var info = eval(data);
			
			if(data != undefined){
				for(var i = 0; i < column.length; i++){
					$('#addModel_' + column[i]['name']).attr("disabled","false");
					$('#addModel_' + column[i]['name']).addClass('displayCantEdit');
					$('#addModel_' + column[i]['name']).val(info[0][column[i]['name']]);
				}
			}
		},
		error:function(data){
			
		},
	})
}


/**
 * 获取编辑、新增、查看的实体属性
 * */
function getColumnInfo(block){
	if(block == "Active" || block == "Technology")
		block = "Tel_And_Act";
	
	$.ajax({
		type:'post',
		url:'../servlet/CommonOperateServlet',
		async:false,
		data:{
			'info':'columnInfo', 'class':block
		},
		success:function(data){
			var info = eval(data);
			if(info != null && info != ""){
				sessionStorage.column = JSON.stringify(info);
			}
		},
		error:function(data){
			alert("服务器访问失败！");
		},
	})
}

/**
 * 模块数据表格展示
 * */
function displayInfo(){
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
					getPermissions();
					displayTable(info);	
					sessionStorage.removeItem('permissions');
				}
			},
			error:function(data){
				alert("服务器访问失败！");
			},
		});
	}
	
}

/**
 * 展示表格
 * */
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
					display += "<td title='"+isNull(info[i][tableTh[url['block']][j]])+"'>"+isNull(info[i][tableTh[url['block']][j]]) + '</td>';
				}
			}
			
			display += "</tr>";
		}else {
			$('#nowPage').html(url['page']);
			var url = GetRequest();
			if(Math.ceil(info[i]['page']) == 0 || Math.ceil(info[i]['page']) == 1) {
				$('#allPage').html('1');
				sessionStorage.backPages = 1;
				addClasses();
			}
			else if(parseInt(url['page'])  > 1 && parseInt(url['page']) < Math.ceil(info[i]['page'])){
				$('#allPage').html(Math.ceil(info[i]['page']));
				sessionStorage.backPages = Math.ceil(info[i]['page']);
				removeClasses();
			}
			else if(parseInt(url['page']) == 1 && parseInt(url['page']) < Math.ceil(info[i]['page'])) {
				$('#allPage').html(Math.ceil(info[i]['page']));
				sessionStorage.backPages = Math.ceil(info[i]['page']);
				aheadAddClasses();
			}
			else {
				$('#allPage').html(Math.ceil(info[i]['page']));
				sessionStorage.backPages = Math.ceil(info[i]['page']);
				backAddClasses();
			}
		}
	}
	
	display += '</table>'
	$('#displayTable').append(display);
}
/**
 * 数据为null的不展示
 * */
function isNull(info){
	if(info == null)
		return "";
	return info;
}

function addClasses() { //全部不能点
	$('#bcakFirstPg').addClass('cantClick');
	$('#bcakPreviousPg').addClass('cantClick');
	$('#bcakNextPg').addClass('cantClick');
	$('#bcakLastPg').addClass('cantClick');
	$('#bcakFirstPg').removeClass('canClick');
	$('#bcakPreviousPg').removeClass('canClick');
	$('#bcakNextPg').removeClass('canClick');
	$('#bcakLastPg').removeClass('canClick');
}

function removeClasses() { //全部能点
	$('#bcakFirstPg').addClass('canClick');
	$('#bcakPreviousPg').addClass('canClick');
	$('#bcakNextPg').addClass('canClick');
	$('#bcakLastPg').addClass('canClick');
	$('#bcakFirstPg').removeClass('cantClick');
	$('#bcakPreviousPg').removeClass('cantClick');
	$('#bcakNextPg').removeClass('cantClick');
	$('#bcakLastPg').removeClass('cantClick');
}

function aheadAddClasses() { //左边不能点
	$('#bcakFirstPg').removeClass('canClick');
	$('#bcakPreviousPg').removeClass('canClick');
	$('#bcakFirstPg').addClass('cantClick');
	$('#bcakPreviousPg').addClass('cantClick');
	$('#bcakNextPg').removeClass('cantClick');
	$('#bcakLastPg').removeClass('cantClick');
	$('#bcakNextPg').addClass('canClick');
	$('#bcakLastPg').addClass('canClick');
}

function backAddClasses() { //右边不能点
	$('#bcakFirstPg').removeClass('cantClick');
	$('#bcakPreviousPg').removeClass('cantClick');
	$('#bcakFirstPg').addClass('canClick');
	$('#bcakPreviousPg').addClass('canClick');
	$('#bcakNextPg').removeClass('canClick');
	$('#bcakLastPg').removeClass('canClick');
	$('#bcakNextPg').addClass('cantClick');
	$('#bcakLastPg').addClass('cantClick');

}

/**
 * 展示表格操作列展示
 * */
function getOperate(type, info){
	var display = "";
		
	if(type == "ProcessCtr" && hasPerssions(type)){
		if(info['state'] == "已提交"){
			display += '<span class="control" onclick="processCtr(\'true\',\''+info['id']+'\')">同意</span>';
			display += '<span class="control" onclick="processCtr(\'false\',\''+info['id']+'\')">驳回</span>';
		}else {
			display += '<span class="control" onclick="del(\''+type+'\',\''+info['id']+'\')">删除</span>';
		}
		return display;
	}
	
	if((hasPerssions(type) || hasPerssions(type +"_display")) || isTheBlock(type))
		display += '<span class="control" onclick="display(' + info['id'] + ')">查看</span>';
	if(type != "QuestionCtr" && type != "AnswerCtr" && type != "Evaluation"){
		if((hasPerssions(type) || hasPerssions(type +"_edit")) || isTheBlock(type))
			display += '<span class="control" onclick="edit('+info['id']+')">编辑</span>';
	}
	
	if((hasPerssions(type) || hasPerssions(type +"_delete")) || isTheBlock(type))
		display += '<span class="control" onclick="del(\''+type+'\',\''+info['id']+'\')">删除</span>';
	
	if(type == "TechnologyCtr" || type == "ActiveCtr"){ 
		if((hasPerssions(type) || (eval(sessionStorage.user)[0]['id'] == info['userID']))
				&& (info['state'] == '已提交' || info['state'] == '已同意')){
			display += '<span class="control" onclick="changeState(\'撤回\',\''+info['id']+'\',\''+info['type']+'\')">撤回</span>';
		}else if((hasPerssions(type) || (eval(sessionStorage.user)[0]['id'] == info['userID']))
				&& (info['state'] == '已撤回' || info['state'] == '已驳回')){
			display += '<span class="control" onclick="changeState(\'提交\',\''+info['id']+'\',\''+info['type']+'\')">提交</span>';
		}
	}else if(type == 'UserCtr' && hasPerssions('PermissionsCtr')){
		display += '<span class="control" onclick="Assign(\''+info['id']+'\',\'Role\',\'选择角色('+info['name']+')\')">分配角色</span>';
	}else if(type == 'RoleCtr' && hasPerssions('PermissionsCtr')){
		display += '<span class="control" onclick="Assign(\''+info['id']+'\',\'Permissions\',\'选择权限('+info['roleName']+')\')">分配权限</span>';
	}
	
	return display;
}

function isTheBlock(type){
	if(type == "RoleCtr" || type == "UserCtr" || type == "PermissionsCtr" || type == "ProcessCtr")
		return false;
	return true;
}

/**
 * 流程审批
 * */
function processCtr(tip, id){
	var object = {};
	object['processId'] = id;
	object['userId'] = eval(sessionStorage.user)[0]['id'];
	object['reviewDate'] = getNowTime();
	
	if(confirm("是否进行审批？")){
		$.ajax({
			type:'post',
			async:false,
			url:'../servlet/ProcessServlet',
			data:{
				'info':'processCtr', 'judge':tip,'object':JSON.stringify(object)
			},
			success:function(data){
				alert("审批成功！");
				window.location.reload();
			},
			error:function(data){
				
			},
		});
		
	}
}
/**
 * 退出权限分配、角色分配
 * */

function quitRolePer(){
	$('#selBody').empty();
	$('#btSetTail').empty();
	$('#cover').css('display','none');
	$('#sel').css('display','none');
}

/**
 * 分配权限、分配角色点击
 * */
function Assign(id, type, title){
	
	var tip = type.toLowerCase();
	
	if(tip == "permissions")
		tip = "perssions";
	
	$('#selTitle').html(title);
	
	$.ajax({
		type:'post',
		async:false,
		url:'../servlet/'+type+'Servlet',
		data:{
			'info':'getAll'+type
		},
		success:function(data){
			var info = eval(data);
			
			if(info != null && info.length != 0){
				for(var i = 0; i < info.length; i++){
					var display = '<div class="itemSel"><input id="check_'+info[i]['id']+'" class="checkbox" type="checkbox" name="aa" value="'+info[i]['id']+'"/>'+
						'<span class="itemSpan" title="">'+ info[i][tip + 'Name']+'</span></div>';
					
					$('#selBody').append(display);
				}
			}
			var display = '<button class="selSure" onclick="addSelTo(\''+id+'\',\''+type+'\')">确定</button>'+
			'<button class="selQuit" onclick="quitRolePer()">取消</button>';
			
			getSel(id,type);
			
			$('#btSetTail').append(display);
			$('#cover').css('display','block');
			$('#sel').css('display','block');
		},
		error:function(data){
			alert('服务器访问失败！');
		},
	});
}

/**
 * 展示还原权限、角色
 * */
function getSel(id, type){
	var tip = type.toLowerCase();	
		
	$.ajax({
		type:'post',
		async:false,
		url:'../servlet/'+type+'Servlet',
		data:{
			'info':'get'+type+'ById', 'id':id
		},
		success:function(data){
			var info = eval(data);
			
			if(info != undefined && info.length != 0){
				for(var i = 0; i < info[0][tip].length; i++){
					$('#check_' + info[0][tip][i]).attr("checked", true);
				}
			}
		},
		error:function(data){
			alert('服务器访问失败！');
		},
	});
		
}
/**
 * 添加权限、角色
 * */
function addSelTo(id, type){
	 var checkbox = $("input[type='checkbox']:checked");
	 
	 removeAllSel(id, type);
	 if(checkbox.length == 0){
		 quitRolePer();
		 return;
	 }
	 
	 var object = {};
	 
	 for(var i = 0; i < checkbox.length; i++){
		 object[i] = checkbox[i].value;
	 }

	$.ajax({
		type:'post',
		async:false,
		url:'../servlet/'+type+'Servlet',
		data:{
			'info':'add'+type+'To', 'id':id, 'object': JSON.stringify(object)
		},
		success:function(data){
			alert('添加成功！');					
			quitRolePer();
			window.location.reload();
		},
		error:function(data){
			alert('服务器访问失败！');
		},
	});
}

/**
 * 清除所有权限
 * */
function removeAllSel(id, type){
	$.ajax({
		type:'post',
		async:false,
		url:'../servlet/'+type+'Servlet',
		data:{
			'info':'removeAll', 'id':id
		},
		success:function(data){			
		},
		error:function(data){
			alert('服务器访问失败！');
		},
	});
}

/**
 * 删除按钮
 * */
function del(type, id){
	if(confirm("确定删除？")){
		if(type == "ActiveCtr" || type == "TechnologyCtr")
			type = "Tel_And_ActCtr";
		$.ajax({
			type:'post',
			url:'../servlet/CommonOperateServlet',
			aysnc:false,
			data:{
				'info':'delete', 'id':id, 'className':type.substring(0, type.length - 3)
			},
			success:function(data){
				var url = GetRequest();
				window.location.href = "BackManagement.html?page=1&block=" + url['block'];
				alert("删除成功！");
			},
			error:function(data){
				alert("服务器访问失败！");
			},
		});
	}
}


/**
 * 模块返回点击
 * */
function addReBt(tip){
	window.location.href = "BackManagement.html?page=1&block=" + tip;
}

/**
 * 模块查询按钮点击
 * */
function loadFind(){
	if($('#findSel').val() == '' || $('#findSel').val() == '请选择') {
		alert('下拉框未选择！');
		return;
		}
	if($('#findbyKeyAndValue').val() == '') {
		alert('请输入查找信息！');
		return;
	}
	var url = GetRequest();
	window.location.href = "BackManagement.html?page=1&block=" + url['block'] + "&type=find&key=" + $('#findSel').val() + "&value=" + encodeUnicode($('#findbyKeyAndValue').val());
}
/**
 * 查询页面加载
 * */
function find() {
	var url = GetRequest();
	$.ajax({
		type:'post',
		url:'../servlet/'+ url['block'] + 'Servlet',
		aysnc:false,
		data:{
			'info':'find', 'key':url['key'], 'value':decodeUnicode(url['value']), 'page':url['page'],'type':url['block'], 'id' :eval(sessionStorage.user)[0]['id'] 
		},
		success:function(data){
			var info = eval(data);
			getPermissions();
			displayTable(info);	
			sessionStorage.removeItem('permissions');	
		},
		error:function(data){
			alert("服务器访问失败！");
		},
	});
}

/**
 * 模块点击事件
 * */
function goBlock(tip){
	window.location.href = "BackManagement.html?page=1&block=" + control[tip];
}

/**
 * 回首页按钮
 * */
function Return(){
	window.location.href = "ManagerIndex.html?activeIndex=1&technologyIndex=1";
}

/**
 * 后台展示首页
 * */
function goMain() {
	window.location.href = "BackManagement.html?block=main";
}

/**
 * 表格展示首页点击
 * */
function backGoFirstPg (){
	var url = GetRequest();
	var pages = sessionStorage.backPages;
	if(parseInt($('#nowPage').html()) <= pages && parseInt(url['page']) > 1 && parseInt($('#nowPage').html()) != 1) {
		window.location.href = "BackManagement.html?page=1&block=" + url['block'];
	}
}

/**
 * 表格展示上一页点击
 * */
function backGoPreviousPg (){
	var url = GetRequest();
	var pages = sessionStorage.backPages;
	if(parseInt($('#nowPage').html()) <= pages && (parseInt(url['page']) - 1) > 0 && (parseInt(url['page']) - 1) < pages) {
		window.location.href = "BackManagement.html?page=" + (parseInt(url['page']) - 1) + "&block=" + url['block'];
	}
}

/**
 * 表格展示下一页点击
 * */
function backGoNextPg (){
	var url = GetRequest();
	var pages = sessionStorage.backPages;
	if(parseInt($('#nowPage').html()) < pages && (parseInt(url['page']) + 1) <= pages && (parseInt(url['page']) + 1) >=2) {
		window.location.href = "BackManagement.html?page=" + (parseInt(url['page']) + 1) + "&block=" + url['block'];
	}
}

/**
 * 表格展示尾页点击
 * */
function backGoLastPg (){
	var url = GetRequest();
	var pages = sessionStorage.backPages;
	if(parseInt($('#nowPage').html()) < pages && parseInt(url['page']) > 0 && parseInt($('#nowPage').html()) != pages) {
		window.location.href = "BackManagement.html?page=" + pages + "&block=" + url['block'];
	}
}

/**
 * 模块新增按钮
 * */
function add() {
	var url = GetRequest();
	window.location.href = "BackManagement.html?block=" + url['block'] + "&type=add";
}

/**
 * 编辑标签
 * */
function edit(id) {
	var url = GetRequest();
	window.location.href = "BackManagement.html?block=" + url['block'] + "&type=edit&id=" + id;
}

/**
 * 查看标签
 * */
function display(id) {
	var url = GetRequest();
	window.location.href = "BackManagement.html?block=" + url['block'] + "&type=display&id=" + id;
}

/**
 * 新增按钮确定
 * */
function submitAdd(){
	if(!yanzhen2())
		return ;
	
	var column = eval(sessionStorage.column);
	var url = GetRequest();
	
	var object = {};
	for(var i = 0; i < column.length; i ++)
		if(column[i]['name'] != 'id')
			object[column[i]['name']] = $('#addModel_' + column[i]['name']).val();
	object['id'] = getId();
	
	var type = url['block'];
	if(type == "Active" || type == "Technology")
		type = "Tel_And_Act";
	
	$.ajax({
		type:'post',
		url:'../servlet/CommonOperateServlet',
		aysnc:false,
		data:{
			'info':'add','object': JSON.stringify(object), 'className': type
		},
		success:function(data){
			alert('新增成功！');
			addReBt(url['block']);
		},
		error:function(data){
			alert('服务器访问失败！');
		},
	})
}

/**
 * 编辑按钮确定
 * */
function submitEdit(){
	if(!yanzhen())
		return ;
	
	var column = eval(sessionStorage.column);
	var url = GetRequest();
	var object = {};
	for(var i = 0; i < column.length; i ++)
		object[column[i]['name']] = $('#addModel_' + column[i]['name']).val();
	
	var type = url['block'];
	if(type == "Active" || type == "Technology")
		type = "Tel_And_Act";
	
	$.ajax({
		type:'post',
		url:'../servlet/CommonOperateServlet',
		aysnc:false,
		data:{
			'info':'edit','object': JSON.stringify(object), 'className': type
		},
		success:function(data){
			alert('编辑成功！');
			addReBt(url['block']);
		},
		error:function(data){
			alert('服务器访问失败！');
		},
	})

}

/**
 * 编辑验证
 * */
function yanzhen(){
	var column = eval(sessionStorage.column);
	
	for(var i = 0; i < column.length; i ++){
		if(column[i]['nullAble'] == false){
			if($('#addModel_' + column[i]['name']).val() == ""){
				alert(column[i]['chineseName'] + '不能为空！');
				return false;
			}else if(column[i]['length'] != -1 && $('#addModel_' + column[i]['name']).val().length > column[i]['length']){
				alert(column[i]['chineseName'] + '最大长度为：' + column[i]['length']);
				return false;
			}			
		}else {
			if(column[i]['length'] != -1 && $('#addModel_' + column[i]['name']).val().length > column[i]['length']){
				alert(column[i]['chineseName'] + '最大长度为：' + column[i]['length']);
				return false;
			}	
		}
	}
	return true;
}
/**
 * 新增验证
 * */
function yanzhen2(){
	var column = eval(sessionStorage.column);
	
	for(var i = 0; i < column.length; i ++){
		if(column[i]['name'] == 'id')
			continue;
		
		if(column[i]['nullAble'] == false){
			if($('#addModel_' + column[i]['name']).val() == ""){
				alert(column[i]['chineseName'] + '不能为空！');
				return false;
			}else if(column[i]['length'] != -1 && $('#addModel_' + column[i]['name']).val().length > column[i]['length']){
				alert(column[i]['chineseName'] + '最大长度为：' + column[i]['length']);
				return false;
			}			
		}else {
			if(column[i]['length'] != -1 && $('#addModel_' + column[i]['name']).val().length > column[i]['length']){
				alert(column[i]['chineseName'] + '最大长度为：' + column[i]['length']);
				return false;
			}	
		}
	}
	return true;
}
/**
 * 撤回提交
 * */

function changeState(state, id, type){
	if(confirm("确定" + state + "?")){
		var object = {};
		if(type == "活动通知" && state == "提交"){
			object['id'] = getId();
			object['releaseDate'] = getNowTime();
			object['userID'] = eval(sessionStorage.user)[0]['id'];
			object['telAndActID'] = id;
			object['state'] = "已提交"; 
		}
			
		
		$.ajax({
			type:'post',
			async:false,
			url:'../servlet/CommonOperateServlet',
			data:{
				'info':'changeState', 'id':id, 'state':'已' + state, 'type':type, 'object':JSON.stringify(object)
			},
			success:function(data){
				alert(state + "成功！");
				window.location.reload();
			},
			error:function(data){
				alert("服务器访问失败！");
			},
		})
	}
}





