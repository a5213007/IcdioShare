package dao.Question;

import java.util.List;
import java.util.Map;

import dao.baseDao.IBaseDao;
import util.connectUtil.*;
import util.operateObject.ObjectToSQL;

public class QuestionDao extends CommonDAO implements IBaseDao{

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
	//***************************************************************************************************
	/**
	 * 跳页(管理)
	 * */
	public List<Map<String, Object>> getInfoByPageAdmin(int page) throws Exception{
		String sql = "select question.id, tel_and_act.id as telAndActID,user.name, tel_and_act.title, user.id as userID, questionContent, askDate from question left join user on(user.id = question.userID) left join tel_and_act on(tel_and_act.id = question.telAndActID) limit " + (page - 1) * 10  + ", 10";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	}
	
	/**
	 * 获取页数页(管理)
	 * */
	public List<Map<String, Object>> getAdminInfoPage() throws Exception{
		String sql = "select count(*) / 10 as page from question";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	}
	//***************************************************************************************************
	
	//***************************************************************************************************
	/**
	 * 跳页(个人)
	 * */
	public List<Map<String, Object>> getInfoByPageUser(int page, Long userId) throws Exception{
		String sql = "select question.id, tel_and_act.id as telAndActID,user.name, tel_and_act.title, user.id as userID, questionContent, askDate from question left join user on(user.id = question.userID) left join tel_and_act on(tel_and_act.id = question.telAndActID) where user.id = "+userId+" limit " + (page - 1) * 10  + ", 10";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	}
	
	/**
	 * 获取页数页(个人)
	 * */
	public List<Map<String, Object>> getUserInfoPage(Long userId) throws Exception{
		String sql = "select count(*) / 10 as page from question where userID = " + userId;
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	}
	//***************************************************************************************************
	
	public List<Map<String, Object>> getAllInfo() throws Exception{
		String sql = "select * from question";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	}
	
	public List<Map<String, Object>> getInfoById(Long id) throws Exception{
		String sql = "select * from question where id = " + id;
		return excuteQuery(sql, null);
	}
	
	public int delete(Long id) throws Exception{
		String sql = "delete from question where id = " + id;
		executeSql(sql);
		return 1;
	}
	
	public List<Map<String, Object>>  getInfoByTechnologyId(Long technologyId){
		String sql = "select question.id, user.id as userID, telAndActID, user.name, questionContent, askDate from" + 
				" question  left join user on(user.id = question.userID) where telAndActID = " + technologyId +
				" ORDER BY askDate asc";		
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	}
	
	public List<Map<String, Object>> findByKeyAndValue(String key, String value, int page){
		String sql = "select * from question where " + key + " like '%" + value + "%' limit " + (page - 1) * 10 + ", 10";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	}
	
	public List<Map<String, Object>> findByKeyAndValuePage(String key, String value, int page){
		String sql = "select count(*) / 10 as page from question where " + key + " like '%" + value + "%' limit " + (page - 1) * 10 + ", 10";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	}
}