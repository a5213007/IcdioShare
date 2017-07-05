package dao.Answer;

import java.util.List;
import java.util.Map;

import dao.baseDao.IBaseDao;
import util.connectUtil.*;
import util.operateObject.ObjectToSQL;

public class AnswerDao extends CommonDAO implements IBaseDao{

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
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return 1;
	}
	
	public List<Map<String, Object>> getAllInfo() throws Exception{
		String sql = "select * from answer";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	}
	
	//*************************************************************************************
	/**
	 * 跳页(管理)
	 * */
	public List<Map<String, Object>> getInfoByPageAdmin(int page) throws Exception{
		String sql = "select answer.id, question.id as questionID, question.questionContent,user.id as userID,user.name,answerContent,answerDate from answer left join user on(user.id = answer.userID) left join question on (question.id = answer.questionID) limit " + (page - 1) * 10  + ", 10";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	}
	
	/**
	 * 获取页数(管理)
	 * */
	public List<Map<String, Object>> getAdminInfoPage() throws Exception{
		String sql = "select count(*) / 10 as page from answer";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	}
	//*************************************************************************************
	
	//*************************************************************************************
		/**
		 * 跳页(个人)
		 * */
		public List<Map<String, Object>> getInfoByPageUser(int page, Long userId) throws Exception{
			String sql = "select answer.id, question.id as questionID, question.questionContent,user.id as userID,user.name,answerContent,answerDate from answer left join user on(user.id = answer.userID) left join question on (question.id = answer.questionID) where user.id = "+userId+" limit " + (page - 1) * 10  + ", 10";
			System.out.println("----------------------------------------");
			System.out.println("SQL:" + sql);
			return excuteQuery(sql, null);
		}
		
		/**
		 * 获取页数(个人)
		 * */
		public List<Map<String, Object>> getUserInfoPage(Long userId) throws Exception{
			String sql = "select count(*) / 10 as page from answer where userID = " + userId;
			System.out.println("----------------------------------------");
			System.out.println("SQL:" + sql);
			return excuteQuery(sql, null);
		}
		//*************************************************************************************
	
	public List<Map<String, Object>> getInfoById(Long id) throws Exception{
		String sql = "select * from answer where id = " + id;
		return excuteQuery(sql, null);
	}
	
	public int delete(Long id) throws Exception{
		String sql = "delete from answer where id = " + id;
		executeSql(sql);
		return 1;
	}
	
	public List<Map<String, Object>> getInfoByQuestionId(Long questionId){
		String sql = "select answer.id, user.id as userID, questionID, user.name, answerContent,  answerDate" +
				" from answer  left join user on(user.id = answer.userID) where questionID = " + questionId + 
				" order by answerDate asc";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	}
	
	public List<Map<String, Object>> findByKeyAndValue(String key, String value, int page){
		String sql = "select answer.id, user.id as userID,question.questionContent, answer.questionID, user.name, answerContent,  answerDate" +
				" from answer  left join user on(user.id = answer.userID) LEFT JOIN question ON (question.id = answer.questionID) where " +
				key + " like '%" + value + "%' order by answerDate asc limit " + (page - 1) * 10 + ", 10";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	}
	
	public List<Map<String, Object>> findByKeyAndValuePage(String key, String value, int page){
		String sql = "select count(*) / 10 as page from answer where " + key + " like '%" + value + "%' limit " + (page - 1) * 10 + ", 10";
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	}
}
