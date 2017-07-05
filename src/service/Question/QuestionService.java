package service.Question;

import java.util.List;
import java.util.Map;

import oracle.net.aso.q;
import service.Permissions.PermissionsService;
import service.baseService.IBaseService;
import dao.Question.QuestionDao;

public class QuestionService implements IBaseService{
	private QuestionDao questionDao = new QuestionDao();
	private PermissionsService permissionsService = new PermissionsService();
	
	public int save(Object object) throws Exception{
		try {
			return questionDao.save(object);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}				
	}
	
	public int update(Object object) throws Exception{		
		try {
			return questionDao.update(object);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	public List<Map<String, Object>> getAllInfo(){
		try {
			return questionDao.getAllInfo();	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Map<String, Object>> getInfoById(Long id) throws Exception{		
		try {
			return questionDao.getInfoById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	//跳页（通过当前页获取数据）
	public List<Map<String, Object>> getInfoByPage(int page, Long userId){
		try {
			if(permissionsService.hasPerssions("QuestionCtr", userId) || permissionsService.hasPerssions("QuestionCtr_display", userId)){
				List<Map<String, Object>> evaluationList = questionDao.getInfoByPageAdmin(page);		
				List<Map<String, Object>> pageList = questionDao.getAdminInfoPage();
			
				evaluationList.add(pageList.get(0));
				return evaluationList;
				
			//没有权限的只能查看自己的	
			}else {
				List<Map<String, Object>> evaluationList =  questionDao.getInfoByPageUser(page, userId);
				
				evaluationList.add(questionDao.getUserInfoPage(userId).get(0));
				
				return evaluationList;
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
		
	public int delete(Long id) throws Exception{
		try {
			return questionDao.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return -1;
	}
	
	public List<Map<String, Object>> getInfoByTechnologyId(Long technologyId){
		try {
			return questionDao.getInfoByTechnologyId(technologyId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public List<Map<String, Object>> findByKeyAndValue(String key, String value, int page){
		try {
			List<Map<String, Object>> list = questionDao.findByKeyAndValue(key, value, page);
			List<Map<String, Object>> list1 = questionDao.findByKeyAndValuePage(key, value, page);
			list.add(list1.get(0));
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return null;
	}
}