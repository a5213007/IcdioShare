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
	$.ajax({
		type:'post',
		url:'../servlet/PermissionsServlet',
		async:false,
		data:{
			'info':'getPermissions','id':eval(sessionStorage.user)[0]['id']
		},
		success:function(data){
			if(eval(data) != undefined && eval(data) != "" && eval(data).length != 0){
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

function uniencode(text)
{
    text = escape(text.toString()).replace(/\+/g, "%2B");
    var matches = text.match(/(%([0-9A-F]{2}))/gi);
    if (matches)
    {
        for (var matchid = 0; matchid < matches.length; matchid++)
        {
            var code = matches[matchid].substring(1,3);
            if (parseInt(code, 16) >= 128)
            {
                text = text.replace(matches[matchid], '%u00' + code);
            }
        }
    }
    text = text.replace('%25', '%u0025');
 
    return text;
}

function convert_int_to_utf8($intval)
{
    $intval = intval($intval);
    switch ($intval)
    {
        // 1 byte, 7 bits
        case 0:
            return chr(0);
        case ($intval & 0x7F):
            return chr($intval);
 
        // 2 bytes, 11 bits
        case ($intval & 0x7FF):
            return chr(0xC0 | (($intval >> 6) & 0x1F)) .
                chr(0x80 | ($intval & 0x3F));
 
        // 3 bytes, 16 bits
        case ($intval & 0xFFFF):
            return chr(0xE0 | (($intval >> 12) & 0x0F)) .
                chr(0x80 | (($intval >> 6) & 0x3F)) .
                chr (0x80 | ($intval & 0x3F));
 
        // 4 bytes, 21 bits
        case ($intval & 0x1FFFFF):
            return chr(0xF0 | ($intval >> 18)) .
                chr(0x80 | (($intval >> 12) & 0x3F)) .
                chr(0x80 | (($intval >> 6) & 0x3F)) .
                chr(0x80 | ($intval & 0x3F));
    }
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