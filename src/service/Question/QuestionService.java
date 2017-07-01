package service.Question;

import java.util.List;
import java.util.Map;

import oracle.net.aso.q;
import service.baseService.IBaseService;
import dao.Question.QuestionDao;

public class QuestionService implements IBaseService{
	private QuestionDao questionDao = new QuestionDao();

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
	
	public List<Map<String, Object>> getAllInfo() throws Exception{
		try {
			return questionDao.getAllInfo();	
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	public List<Map<String, Object>> getInfoById(Long id) throws Exception{		
		try {
			return questionDao.getInfoById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
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
}