function hasPerssions(type){
	var perssions = eval(sessionStorage.user)[0]['sign'];
	for(var i = 0; i < perssions.length; i++){
		if(type == perssions[i])
			return true;
	}
	
	return false;
}

function getPermissions(){
	$.ajax({
		type:'post',
		url:'../servlet/PermissionsServlet',
		async:false,
		data:{
			'info':'getPermissions'
		},
		success:function(data){
			if(eval(data) != undefined || eval(data) != ""){
				sessionStorage.permissions = eval(data);
			}
		},
		error:function(data){
			alert("权限获取失败！")
		},
	})
}

function goWhere(tip){
	window.location.href = tip + ".html?block=main";
}

//将编码转换成字符  
function utf8ToChar(str) {  
    var iCode, iCode1, iCode2;  
    iCode = parseInt("0x" + str.substr(1, 2));  
    iCode1 = parseInt("0x" + str.substr(4, 2));  
    iCode2 = parseInt("0x" + str.substr(7, 2));  
    return String.fromCharCode(((iCode & 0x0F) << 12) | ((iCode1 & 0x3F) << 6) | (iCode2 & 0x3F));  
} 

function isLogin(){
	if(sessionStorage.user == undefined){
		alert('请先登录！');
		window.location.href = "login.html";
	}
}

function reManager(){
	window.history.go(-1);
}

function exit() {
	sessionStorage.removeItem('user');
	$.ajax({
		type : "post",
		url : "../servlet/ExitLogin",
		async : false,
		data :{
		},
		success: function(data) {
			window.location.href="index.html";	
		},
		error : function(data){
		},
	});
}
function GetRequest() {  
	   var url = location.search; //获取url中"?"符后的字串  
	   var theRequest = new Object();   
	   if (url.indexOf("?") != -1) {  
	      var str = url.substr(1);  
	      strs = str.split("&");  
	      for(var i = 0; i < strs.length; i ++) {  
	         theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);  
	      }  
	   }  
	   return theRequest;  
	} 

function getId(){
	var date = new Date();
	var ID = date.toString().substring(11, 15);
	ID += parseInt(date.getMonth()) + 1; //月
	ID += date.getDate();  //天
	ID += date.toString().substring(16,18) + date.toString().substring(19,21);
	ID += date.toString().substring(22,24);
	return ID;
}

function getNowTime(){
	var date = new Date();
	var time = date.toString().substring(11, 15) + "/";
	time += (parseInt(date.getMonth()) + 1) + "/"; //月
	time += date.getDate() + " ";  //天
	time += date.toString().substring(16,18) + ":" + date.toString().substring(19,21) + ":";
	time += date.toString().substring(22,24);
	return time;
}