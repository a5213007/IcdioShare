function loadManager(){
	if(sessionStorage.user == undefined){
		alert('请先登录！');
		window.location.href = "login.html";
	}
	else {
		var user = eval(sessionStorage.user);
		$("#name").html('欢迎你，<a>' + user[0]['name'] + '</a>');

		var page = GetRequest();
		$.ajax ({
			type:'post',
			async:false,
			url:'../servlet/ManagerINdexServlet',
			data:{
				'activeIndex':page['activeIndex'], 'technologyIndex':page['technologyIndex']
			},
			success: function(data) {
				if(data == undefined || data == '') {

				}
				else {
					var info = eval(data);
					var activeDisplay = "";
					var technologyDisplay = "";
					for(var i = 0; i < info.length; i++) {
						if(info[i]['type'] == "活动通知")
							activeDisplay += "<div class='item'><span class='itemFl'>" + info[i]['title'] +
							 "</span><span class='itemFr'>" + info[i]['releaseDate'] + "</span></div>";
						else if(info[i]['type'] == "技术分享")
							technologyDisplay += "<div class='item'><span class='itemFl'>" + info[i]['title'] +
							 "</span><span class='itemFr'>" + info[i]['releaseDate'] + "</span></div>";
					}
				}
				$('#activeDisplay').append(activeDisplay);
				$('#technologyDisplay').append(technologyDisplay);
			},
			error: function(data) {
			},
		});

		$.ajax({
			type:'post',
			async:false,
			url:'../servlet/DividePageServlet',
			data:{
				'type':'AllPage'
			},
			success: function(data) {
				if(data == undefined || data == '') {

				}
				else {
					var info = eval(data);
					sessionStorage.pages = JSON.stringify(info);
					var allActivePages = info[0]['活动通知'];
					var allTechnologyPages = info[0]['技术分享'];
					$('#activePages').html(allActivePages);
					$('#technologyPages').html(allTechnologyPages);
					$('#currentActivePage').html(page['activeIndex']);
					$('#currentTechnologyPage').html(page['technologyIndex']);

					/*
					活动管理首页  上一页  下一页  尾页  样式改变
					*/
					if($('#activePages').html() == $('#currentActivePage').html() && $('#currentActivePage').html() == 1) {
						$('#activeFirstPage').removeClass('canEdit');
						$('#activePreviousPage').removeClass('canEdit');
						$('#activeNextPage').removeClass('canEdit');
						$('#activeLastPage').removeClass('canEdit');
						$('#activeFirstPage').addClass('cantEdit');
						$('#activePreviousPage').addClass('cantEdit');
						$('#activeNextPage').addClass('cantEdit');
						$('#activeLastPage').addClass('cantEdit');
					}
					else if($('#currentActivePage').html() == 1) {
						$('#activeFirstPage').removeClass('canEdit');
						$('#activePreviousPage').removeClass('canEdit');
						$('#activeFirstPage').addClass('cantEdit');
						$('#activePreviousPage').addClass('cantEdit');
						$('#activeNextPage').removeClass('cantEdit');
						$('#activeLastPage').removeClass('cantEdit');
						$('#activeNextPage').addClass('canEdit');
						$('#activeLastPage').addClass('canEdit');
					}else if($('#activePages').html() == $('#currentActivePage').html()) {
						$('#activeFirstPage').removeClass('cantEdit');
						$('#activePreviousPage').removeClass('cantEdit');
						$('#activeFirstPage').addClass('canEdit');
						$('#activePreviousPage').addClass('canEdit');
						$('#activeNextPage').addClass('cantEdit');
						$('#activeLastPage').addClass('cantEdit');
						$('#activeNextPage').removeClass('canEdit');
						$('#activeLastPage').removeClass('canEdit');
					}else {
						$('#activeFirstPage').removeClass('cantEdit');
						$('#activePreviousPage').removeClass('cantEdit');
						$('#activeNextPage').removeClass('cantEdit');
						$('#activeLastPage').removeClass('cantEdit');
						$('#activeFirstPage').addClass('canEdit');
						$('#activePreviousPage').addClass('canEdit');
						$('#activeNextPage').addClass('canEdit');
						$('#activeLastPage').addClass('canEdit');
					}
					/*
					技术分享首页  上一页  下一页  尾页  样式改变
					*/
					if($('#technologyPages').html() == $('#currentTechnologyPage').html() && $('#currentTechnologyPage').html() == 1) {
						$('#technologyFirstPage').removeClass('canEdit');
						$('#technologyPreviousPage').removeClass('canEdit');
						$('#technologyNextPage').removeClass('canEdit');
						$('#technologyLastPage').removeClass('canEdit');
						$('#technologyFirstPage').addClass('cantEdit');
						$('#technologyPreviousPage').addClass('cantEdit');
						$('#technologyNextPage').addClass('cantEdit');
						$('#technologyLastPage').addClass('cantEdit');
					}
					else if($('#currentTechnologyPage').html() == 1) {
						$('#technologyFirstPage').removeClass('canEdit');
						$('#technologyPreviousPage').removeClass('canEdit');
						$('#technologyFirstPage').addClass('cantEdit');
						$('#technologyPreviousPage').addClass('cantEdit');
						$('#technologyNextPage').removeClass('cantEdit');
						$('#technologyLastPage').removeClass('cantEdit');
						$('#technologyNextPage').addClass('canEdit');
						$('#technologyLastPage').addClass('canEdit');
					}else if($('#technologyPages').html() == $('#currentTechnologyPage').html()) {
						$('#technologyFirstPage').removeClass('cantEdit');
						$('#technologyPreviousPage').removeClass('cantEdit');
						$('#technologyFirstPage').addClass('canEdit');
						$('#technologyPreviousPage').addClass('canEdit');
						$('#technologyNextPage').addClass('cantEdit');
						$('#technologyLastPage').addClass('cantEdit');
						$('#technologyNextPage').removeClass('canEdit');
						$('#technologyLastPage').removeClass('canEdit');
					}else {
						$('#technologyFirstPage').removeClass('cantEdit');
						$('#technologyPreviousPage').removeClass('cantEdit');
						$('#technologyNextPage').removeClass('cantEdit');
						$('#technologyLastPage').removeClass('cantEdit');
						$('#technologyFirstPage').addClass('canEdit');
						$('#technologyPreviousPage').addClass('canEdit');
						$('#technologyNextPage').addClass('canEdit');
						$('#technologyLastPage').addClass('canEdit');
					}
				}
			},
			error: function(data) {
			},
		});

	}
}

