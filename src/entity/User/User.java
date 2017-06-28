package entity.User;

import util.codeGenerate.*;

@Entity(name="用户信息", table="user")
public class User{
	@Column(name="id", chineseName="用户信息id",nullAble=false, note="id", type="String", length=14)
	private String id;

	@Column(name="name", chineseName="姓名", nullAble=true, note="姓名", type="String", length=-1)
	private String name;
	 
	@Column(name="account", chineseName="账号", nullAble=true, note="账号", type="String", length=-1)
	private String account;
	 
	@Column(name="password", chineseName="密码", nullAble=true, note="密码", type="String", length=-1)
	private String password;
	 
	@Column(name="sex", chineseName="性别", nullAble=false, note="性别", type="String", length=-1)
	private String sex;
	 
	@Column(name="age", chineseName="年龄", nullAble=false, note="年龄", type="Integer", length=-1)
	private Integer age;
	 
	@Column(name="phoneNum", chineseName="电话号码", nullAble=false, note="电话号码", type="String", length=11)
	private String phoneNum;
	 
	public void setName(String name){
		this.name = name;	
	}

	public String getName(){
		return name;		
	}	 

	public void setAccount(String account){
		this.account = account;	
	}

	public String getAccount(){
		return account;		
	}	 

	public void setPassword(String password){
		this.password = password;	
	}

	public String getPassword(){
		return password;		
	}	 

	public void setSex(String sex){
		this.sex = sex;	
	}

	public String getSex(){
		return sex;		
	}	 

	public void setAge(Integer age){
		this.age = age;	
	}

	public Integer getAge(){
		return age;		
	}	 

	public void setPhoneNum(String phoneNum){
		this.phoneNum = phoneNum;	
	}

	public String getPhoneNum(){
		return phoneNum;		
	}	 

}
