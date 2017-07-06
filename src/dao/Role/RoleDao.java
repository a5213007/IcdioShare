package dao.Role;

import java.util.List;
import java.util.Map;

import dao.baseDao.IBaseDao;
import util.connectUtil.*;
import util.operateObject.ObjectToSQL;

public class RoleDao extends CommonDAO implements IBaseDao{

	public int save(Object object) throws Exception{
		String sql = ObjectToSQL.toSqlForSave(object);
		executeSql(sql);
		return 1;		
	}
	
	public int update(Object object) throws Exception{
		String sql = ObjectToSQL.toSqlForUpdate(object);
		executeSql(sql);
		return 1;
	}
	
	public List<Map<String, Object>> getAllInfo() throws Exception{
		String sql = "select * from role";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	}
	
	/**
	 * 跳页
	 * */
	public List<Map<String, Object>> getPageInfo(int page) throws Exception{
		String sql = "select * from role limit " + (page - 1) * 10  + ", 10";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	}
	
	/**
	 * 获取页数
	 * */
	public List<Map<String, Object>> getAllInfoPage() throws Exception{
		String sql = "select count(*) / 10 as page from role";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	}
	
	public List<Map<String, Object>> getInfoById(Long id) throws Exception{
		String sql = "select * from role where id = " + id;
		return excuteQuery(sql, null);
	}
	
	public int delete(Long id) throws Exception{
		String sql = "delete from role where id = " + id;
		executeSql(sql);
		return 1;
	}
	public List<Map<String, Object>> findByKeyAndValue(String key, String value, int page){
		String sql = "select * from role where " + key + " like '%" + value + "%' limit " + (page - 1) * 10 + ", 10";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	}
	
	public List<Map<String, Object>> findByKeyAndValuePage(String key, String value, int page){
		String sql = "select count(*) / 10 as page from role where " + key + " like '%" + value + "%' limit " + (page - 1) * 10 + ", 10";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	}
	
	public List<Map<String, Object>> getRoleByUserId(Long userId)throws Exception{
		String sql = "select user.id, user_role.roleID from user_role left join user on user_role.userID = user.id where userID = " + userId;
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	}
	
	public void addRoleToUser(String id, String perssions, Long userId)throws Exception{
		String sql = "insert into user_role(id,userID,roleID) values('"+id+"','"+userId+"','"+perssions+"')";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		executeSql(sql);
	}
	
	public void removeAllRole(Long userId) throws Exception{
		String sql = "delete from user_role where userID = " + userId;
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		executeSql(sql);
	}
}