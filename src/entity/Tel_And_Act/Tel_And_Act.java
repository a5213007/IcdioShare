package entity.Tel_And_Act;

import util.codeGenerate.*;

@Entity(name="技术及日常活动", table="tel_and_act")
public class Tel_And_Act{
	@Column(name="id", chineseName="ID",nullAble=false, note="id", type="String", length=14,editAble=false)
	private String id;

	@Column(name="userID", chineseName="人员ID", nullAble=false, note="人员ID", type="String", length=14,editAble=false)
	private String userID;
	 
	@Column(name="releaseDate", chineseName="发布日期", nullAble=false, note="发布日期", type="String", length=-1,editAble=false)
	private String releaseDate;
	 
	@Column(name="title", chineseName="标题", nullAble=false, note="标题", type="String", length=-1)
	private String title;
	 
	@Column(name="contentType", chineseName="内容类型", nullAble=true, note="辩论、演讲等(技术发布专属)", type="String", length=-1,editAble=false)
	private String contentType;
	 
	@Column(name="type", chineseName="类型", nullAble=false, note="类型", type="String", length=-1,editAble=false)
	private String type;
	 
	@Column(name="attachment", chineseName="附件", nullAble=true, note="文件形式", type="String", length=-1,editAble=false)
	private String attachment;
	 
	@Column(name="state", chineseName="状态", nullAble=true, note="状态", type="String", length=-1,editAble=false)
	private String state;
	 
	@Column(name="activeDate", chineseName="活动时间", nullAble=true, note="活动时间", type="String", length=-1)
	private String activeDate;
	 
	@Column(name="activePlace", chineseName="活动地点", nullAble=true, note="活动地点", type="String", length=-1)
	private String activePlace;
	
	 
	@Column(name="content", chineseName="内容", nullAble=false, note="内容", type="String", length=8000)
	private String content;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUserID(String userID){
		this.userID = userID;	
	}

	public String getUserID(){
		return userID;		
	}	 

	public void setReleaseDate(String releaseDate){
		this.releaseDate = releaseDate;	
	}

	public String getReleaseDate(){
		return releaseDate;		
	}	 

	public void setTitle(String title){
		this.title = title;	
	}

	public String getTitle(){
		return title;		
	}	 

	public void setContent(String content){
		this.content = content;	
	}

	public String getContent(){
		return content;		
	}	 

	public void setContentType(String contentType){
		this.contentType = contentType;	
	}

	public String getContentType(){
		return contentType;		
	}	 

	public void setType(String type){
		this.type = type;	
	}

	public String getType(){
		return type;		
	}	 

	public void setAttachment(String attachment){
		this.attachment = attachment;	
	}

	public String getAttachment(){
		return attachment;		
	}	 

	public void setState(String state){
		this.state = state;	
	}

	public String getState(){
		return state;		
	}	 

	public void setActiveDate(String activeDate){
		this.activeDate = activeDate;	
	}

	public String getActiveDate(){
		return activeDate;		
	}	 

	public void setActivePlace(String activePlace){
		this.activePlace = activePlace;	
	}

	public String getActivePlace(){
		return activePlace;		
	}	 

}
