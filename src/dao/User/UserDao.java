package dao.User;

import java.util.List;
import java.util.Map;

import dao.baseDao.IBaseDao;
import util.connectUtil.*;
import util.operateObject.ObjectToSQL;

public class UserDao extends CommonDAO implements IBaseDao{

	public int save(Object object) throws Exception{
		String sql = ObjectToSQL.toSqlForSave(object);
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		executeSql(sql);
		return 1;		
	}
	
	public int update(Object object) throws Exception{
		String sql = ObjectToSQL.toSqlForUpdate(object);
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		executeSql(sql);
		return 1;
	}
	
	/**
	 * 跳页
	 * */
	public List<Map<String, Object>> getPageInfo(int page) throws Exception{
		String sql = "select * from user limit " + (page - 1) * 10  + ", 10";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	}
	
	/**
	 * 获取页数
	 * */
	public List<Map<String, Object>> getAllInfoPage() throws Exception{
		String sql = "select count(*) / 10 as page from user";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	}
	
	public List<Map<String, Object>> getAllInfo() throws Exception{
		String sql = "select id,name,account,sex,age,phoneNum from user";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	}
	
	public List<Map<String, Object>> getInfoById(Long id) throws Exception{
		String sql = "select * from user where id = " + id;
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	}
	
	public int delete(Long id) throws Exception{
		String sql = "delete from user where id = " + id;
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		executeSql(sql);
		return 1;
	}
	
	public List<Map<String, Object>> getInfoByAccount(String account) throws Exception{
		String sql = "select * from user where account = " + account;
		return excuteQuery(sql, null);
	}
	
	public List<Map<String, Object>> getViewByAccount(String account) throws Exception{
		String sql = "select * from v_user where account = " + account;
		return excuteQuery(sql, null);
	}
	
	public int updateTo(Map<String, Object> map) throws Exception{
		if(map.get("age").toString().length() == 0)
			map.remove("age");
		String sql = "update user set name='" + map.get("name") + "',sex = '" + 
				map.get("sex") + "',age = " + map.get("age") + ",phoneNum = '"
				+map.get("phoneNum") + "' where account = '" + map.get("account") + "'";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		executeSql(sql);
		return 1;
	}
	
	public int updatePd(String account, String password) throws Exception{
		String sql = "update user set password='" + password + "' where account = " +account + "";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		executeSql(sql);
		return 1;
	}
	
	public List<Map<String, Object>> findByKeyAndValue(String key, String value, int page){
		String sql = "select * from user where " + key + " like '%" + value + "%' limit " + (page - 1) * 10 + ", 10";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	}
	
	public List<Map<String, Object>> findByKeyAndValuePage(String key, String value, int page){
		String sql = "select count(*) / 10 as page from user where " + key + " like '%" + value + "%' limit " + (page - 1) * 10 + ", 10";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	}
}