package dao.Evaluation;

import java.util.List;
import java.util.Map;

import dao.baseDao.IBaseDao;
import util.connectUtil.*;
import util.operateObject.ObjectToSQL;

public class EvaluationDao extends CommonDAO implements IBaseDao{

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
	
	
	//********************************************************************************
	/**
	 * 跳页(管理)
	 * */
	public List<Map<String, Object>> getInfoByPageAdmin(int page) throws Exception{
		String sql = "SELECT evaluation.id,	telAndActID,tel_and_act.title,telAndActID,"+
				"user.name,evaluationContent,evalutionDate FROM	evaluation LEFT JOIN "+
				" tel_and_act ON (tel_and_act.id = evaluation.telAndActID) LEFT JOIN user"+
				" ON (user .id = evaluation.userID) limit " + (page - 1) * 10  + ", 10";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	}
	
	/**
	 * 获取页数(管理)
	 * */
	public List<Map<String, Object>> getAdminInfoPage() throws Exception{
		String sql = "select count(*) / 10 as page from evaluation";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	}
	//********************************************************************************
	
	//********************************************************************************
		/**
		 * 跳页(个人)
		 * */
		public List<Map<String, Object>> getInfoByPageUser(int page, Long userId) throws Exception{
			String sql = "SELECT evaluation.id,	telAndActID,tel_and_act.title,telAndActID,user.id as userID,"+
					"user.name,evaluationContent,evalutionDate FROM	evaluation LEFT JOIN "+
					" tel_and_act ON (tel_and_act.id = evaluation.telAndActID) LEFT JOIN user"+
					" ON (user .id = evaluation.userID) where user.id = " +userId+" limit " + (page - 1) * 10  + ", 10";
			System.out.println("----------------------------------------");
			System.out.println("SQL:" + sql);
			return excuteQuery(sql, null);
		}
		
		/**
		 * 获取页数(个人)
		 * */
		public List<Map<String, Object>> getUserInfoPage(Long userId) throws Exception{
			String sql = "select count(*) / 10 as page from evaluation where userID = " + userId;
			System.out.println("----------------------------------------");
			System.out.println("SQL:" + sql);
			return excuteQuery(sql, null);
		}
		//********************************************************************************
	
	public List<Map<String, Object>> getAllInfo() throws Exception{
		String sql = "select * from evaluation";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	}
	
	public List<Map<String, Object>> getInfoById(Long id) throws Exception{
		String sql = "select * from evaluation where id = " + id;
		return excuteQuery(sql, null);
	}
	
	public int delete(Long id) throws Exception{
		String sql = "delete from evaluation where id = " + id;
		executeSql(sql);
		return 1;
	}
	
	public List<Map<String, Object>>  getInfoByActiveId(Long id) throws Exception{
		String sql = "select telAndActID, user.id, user.name, evaluationContent," +
				"evalutionDate,Type from evaluation LEFT JOIN user on(user.id = "+
				"evaluation.userID) where telAndActID = "+ id + " ORDER BY evalutionDate asc";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	}
	
	public List<Map<String, Object>> findByKeyAndValue(String key, String value,String type, int page, Long userId){
		String userArg = "";
		if(userId != null)
			userArg = " and user.id = " + userId;
		
		String sql = "select evaluation.id,tel_and_act.title, user.id, user.name as name, evaluationContent," +
				"evalutionDate,evaluation.Type from evaluation LEFT JOIN user on(user.id = "+
				"evaluation.userID)LEFT JOIN tel_and_act ON (evaluation.telAndActID = tel_and_act.id" +
				") where " + key + " like '%" + value + "%' "+userArg+" ORDER BY evalutionDate asc limit " + (page - 1) * 10 + ", 10";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	}
	
	public List<Map<String, Object>> findByKeyAndValuePage(String key, String value,String type, int page, Long userId){
		String userArg = "";
		if(userId != null)
			userArg = " and user.id = " + userId;
		
		String sql = "select count(*) / 10 as page from evaluation LEFT JOIN user on(user.id = evaluation.userID)LEFT JOIN tel_and_act ON (evaluation.telAndActID = tel_and_act.id) where " + key + " like '%" + value + "%' "+userArg+" limit " + (page - 1) * 10 + ", 10";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	}
}