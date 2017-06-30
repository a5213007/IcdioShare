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

function getId(){
	var date = new Date();
	var ID = date.toString().substring(11, 15);
	ID += parseInt(date.getMonth()) + 1; //月
	ID += date.getDate();  //天
	ID += date.toString().substring(16,18) + date.toString().substring(19,21);
	ID += date.toString().substring(22,24);
	return ID;
}