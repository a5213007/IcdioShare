function hasPerssions(type){
	if(sessionStorage.permissions == undefined || sessionStorage.permissions == "[]")
		return false;
	
	var perssions = eval(sessionStorage.permissions)[0]['sign'];
	for(var i = 0; i < perssions.length; i++){
		if(type == perssions[i])
			return true;
	}
	
	return false;
}

function getPermissions(){
	sessionStorage.removeItem('permissions');
	$.ajax({
		type:'post',
		url:'../servlet/PermissionsServlet',
		async:false,
		data:{
			'info':'getPermissions','id':eval(sessionStorage.user)[0]['id']
		},
		success:function(data){
			if(eval(data) != undefined && eval(data) != "" && eval(data).length != 0 && data != "[]"){
				sessionStorage.permissions = JSON.stringify(eval(data));
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

//转为unicode 编码  
function encodeUnicode(str) {  
    var res = [];  
    for ( var i=0; i<str.length; i++ ) {  
    res[i] = ( "00" + str.charCodeAt(i).toString(16) ).slice(-4);  
    }  
    return "\\u" + res.join("\\u");  
}  
  
// 解码  
function decodeUnicode(str) {  
    str = str.replace(/\\/g, "%");  
    return unescape(str);  
}  

function isLogin(){
	if(sessionStorage.user == undefined){
		alert('请先登录！');
		window.location.href = "login.html";
	}
}

function getCount(str){
	var bbb = str.split("\n")
	return bbb.length - 1 ;
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
	
	if(parseInt(date.getMonth()) + 1 < 10)
		ID += '0' + parseInt(date.getMonth()) + 1; //月
	else 
		ID += parseInt(date.getMonth()) + 1; //月
	
	if(parseInt(date.getDate()) < 10)
		ID += '0' + date.getDate();  //天
	else 
		ID += date.getDate();
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