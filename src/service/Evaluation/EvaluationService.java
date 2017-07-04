package service.Evaluation;

import java.util.List;
import java.util.Map;

import service.Permissions.PermissionsService;
import service.baseService.IBaseService;
import dao.Evaluation.EvaluationDao;

public class EvaluationService implements IBaseService{
	private EvaluationDao evaluationDao = new EvaluationDao();
	private PermissionsService permissionsService = new PermissionsService();
	
	public int save(Object object) {
		try {
			return evaluationDao.save(object);
		} catch (Exception e) {
			e.printStackTrace();
			
		}	
		return -1;
	}
	
	public int update(Object object) {		
		try {
			return evaluationDao.update(object);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return -1;
	}
	
	public List<Map<String, Object>> getAllInfo() {
		try {
			return evaluationDao.getAllInfo();	
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return null;
	}
	
	
	//跳页（通过当前页获取数据）
	public List<Map<String, Object>> getInfoByPage(int page, Long userId){
		try {
			if(permissionsService.hasPerssions("TechnologyCtr", userId) || permissionsService.hasPerssions("TechnologyCtr_display", userId)){
				List<Map<String, Object>> evaluationList = evaluationDao.getInfoByPageAdmin(page);		
				List<Map<String, Object>> pageList = evaluationDao.getAdminInfoPage();
			
				evaluationList.add(pageList.get(0));
				return evaluationList;
				
			//没有权限的只能查看自己的	
			}else {
				List<Map<String, Object>> evaluationList =  evaluationDao.getInfoByPageUser(page, userId);
				
				evaluationList.add(evaluationDao.getUserInfoPage(userId).get(0));
				
				return evaluationList;
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Map<String, Object>> getInfoById(Long id){		
		try {
			return evaluationDao.getInfoById(id);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}
	
	public int delete(Long id){
		try {
			return evaluationDao.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public List<Map<String, Object>>  getInfoByActiveId(Long id){
		try {
			return evaluationDao.getInfoByActiveId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}