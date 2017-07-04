var control = {};
var control2 = {};
control['权限管理'] = "Permissons";
control['角色管理'] = "Role";
control['人员信息管理'] = "User";
control['流程管理'] = "Process";
control['评论管理'] = "Evaluation";
control['问题答案管理'] = "QuesAndAnsw";
control['技术分享管理'] = "Technology";
control['活动通知管理'] = "Active";

control2['Permissons'] = "权限管理";
control2['Role'] = "角色管理";
control2['User'] = "人员信息管理";
control2['Process'] = "流程管理";
control2['Evaluation'] = "评论管理";
control2['QuesAndAnsw'] = "问题答案管理";
control2['Technology'] = "技术分享管理";
control2['Active'] = "活动通知管理";

function loadBackManagement(){
	var url = GetRequest();
	
	if(url['block'] != undefined){
		if(url['block'] == 'main')
			return;
		
		$('#displayModel').css('display','block');
		$('#title').html(control2[url['block']]);
		$('#title').css('display','block');
		
		if(url['block'] == "Process" || url['block'] == "QuesAndAnsw" || url['block'] == "Process" || url['block'] == "Evaluation"){
			$("#btAddInfo").css('display','none');
		}else {
			$("#btAddInfo").css('display','block');
		}	
	}else {
		alert("路径出错！");
		reManager();
	}
	
}

function goBlock(tip){
	window.location.href = "BackManagement.html?block=" + control[tip];
}
function Return(){
	window.location.href = "ManagerIndex.html?activeIndex=1&technologyIndex=1";
}

function goMain() {
	window.location.href = "BackManagement.html?block=main";
}








