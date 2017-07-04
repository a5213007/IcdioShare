package service.Tel_And_Act;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import service.Answer.AnswerService;
import service.Evaluation.EvaluationService;
import service.Perssions.PerssionsService;
import service.Process.ProcessService;
import service.Question.QuestionService;
import service.baseService.IBaseService;
import util.operateObject.Tool;
import dao.Tel_And_Act.Tel_And_ActDao;
import entity.Tel_And_Act.Tel_And_Act;

public class Tel_And_ActService implements IBaseService{
	private Tel_And_ActDao tel_and_actDao = new Tel_And_ActDao();
	private PerssionsService perssionsService = new PerssionsService();

	public int save(Object object) throws Exception{
		try {
			//将英文双引号转化为中文双引号		
			((Tel_And_Act)object).setTitle(Tool.replace(((Tel_And_Act)object).getTitle()));
			((Tel_And_Act)object).setContent(Tool.replace(((Tel_And_Act)object).getContent()));
			
			if("活动通知".equals(((Tel_And_Act)object).getType())){
				((Tel_And_Act)object).setActivePlace(Tool.replace(((Tel_And_Act)object).getActivePlace()));
			}
			
			//增加状态
			((Tel_And_Act)object).setState("已提交");
			
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
	
	public List<Map<String, Object>> getActiveWithEvaluation(Long id){
		try {
			EvaluationService evaluationService = new EvaluationService();
			List<Map<String, Object>> list = tel_and_actDao.getActiveAndUserById(id);
			
			if(list.size() != 0){
				List<Map<String, Object>> evaluationList = evaluationService.getInfoByActiveId(id);
				list.addAll(evaluationList);
				return list;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Map<String, Object>> getAllTechnologyByPage(int page, Long userId){
		try {
			
			//拥有权限能查看所有的
			if(perssionsService.hasPerssions("TechnologyCtr", userId) || perssionsService.hasPerssions("TechnologyCtr_display", userId)){
				List<Map<String, Object>> telList =  tel_and_actDao.getAllTechnologyByPageAdmin(page);
				telList.add(tel_and_actDao.getAllTechnologyPageAdmin().get(0));
				
				return telList;
				
			//没有权限的只能查看自己的	
			}else {
				List<Map<String, Object>> telList =  tel_and_actDao.getAllTechnologyByPage(page, userId);
				telList.add(tel_and_actDao.getAllTechnologyPage(userId).get(0));
				
				return telList;
			}
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}