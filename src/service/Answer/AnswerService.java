package service.Answer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import oracle.net.aso.q;
import service.baseService.IBaseService;
import dao.Answer.AnswerDao;

public class AnswerService implements IBaseService{
	private AnswerDao answerDao = new AnswerDao();

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
}