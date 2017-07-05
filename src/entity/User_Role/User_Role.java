package entity.User_Role;

import util.codeGenerate.*;

@Entity(name="人员角色中间表", table="user_role")
public class User_Role{
	@Column(name="id", chineseName="人员角色中间表id",nullAble=false, note="id", type="String", length=14)
	private String id;

	@Column(name="userID", chineseName="人员ID", nullAble=true, note="人员ID", type="String", length=14)
	private String userID;
	 
	@Column(name="roleID", chineseName="角色ID", nullAble=true, note="角色ID", type="String", length=14)
	private String roleID;
	
	
	
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

	public void setRoleID(String roleID){
		this.roleID = roleID;	
	}

	public String getRoleID(){
		return roleID;		
	}	 

}
