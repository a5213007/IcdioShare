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
	$('#account').attr("disabled",false);
	$('#name').attr("disabled",false);
	$('#sex').attr("disabled",false);
	$('#age').attr("disabled",false);
	$('#phoneNum').attr("disabled",false);
	$('#account').removeClass('userDisplay_inputBorder1');
	$('#name').removeClass('userDisplay_inputBorder1');
	$('#sex').removeClass('userDisplay_inputBorder1');
	$('#age').removeClass('userDisplay_inputBorder1');
	$('#phoneNum').removeClass('userDisplay_inputBorder1');
	$('#account').addClass('userDisplay_inputBorder2');
	$('#name').addClass('userDisplay_inputBorder2');
	$('#sex').addClass('userDisplay_inputBorder2');
	$('#age').addClass('userDisplay_inputBorder2');
	$('#phoneNum').addClass('userDisplay_inputBorder2');
	$('#changeInfo').addClass('displayNone');
	$('#insure').removeClass('displayNone');
	$('#return').removeClass('displayNone');
}

function User_return() {
	window.location.href="UserDisplay.html";
}