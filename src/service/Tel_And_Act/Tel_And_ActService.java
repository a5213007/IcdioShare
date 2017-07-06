package service.Tel_And_Act;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import service.Answer.AnswerService;
import service.Evaluation.EvaluationService;
import service.Permissions.PermissionsService;
import service.Process.ProcessService;
import service.Question.QuestionService;
import service.baseService.IBaseService;
import util.operateObject.JsonToObject;
import util.operateObject.Tool;
import dao.Tel_And_Act.Tel_And_ActDao;
import entity.Tel_And_Act.Tel_And_Act;

public class Tel_And_ActService implements IBaseService{
	private Tel_And_ActDao tel_and_actDao = new Tel_And_ActDao();
	private PermissionsService perssionsService = new PermissionsService();

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
	
	public void changeState(String state, Long id){
		try {
			List<Map<String, Object>> list = getInfoById(id);
			
			if(list.size() != 0){
				list.get(0).put("state", state);
				
				JSONArray json = JSONArray.fromObject(list);
				
				update(JsonToObject.jsonToObj("Tel_And_Act", json));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
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
	
	//后台活动通知展示
	public List<Map<String, Object>> getAllActiveByPage(int page, Long userId){
		try {
			
			//拥有权限能查看所有的
			if(perssionsService.hasPerssions("ActiveCtr", userId) || perssionsService.hasPerssions("ActiveCtr_display", userId)){
				List<Map<String, Object>> telList =  tel_and_actDao.getAllActiveByPageAdmin(page);
				telList.add(tel_and_actDao.getAllActivePageAdmin().get(0));
				
				return telList;
				
			//没有权限的只能查看自己的	
			}else {
				List<Map<String, Object>> telList =  tel_and_actDao.getAllActiveByPage(page, userId);
				telList.add(tel_and_actDao.getAllActivePage(userId).get(0));
				
				return telList;
			}
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Map<String, Object>> findByKeyAndValue(String key, String value,String type, int page, Long id){
		try {
			String Type = type.equals("Technology") ? "技术分享" : "活动通知";
			
			if((type.equals("Technology") && (perssionsService.hasPerssions("TechnologyCtr", id) || perssionsService.hasPerssions("TechnologyCtr_display", id))) ||
					(type.equals("Active") && (perssionsService.hasPerssions("ActiveCtr", id) || perssionsService.hasPerssions("ActiveCtr_display", id)))){
				Tel_And_ActDao tel_And_ActDao = new Tel_And_ActDao();
				List<Map<String, Object>> list = tel_And_ActDao.findByKeyAndValue(key, value,Type, page, null);
				List<Map<String, Object>> list1 = tel_And_ActDao.findByKeyAndValuePage(key, value,Type, page, null);
				
				list.add(list1.get(0));
				return list;
			}else{
				Tel_And_ActDao tel_And_ActDao = new Tel_And_ActDao();
				List<Map<String, Object>> list = tel_And_ActDao.findByKeyAndValue(key, value,Type, page,id);
				List<Map<String, Object>> list1 = tel_And_ActDao.findByKeyAndValuePage(key, value,Type, page,id);
				
				list.add(list1.get(0));
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return null;
	}
}