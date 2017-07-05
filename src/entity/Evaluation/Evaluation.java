package entity.Evaluation;

import util.codeGenerate.*;

@Entity(name="评价信息", table="evaluation")
public class Evaluation{
	@Column(name="id", chineseName="评价信息id",nullAble=false, note="id", type="String", length=14)
	private String id;

	@Column(name="telAndActID", chineseName="技术日常活动表ID", nullAble=true, note="技术日常活动表ID", type="String", length=14)
	private String telAndActID;
	 
	@Column(name="userID", chineseName="人员ID", nullAble=true, note="人员ID", type="String", length=14)
	private String userID;
	 
	@Column(name="evaluationContent", chineseName="评价内容", nullAble=true, note="评价内容", type="String", length=200)
	private String evaluationContent;
	 
	@Column(name="evalutionDate", chineseName="评价时间", nullAble=true, note="评价时间", type="String", length=-1)
	private String evalutionDate;
	 
	@Column(name="Type", chineseName="类型", nullAble=true, note="类型", type="String", length=-1)
	private String Type;
	 	
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

	public void setEvaluationContent(String evaluationContent){
		this.evaluationContent = evaluationContent;	
	}

	public String getEvaluationContent(){
		return evaluationContent;		
	}	 

	public void setEvalutionDate(String evalutionDate){
		this.evalutionDate = evalutionDate;	
	}

	public String getEvalutionDate(){
		return evalutionDate;		
	}	 

	public void setType(String Type){
		this.Type = Type;	
	}

	public String getType(){
		return Type;		
	}	 

}
