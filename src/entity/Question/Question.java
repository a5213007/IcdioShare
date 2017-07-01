package entity.Question;

import util.codeGenerate.*;

@Entity(name="问题信息", table="question")
public class Question{
	@Column(name="id", chineseName="问题信息id",nullAble=false, note="id", type="String", length=14)
	private String id;

	@Column(name="telAndActID", chineseName="技术日常活动表ID", nullAble=true, note="技术日常活动表ID", type="String", length=14)
	private String telAndActID;
	 
	@Column(name="userID", chineseName="人员ID", nullAble=true, note="人员ID", type="String", length=14)
	private String userID;
	 
	@Column(name="questionContent", chineseName="问题内容", nullAble=true, note="问题内容", type="String", length=-1)
	private String questionContent;
	 
	@Column(name="askDate", chineseName="提问时间", nullAble=true, note="提问时间", type="String", length=-1)
	private String askDate;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTelAndActID(String telAndActID){
		this.telAndActID = telAndActID;	
	}

	public String getTelAndActID(){
		return telAndActID;		
	}	 

	public void setUserID(String userID){
		this.userID = userID;	
	}

	public String getUserID(){
		return userID;		
	}	 

	public void setQuestionContent(String questionContent){
		this.questionContent = questionContent;	
	}

	public String getQuestionContent(){
		return questionContent;		
	}	 

	public void setAskDate(String askDate){
		this.askDate = askDate;	
	}

	public String getAskDate(){
		return askDate;		
	}	 

}