function turnToActiveFirstPage() {
	var page = GetRequest();
	var pages = eval(sessionStorage.pages);
	if(parseInt(page['activeIndex']) > 1 && parseInt(page['activeIndex']) <= pages[0]['活动通知'])
		window.location.href = "ManagerIndex.html?activeIndex=1&technologyIndex=" + page['technologyIndex'];
}

function turnToTechnologyFirstPage() {
	var page = GetRequest();
	var pages = eval(sessionStorage.pages);
	if(parseInt(page['technologyIndex']) > 1 && parseInt(page['technologyIndex']) <= pages[0]['技术分享'])
		window.location.href = "ManagerIndex.html?activeIndex=" + page['activeIndex'] + "&technologyIndex=1";
}

function turnToActivePreviousPage() {
	var page = GetRequest();
	var pages = eval(sessionStorage.pages);
	if(parseInt(page['activeIndex']) > 1 && parseInt(page['activeIndex']) <= pages[0]['活动通知'])
		window.location.href = "ManagerIndex.html?activeIndex=" + (parseInt(page['activeIndex']) - 1) + "&technologyIndex=" + page['technologyIndex'];
}

function turnToActiveNextPage() {
	var page = GetRequest();
	var pages = eval(sessionStorage.pages);
	if(parseInt(page['activeIndex']) < pages[0]['活动通知'] && parseInt(page['activeIndex']) > 0)
		window.location.href = "ManagerIndex.html?activeIndex=" + (parseInt(page['activeIndex']) + 1) + "&technologyIndex=" + page['technologyIndex'];
}

function turnToTechnologyPreviousPage() {
	var page = GetRequest();
	var pages = eval(sessionStorage.pages);
	if(parseInt(page['technologyIndex']) > 1 && parseInt(page['technologyIndex']) <= pages[0]['技术分享'])
		window.location.href = "ManagerIndex.html?activeIndex=" + page['activeIndex'] + "&technologyIndex=" + (parseInt(page['technologyIndex']) - 1);
}

function turnToTechnologyNextPage() {
	var page = GetRequest();
	var pages = eval(sessionStorage.pages);
	if(parseInt(page['technologyIndex']) < pages[0]['技术分享'] && parseInt(page['technologyIndex']) > 0)
		window.location.href = "ManagerIndex.html?activeIndex=" + page['activeIndex'] + "&technologyIndex=" + (parseInt(page['technologyIndex']) + 1);
}

function turnToActiveLastPage() {
	var pages = eval(sessionStorage.pages);
	var page = GetRequest();
	if(parseInt(page['activeIndex']) < pages[0]['活动通知'] && parseInt(page['activeIndex']) > 0)
		window.location.href = "ManagerIndex.html?activeIndex=" + pages[0]['活动通知'] + "&technologyIndex=" + page['technologyIndex'] ;
}

function turnToTechnologyLastPage() {
	var pages = eval(sessionStorage.pages);
	var page = GetRequest();
	if(parseInt(page['technologyIndex']) < pages[0]['技术分享'] && parseInt(page['technologyIndex']) > 0)
		window.location.href = "ManagerIndex.html?activeIndex=" + page['activeIndex'] + "&technologyIndex=" + pages[0]['技术分享'] ;
}

function closeTechnology(){
	$("#cover").css('display','none');
	$("#technologyCover").css('display','none');
	$("#technology").css('display','none');
}

function openTechnology(){
	ini('tey');
	$("#cover").css('display','block');
	$("#technologyCover").css('display','block');
	$("#technology").css('display','block');
}

function closeActive(){
	$("#cover").css('display','none');
	$("#activeCover").css('display','none');
	$("#active").css('display','none');
}

function openActive(){
	ini('active');
	$("#cover").css('display','block');
	$("#activeCover").css('display','block');
	$("#active").css('display','block');
}

function ini(type){
	if(type == 'tey'){
		$("#teyFile").val(""); 
		$("#teyTitle").val("");
		$("#teyContent").val("");
		$("#fileTip").html("");
	}else {
		$("#actTitle").val("");
		$("#actContent").val("");
		$("#actTime").val("");
		$("#actPlace").val("");
		$("#actType option:first").prop("selected", 'selected');
	}	
}

function submitTechnology(){
	
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
