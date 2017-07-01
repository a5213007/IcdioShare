package service.Tel_And_Act;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import oracle.net.aso.q;
import service.Answer.AnswerService;
import service.Question.QuestionService;
import service.baseService.IBaseService;
import dao.Tel_And_Act.Tel_And_ActDao;

public class Tel_And_ActService implements IBaseService{
	private Tel_And_ActDao tel_and_actDao = new Tel_And_ActDao();

	public int save(Object object) throws Exception{
		try {
			return tel_and_actDao.save(object);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}				
	}
	
	public int update(Object object) throws Exception{		
		try {
			return tel_and_actDao.update(object);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	public List<Map<String, Object>> getAllInfo() throws Exception{
		try {
			return tel_and_actDao.getAllInfo();	
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	public List<Map<String, Object>> getInfoById(Long id) throws Exception{		
		try {
			return tel_and_actDao.getInfoById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	public int delete(Long id) throws Exception{
		try {
			return tel_and_actDao.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}		
	}
	
	public List<Map<String, Object>> getAllActiveInfo(){		
		try {
			return tel_and_actDao.getAllActiveInfo();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	} 
	
	public List<Map<String, Object>> getAllTechnologyInfo(){		
		try {
			return tel_and_actDao.getAllTechnologyInfo();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	} 
	
	public List<Map<String, Object>> getAllTechnologyInfoByIndex(int index){		
		try {
//			List<Map<String, Object>> list = tel_and_actDao.getAllTechnologyInfo();
//			List<Map<String, Object>> list2 = new ArrayList<>();
//			
//			if(list.size() >= (index - 1) * 10) {
//				for (int i = (index - 1) * 10; i < (list.size() >= index* 10 ? index * 10:list.size());i++)
//					list2.add(list.get(i));
//				return list2;
//			}
			
			return tel_and_actDao.getAllTechnologyByIndex(index);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	} 
	
	public List<Map<String, Object>> getAllActiveInfoByIndex(int index){		
		try {
//			List<Map<String, Object>> list = tel_and_actDao.getAllActiveInfo();
//			List<Map<String, Object>> list2 = new ArrayList<>();
//			
//			if(list.size() >= (index - 1) * 10) {
//				for (int i = (index - 1) * 10; i < (list.size() >= index* 10 ? index * 10:list.size());i++)
//					list2.add(list.get(i));
//				return list2;
//			}
			
			return tel_and_actDao.getAllActiveInfoByIndex(index);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Map<String, Object>> getTechnologyWithQuestionAndAnswer(Long id){
		try {
			QuestionService questionService = new QuestionService();
			AnswerService answerService = new AnswerService();
			
			List<Map<String, Object>> teyList = tel_and_actDao.getTechnologyAndUserById(id);
			List<Map<String, Object>> questionList = questionService.getInfoByTechnologyId(id);
			Set<Long> quesSet = new HashSet<Long>();
			
			for (int i = 0; i < questionList.size(); i ++) {
				quesSet.add(Long.parseLong(questionList.get(i).get("id")+""));
			}
			
			if(questionList.size() != 0){
				List<Map<String, Object>> answerList = answerService.getInfoByQuestionId(quesSet);
				teyList.addAll(questionList);
				teyList.addAll(answerList);
			}
			
			return teyList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}