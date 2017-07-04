package entity.Process;

import util.codeGenerate.*;

@Entity(name="流程信息", table="peocess")
public class Process{
	@Column(name="id", chineseName="流程信息id",nullAble=false, note="id", type="String", length=14)
	private String id;

	@Column(name="state", chineseName="状态", nullAble=true, note="状态", type="String", length=-1)
	private String state;
	 
	@Column(name="telAndActID", chineseName="技术日常活动表ID", nullAble=true, note="技术日常活动表ID", type="String", length=14)
	private String telAndActID;
	 
	@Column(name="submitID", chineseName="提交人ID", nullAble=true, note="提交人ID", type="String", length=14)
	private String submitID;
	 
	@Column(name="reviewID", chineseName="审批人ID", nullAble=true, note="审批人ID", type="String", length=14)
	private String reviewID;
	
	@Column(name="releaseDate", chineseName="提交日期", nullAble=true, note="提交日期", type="String", length=-1)
	private String releaseDate;
	
	@Column(name="reviewDate", chineseName="审批日期", nullAble=true, note="审批日期", type="String", length=-1)
	private String reviewDate;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}

	public void setState(String state){
		this.state = state;	
	}

	public String getState(){
		return state;		
	}	 

	public void setTelAndActID(String telAndActID){
		this.telAndActID = telAndActID;	
	}

	public String getTelAndActID(){
		return telAndActID;		
	}	 

	public void setSubmitID(String submitID){
		this.submitID = submitID;	
	}

	public String getSubmitID(){
		return submitID;		
	}	 

	public void setReviewID(String reviewID){
		this.reviewID = reviewID;	
	}

	public String getReviewID(){
		return reviewID;		
	}	 

}
