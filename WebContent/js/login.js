function turnToLogin(){
	window.location.href = "login.html";
}

function Submit() {
	var account = $('#account').val();
	var password = $('#password').val();
	var data = {};
	data['account'] = account;
	data['password'] = password;
	if(!checkSubmit(data))
		return ;
	$.ajax({
		type : "post",
		url : "../servlet/LoginServlet",
		async : false,
		data :{
			'account' : account, 'password' : password
		},
		success: function(data) {
			if(data == undefined || data == '') {
				alert('账号密码错误或无此账号！');
			}
			else {
				var user = eval(data);
				sessionStorage.user = JSON.stringify(user);
				window.location.href="ManagerIndex.html?activeIndex=1&technologyIndex=1";
			}			
		},
		error : function(data){
			alert('访问服务器失败！');
		},
	});
}

function checkSubmit(data) {
	if(data['account'].length == 0 || data['account'] == undefined || data['account'] == ''){
		alert("账号密码不能为空！");
		return false;
	}
	else if(data['account'].length < 11 || data['account'].length > 11) {
		alert("账号必须为11位数字！");
		return false;
	}
	return true;

}

function checkAccount() {
	if($('#account').val().length < 11 || $('#account').val().length > 11)
		$('#account_tip').html("账号必须为11位数字!");
	else
		$('#account_tip').html("");
}

function checkPassword() {
	if($('#password').val().length == 18)
		$('#password_tip').html("密码不能超过18位!");
	else
		$('#password_tip').html("");
}

function alreadyLogin() {
	if (sessionStorage.user != undefined) {
		window.location.href = 'ManagerIndex.html?activeIndex=1&technologyIndex=1';
	}
}

function submitByEnter() {
	if(event.keyCode == "13") {
		Submit();
	}
}

