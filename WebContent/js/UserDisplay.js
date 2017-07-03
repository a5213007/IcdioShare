/**
 * 
 */
function init() {
	isLogin();
	var user = eval(sessionStorage.user);
	$('#account').val(user[0]['account']);
	$('#name').val(user[0]['name']);
	$('#sex').val(user[0]['sex']);
	$('#age').val(user[0]['age']);
	$('#phoneNum').val(user[0]['phoneNum']);
}

function changeButton() {
	$('#name').attr("disabled",false);
	$('#sex').attr("disabled",false);
	$('#age').attr("disabled",false);
	$('#phoneNum').attr("disabled",false);
	$("#account").attr("readOnly",true); 
	$('#name').removeClass('userDisplay_inputBorder1');
	$('#sex').removeClass('userDisplay_inputBorder1');
	$('#age').removeClass('userDisplay_inputBorder1');
	$('#phoneNum').removeClass('userDisplay_inputBorder1');
	$('#name').addClass('userDisplay_inputBorder2');
	$('#sex').addClass('userDisplay_inputBorder2');
	$('#age').addClass('userDisplay_inputBorder2');
	$('#phoneNum').addClass('userDisplay_inputBorder2');
	$('#changeInfo').addClass('displayNone');
	$('#insure').removeClass('displayNone');
	$('#return').removeClass('displayNone');
	$('#changePd').addClass('displayNone');
}

function User_return() {
	window.location.href="UserDisplay.html";
}

function checkName() {
	var name = $('#name').val();
	if (name == ''  || name.length == 0) {
		$('#nameTip').html('姓名不能为空！');
	}
	else if (name.length > 20) {
		$('#nameTip').html('姓名不能超过20个字！');
	}
	else
		$('#nameTip').html('');
}

function checkAge() {
	var age = $('#age').val();
	if (sex.length == 0 || sex == '') {
		$('#sexTip').html('');
	}
	else if (parseInt(age) > 100 || parseInt(age) < 1) {
		$('#ageTip').html('年龄在1~100之间！');
	}
	else
		$('#ageTip').html('');
}

function checkSex() {
	var sex = $('#sex').val();
	if (sex.length == 0 || sex == '') {
		$('#sexTip').html('');
	}
	else if(sex  != '男' && sex != '女')
		$('#sexTip').html('性别只能为男女！');
	else
		$('#sexTip').html('');
}

function checkPhoneNum() {
	var phoneNum = $('#phoneNum').val();
	if (phoneNum.length == 0 || phoneNum == '') {
		$('#phoneNumTip').html('');
	}
	else if (phoneNum.length != 11) {
		$('#phoneNumTip').html('电话号码为11位！');
	}
	else if (!(/^1[3|4|5|8][0-9]\d{4,8}$/.test(phoneNum))) {
		$('#phoneNumTip').html('电话号码不规范！');
	}
	else
		$('#phoneNumTip').html('');
}

function submit() {
	var account = $('#account').val();
	var name = $('#name').val();
	var sex = $('#sex').val();
	var age = $('#age').val();
	var phoneNum = $('#phoneNum').val();
	var data = {};
	data['account'] = account;
	data['name'] = name;
	data['sex'] = sex;
	data['age'] = age;
	data['phoneNum'] = phoneNum;
	if(!checkSubmit(data))
		return;

	$.ajax({
		type:"post",
		url:"../servlet/UserServlet",
		async : false,
		data :{
			'data' : JSON.stringify(data),'type':'changeInfo'
		},
		success : function(data) {
			alert('修改成功！请重新登录！');
			sessionStorage.removeItem('user');
			window.location.href = "login.html";
		},
		errot: function(data){
			alert('访问服务器失败！')
		},
	});
}

function checkSubmit(data){
	if (data['name'] == ''  || data['name'].length == 0) {
		alert('姓名不能为空！');
		return false;
	}
	else if (data['name'].length > 20 && data['name'] != '') {
		alert('姓名不能超过20个字！');
		return false;
	}
	else if(data['sex']  != '男' && data['sex'] != '女' && data['sex'] != '') {
		alert('性别只能为男女！');
		return false;
	}
	else if ((parseInt(data['age']) > 100 || parseInt(data['age'])  && data['age'] != '') < 1) {
		alert('年龄在1~100之间！');
		return false;
	}
	else if (data['phoneNum'].length != 11 && data['phoneNum'] != '') {
		alert('电话号码为11位！');
		return false;
	}
	else if (!(/^1[3|4|5|8][0-9]\d{4,8}$/.test(data['phoneNum'])) && data['phoneNum'] != '') {
		alert('电话号码不规范！');
		return false;
	}
	else
		return true;
}

function checkPassword() {
	if($('#newPd').val().length == 18)
		$('#newPdTip').html("密码不能超过18位!");
	else
		$('#newPdTip').html("");
}

function checkPdIsEqual() {
	if($('#newPd').val() != $('#rePd').val())
		$('#rePdTip').html("前后密码不一致，请修改!");
	else
		$('#rePdTip').html("");
}

function pdReturn() {
	window.location.href="UserDisplay.html";
}

function changePd() {
	$('#changePd').addClass('displayNone');
	$('#changeInfo').addClass('displayNone');
	$('#pdFrame').removeClass('displayNone');
}

function pdSubmit() {
	var account = $('#account').val();
	var newPassword = $('#newPd').val();
	var rePassword = $('#rePd').val();

	if (newPassword != rePassword) {
		alert("前后密码不一致，请修改！");
		return;
	}

	$.ajax({
		type:"post",
		url:"../servlet/UserServlet",
		async : false,
		data :{
			'account':account, 'password':rePassword,'type':'changePd'
		},
		success : function(data) {
			alert('修改成功！请重新登录！');
			sessionStorage.removeItem('user');
			window.location.href = "login.html";
		},
		errot: function(data){
			alert('访问服务器失败！')
		},
	});
}