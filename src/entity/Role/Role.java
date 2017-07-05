package entity.Role;

import util.codeGenerate.*;

@Entity(name="角色信息", table="role")
public class Role{
	@Column(name="id", chineseName="角色信息id",nullAble=false, note="id", type="String", length=14,editAble=false)
	private String id;

	@Column(name="roleName", chineseName="角色名称", nullAble=false, note="角色名称", type="String", length=-1)
	private String roleName;
	 
	public void setRoleName(String roleName){
		this.roleName = roleName;	
	}

	public String getRoleName(){
		return roleName;		
	}	 

}
