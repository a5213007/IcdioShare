package service.Evaluation;

import java.util.List;
import java.util.Map;
import service.baseService.IBaseService;
import dao.Evaluation.EvaluationDao;

public class EvaluationService implements IBaseService{
	private EvaluationDao evaluationDao = new EvaluationDao();

	public int save(Object object) throws Exception{
		try {
			return evaluationDao.save(object);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}				
	}
	
	public int update(Object object) throws Exception{		
		try {
			return evaluationDao.update(object);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	public List<Map<String, Object>> getAllInfo() throws Exception{
		try {
			return evaluationDao.getAllInfo();	
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	public List<Map<String, Object>> getInfoById(Long id) throws Exception{		
		try {
			return evaluationDao.getInfoById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	public int delete(Long id) throws Exception{
		try {
			return evaluationDao.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}		
	}
}