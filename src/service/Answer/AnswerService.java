package service.Answer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import oracle.net.aso.q;
import service.Permissions.PermissionsService;
import service.baseService.IBaseService;
import dao.Answer.AnswerDao;

public class AnswerService implements IBaseService{
	private AnswerDao answerDao = new AnswerDao();
	private PermissionsService permissionsService = new PermissionsService();
	
	public int save(Object object) throws Exception{
		try {
			return answerDao.save(object);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}				
	}
	
	public int update(Object object) throws Exception{		
		try {
			return answerDao.update(object);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	
	public List<Map<String, Object>> getAllInfo(){
		try {
			return answerDao.getAllInfo();	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//跳页（通过当前页获取数据）
	public List<Map<String, Object>> getInfoByPage(int page, Long userId){
		try {
			if(permissionsService.hasPerssions("AnswerCtr", userId) || permissionsService.hasPerssions("AnswerCtr_display", userId)){
				List<Map<String, Object>> evaluationList = answerDao.getInfoByPageAdmin(page);		
				List<Map<String, Object>> pageList = answerDao.getAdminInfoPage();
			
				evaluationList.add(pageList.get(0));
				return evaluationList;
				
			//没有权限的只能查看自己的	
			}else {
				List<Map<String, Object>> evaluationList =  answerDao.getInfoByPageUser(page, userId);
				
				evaluationList.add(answerDao.getUserInfoPage(userId).get(0));
				
				return evaluationList;
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Map<String, Object>> getInfoById(Long id) throws Exception{		
		try {
			return answerDao.getInfoById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	public int delete(Long id) throws Exception{
		try {
			return answerDao.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}		
	}
	
	public List<Map<String, Object>> getInfoByQuestionId(Set<Long> questionIdSet){
		try {
			Iterator<Long> iterator = questionIdSet.iterator();
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			
			while(iterator.hasNext()){
				List<Map<String, Object>> temp = answerDao.getInfoByQuestionId(iterator.next());
				list.addAll(temp);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Map<String, Object>> findByKeyAndValue(String key, String value,String type, int page, Long id){
		try {
			if(permissionsService.hasPerssions("QuestionCtr", id) || permissionsService.hasPerssions("QuestionCtr_display", id)){
				List<Map<String, Object>> list = answerDao.findByKeyAndValue(key, value,type, page,null);
				List<Map<String, Object>> list1 = answerDao.findByKeyAndValuePage(key, value,type, page,null);
				list.add(list1.get(0));
				return list;
			}else {
				List<Map<String, Object>> list = answerDao.findByKeyAndValue(key, value,type, page,id);
				List<Map<String, Object>> list1 = answerDao.findByKeyAndValuePage(key, value,type, page,id);
				list.add(list1.get(0));
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return null;
	}
}