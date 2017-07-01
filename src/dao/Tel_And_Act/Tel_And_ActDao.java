package dao.Tel_And_Act;

import java.util.List;
import java.util.Map;

import dao.baseDao.IBaseDao;
import util.connectUtil.*;
import util.operateObject.ObjectToSQL;

public class Tel_And_ActDao extends CommonDAO implements IBaseDao{

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
		String sql = "select * from tel_and_act" + " and state = '已提交'";
		return excuteQuery(sql, null);
	}
	
	public List<Map<String, Object>> getInfoById(Long id) throws Exception{
		String sql = "select * from tel_and_act where id = " + id + " and state = '已提交'";
		return excuteQuery(sql, null);
	}
	
	public int delete(Long id) throws Exception{
		String sql = "delete from tel_and_act where id = " + id + " and state = '已提交'";
		executeSql(sql);
		return 1;
	}
	
	public List<Map<String, Object>> getAllActiveInfo() throws Exception{
		String sql = "select * from v_active";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	} 
	
	public List<Map<String, Object>> getAllActiveInfoByIndex(int index) throws Exception{
		String sql = "select * from v_active where state = '已提交' limit " + (index - 1) * 10 + ",10";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	} 
	
	public List<Map<String, Object>> getAllTechnologyByIndex(int index) throws Exception{
		String sql = "select * from v_technology where state = '已提交' limit " + (index - 1) * 10 + ",10";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	} 
	
	public List<Map<String, Object>> getAllTechnologyInfo() throws Exception{
		String sql = "select * from v_technology";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	} 
	
	public List<Map<String, Object>> getTechnologyAndUserById(Long id) throws Exception{
		String sql = "select * from v_technologyanduser where id = " + id + " and state = '已提交'";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	} 
}