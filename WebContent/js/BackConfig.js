var control = {};
var control2 = {};
var tableTh = {};
var tableTh2 = {};

//权限
control['权限管理'] = "Permissions";
control2['Permissions'] = "权限管理";
tableTh['Permissions'] = ['id','perssionsName','sign','operate'];
tableTh2['Permissions'] = ['权限ID','权限名称','权限标识','操作'];


//角色
control['角色管理'] = "Role";
control2['Role'] = "角色管理";
tableTh['Role'] = ['id','roleName','operate'];
tableTh2['Role'] = ['角色ID','角色名称','操作'];


//人员信息
control['人员信息管理'] = "User";
control2['User'] = "人员信息管理";
tableTh['User'] = ['id','name','account','sex','age','phoneNum','operate'];
tableTh2['User'] = ['用户ID','姓名','账号','性别','年龄','电话','操作'];

//流程
control['流程管理'] = "Process";
control2['Process'] = "流程管理";
tableTh['Process'] = ['id','telAndActID','submitID','releaseDate','state','operate'];
tableTh2['Process'] = ['流程ID','活动ID','发布人ID','发布时间','状态','操作'];

//评论
control['评论管理'] = "Evaluation";
control2['Evaluation'] = "评论管理";
tableTh['Evaluation'] = ['telAndActID','userID','evaluationContent','evalutionDate','operate'];
tableTh2['Evaluation'] = ['技术分享表ID','发言人ID','评价内容','评价时间','操作'];

//答案
control['答案管理'] = "Answer";
control2['Answer'] = "答案管理";
tableTh['Answer'] = ['questionID','userID','answerContent','answerDate','operate'];
tableTh2['Answer'] = ['问题ID','发布人ID','内容','发布时间','操作'];

//活动通知
control['活动通知管理'] = "Active";
control2['Active'] = "活动通知管理";
tableTh['Active'] = ['title','contentType','userID','releaseDate','state','operate'];
tableTh2['Active'] = ['标题','活动类型','发布人ID','发布时间','状态','操作'];

//技术分享
control['技术分享管理'] = "Technology";
control2['Technology'] = "技术分享管理";
tableTh['Technology'] = ['title','userID','releaseDate','state','operate'];
tableTh2['Technology'] = ['标题','发布人ID','发布时间','状态','操作'];

//问题
control['问题管理'] = "Question";
control2['Question'] = "问题管理";	
tableTh['Question'] = ['telAndActID','userID','questionContent','askDate','operate'];
tableTh2['Question'] = ['技术分享表ID','发布人ID','内容','发布时间','操作'];



