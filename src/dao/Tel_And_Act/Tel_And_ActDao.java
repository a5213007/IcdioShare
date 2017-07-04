package dao.Tel_And_Act;

import java.util.List;
import java.util.Map;

import dao.baseDao.IBaseDao;
import util.connectUtil.*;
import util.operateObject.ObjectToSQL;

public class Tel_And_ActDao extends CommonDAO implements IBaseDao{

	public int save(Object object) throws Exception{
		String sql = ObjectToSQL.toSqlForSave(object);
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		executeSql(sql);
		return 1;		
	}
	
	public int update(Object object) throws Exception{
		String sql = ObjectToSQL.toSqlForUpdate(object);
		executeSql(sql);
		return 1;
	}
	
	public List<Map<String, Object>> getAllInfo() throws Exception{
		String sql = "select * from tel_and_act";
		return excuteQuery(sql, null);
	}
	
	public List<Map<String, Object>> getInfoById(Long id) throws Exception{
		String sql = "select * from tel_and_act where id = " + id;
		return excuteQuery(sql, null);
	}
	
	public int delete(Long id) throws Exception{
		String sql = "delete from tel_and_act where id = " + id;
		executeSql(sql);
		return 1;
	}
	
	public List<Map<String, Object>> getAllActiveInfo() throws Exception{
		String sql = "select * from v_active" + " where state = '已同意'";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	} 
	
	public List<Map<String, Object>> getAllActiveInfoByIndex(int index) throws Exception{
		String sql = "select * from v_active where state = '已同意' order by releaseDate desc  limit " 
				+ (index - 1) * 10 + ",10";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	} 
	
	public List<Map<String, Object>> getAllTechnologyByIndex(int index) throws Exception{
		String sql = "select * from v_technology where state = '已提交' order by releaseDate desc limit " 
				+ (index - 1) * 10 + ",10";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	} 
	
	//后台展示技技术分享表格以及获取分页(管理)
	//*********************************************************************************
	public List<Map<String, Object>> getAllTechnologyByPageAdmin(int page) throws Exception{
		String sql = "select * from v_technology order by releaseDate desc limit " 
				+ (page - 1) * 10 + ",10";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	}
	
	public List<Map<String, Object>> getAllTechnologyPageAdmin() throws Exception{
		String sql = "select count(*) / 10 as page from v_technology ";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	}
	/*********************************************************************************/
	
	//后台展示技技术分享表格以及获取分页(个人)
	//*********************************************************************************
	public List<Map<String, Object>> getAllTechnologyByPage(int page, Long userId) throws Exception{
		String sql = "select * from v_technology where userID = " + userId +
				" order by releaseDate desc limit " + (page - 1) * 10 + ",10";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	}
	
	public List<Map<String, Object>> getAllTechnologyPage(Long userId) throws Exception{
		String sql = "select count(*) / 10 as page from v_technology where userID = " + userId;
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	}
	/*********************************************************************************/

	//后台展示技日常活动表格以及获取分页(管理)
	//*********************************************************************************
	public List<Map<String, Object>> getAllActiveByPageAdmin(int page) throws Exception{
		String sql = "select * from v_active order by releaseDate desc limit " 
				+ (page - 1) * 10 + ",10";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	}
	
	public List<Map<String, Object>> getAllActivePageAdmin() throws Exception{
		String sql = "select count(*) / 10 as page from v_active ";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	}
	/*********************************************************************************/	
	
	//后台展示技日常活动表格以及获取分页(个人)
		//*********************************************************************************
		public List<Map<String, Object>> getAllActiveByPage(int page, Long userId) throws Exception{
			String sql = "select * from v_active where userID = " + userId + " order by "+
					"releaseDate desc limit " + (page - 1) * 10 + ",10";
			System.out.println("----------------------------------------");
			System.out.println("SQL:" + sql);
			return excuteQuery(sql, null);
		}
		
		public List<Map<String, Object>> getAllActivePage(Long userId) throws Exception{
			String sql = "select count(*) / 10 as page from v_active  where userID = " + userId;
			System.out.println("----------------------------------------");
			System.out.println("SQL:" + sql);
			return excuteQuery(sql, null);
		}
		/*********************************************************************************/	
	
	/**
	 * 前台展示已提交的技术分享页面
	 * */
	public List<Map<String, Object>> getAllTechnologyInfo() throws Exception{
		String sql = "select * from v_technology where state = '已提交'";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	} 
	
	/**
	 * 通过id获取已提交的技术分享
	 * */
	public List<Map<String, Object>> getTechnologyAndUserById(Long id) throws Exception{
		String sql = "select * from v_technologyanduser where id = " + id + " and state = '已提交'";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	} 
	
	/**
	 * 通过id获取已同意的活动通知
	 * */
	public List<Map<String, Object>> getActiveAndUserById(Long id){
		String sql = "SELECT tel_and_act.id, user.id, user.name, releaseDate, title,"+
				" content, contentType, type, state, activeDate, activePlace from "+
				"tel_and_act LEFT JOIN user on(user.id = tel_and_act.userID) where tel_and_act.id = " 
				+ id + " and state = '已同意'";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	}
}
