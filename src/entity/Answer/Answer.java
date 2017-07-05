package entity.Answer;

import util.codeGenerate.*;

@Entity(name="答案信息", table="answer")
public class Answer{
	@Column(name="id", chineseName="答案信息id",nullAble=false, note="id", type="String", length=14)
	private String id;

	@Column(name="questionID", chineseName="问题ID", nullAble=true, note="问题ID", type="String", length=14)
	private String questionID;
	 
	@Column(name="userID", chineseName="人员ID", nullAble=true, note="人员ID", type="String", length=14)
	private String userID;
	 
	@Column(name="answerDate", chineseName="回答时间", nullAble=true, note="回答时间", type="String", length=-1)
	private String answerDate;
	 
	@Column(name="answerContent", chineseName="回答内容", nullAble=true, note="回答内容", type="String", length=8000)
	private String answerContent;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setQuestionID(String questionID){
		this.questionID = questionID;	
	}

	public String getQuestionID(){
		return questionID;		
	}	 

	public void setUserID(String userID){
		this.userID = userID;	
	}

	public String getUserID(){
		return userID;		
	}	 

	public void setAnswerContent(String answerContent){
		this.answerContent = answerContent;	
	}

	public String getAnswerContent(){
		return answerContent;		
	}	 

	public void setAnswerDate(String answerDate){
		this.answerDate = answerDate;	
	}

	public String getAnswerDate(){
		return answerDate;		
	}	 

}
