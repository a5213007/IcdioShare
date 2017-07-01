function hasPerssions(userId){
	$.ajax({
		type:'post',
		url:'',
		aysnc:false,
		data:{
			
		},
		success:function(data){
			
		},
		error:function(data){
			
		},
	})
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