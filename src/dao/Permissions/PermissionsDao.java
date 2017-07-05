package dao.Permissions;

import java.util.List;
import java.util.Map;

import dao.baseDao.IBaseDao;
import util.connectUtil.*;
import util.operateObject.ObjectToSQL;

public class PermissionsDao extends CommonDAO implements IBaseDao{

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
		String sql = "select * from permissions";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	}
	
	public List<Map<String, Object>> getInfoById(Long id) throws Exception{
		String sql = "select * from permissions where id = " + id;
		return excuteQuery(sql, null);
	}
	
	public int delete(Long id) throws Exception{
		String sql = "delete from permissions where id = " + id;
		executeSql(sql);
		return 1;
	}
	
	/**
	 * 跳页
	 * */
	public List<Map<String, Object>> getPageInfo(int page) throws Exception{
		String sql = "select * from permissions limit " + (page - 1) * 10 + ", 10";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	}
	
	/**
	 * 获取页数
	 * */
	public List<Map<String, Object>> getAllInfoPage() throws Exception{
		String sql = "select count(*) / 10 as page from permissions";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	}
	
	public List<Map<String, Object>> hasPermissions(Long userId){
		String sql = "select * from v_user where id = "+ userId + " and sign is not NULL";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	}
	
	public List<Map<String, Object>> hasPermissions(String signName, Long userId){
		String sql = "select * from v_user where id = "+ userId + 
				" and sign = '" + signName + "'";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	}
	
	
	public List<Map<String, Object>> findByKeyAndValue(String key, String value, int page){
		String sql = "select * from permissions where " + key + " like '%" + value + "%' limit " + (page - 1) * 10 + ", 10";
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	}
	
	public List<Map<String, Object>> findByKeyAndValuePage(String key, String value, int page){
		String sql = "select count(*) / 10 as page from permissions where " + key + " like '%" + value + "%' limit " + (page - 1) * 10 + ", 10";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	}
	
	public List<Map<String, Object>> getPerssionsByRoleId(Long roleId)throws Exception{
		String sql = "SELECT role.id, perssions_role.perssionsID from role INNER JOIN perssions_role ON perssions_role.roleID = role.id INNER JOIN permissions ON perssions_role.perssionsID = permissions.id where roleID = " + roleId;
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	}
	
	public void addPermissionsToRole(String id, String perssions, Long roleID)throws Exception{
		String sql = "insert into perssions_role(id,perssionsID,roleID) values('"+id+"','"+perssions+"','"+roleID+"')";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		executeSql(sql);
	}
	
	public void removeAllPermissions(Long roleId) throws Exception{
		String sql = "delete from perssions_role where roleID = " + roleId;
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		executeSql(sql);
	}
}
