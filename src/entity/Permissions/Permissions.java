package entity.Permissions;

import util.codeGenerate.*;

@Entity(name="权限信息", table="perssions")
public class Permissions{
	@Column(name="id", chineseName="权限信息id",nullAble=false, note="id", type="String", length=14)
	private String id;

	@Column(name="perssionsName", chineseName="权限名称", nullAble=true, note="权限名称", type="String", length=-1)
	private String perssionsName;
	 
	@Column(name="sign", chineseName="权限标识", nullAble=true, note="用于标记权限", type="String", length=-1)
	private String sign;
	 
	public void setPerssionsName(String perssionsName){
		this.perssionsName = perssionsName;	
	}

	public String getPerssionsName(){
		return perssionsName;		
	}	 

	public void setSign(String sign){
		this.sign = sign;	
	}

	public String getSign(){
		return sign;		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}	 

}
