package service.Process;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import service.Tel_And_Act.Tel_And_ActService;
import service.baseService.IBaseService;
import util.operateObject.JsonToObject;
import dao.Process.ProcessDao;
import entity.Process.Process;

public class ProcessService implements IBaseService{
	private ProcessDao processDao = new ProcessDao();
	private Tel_And_ActService tel_And_ActService = new Tel_And_ActService();
	
	public int save(Object object) throws Exception{
		try {
			return processDao.save(object);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}				
	}
	
	public int update(Object object) throws Exception{		
		try {
			return processDao.update(object);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	public List<Map<String, Object>> getAllInfo(){
		try {
			return processDao.getAllInfo();	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void changeState(String judge, JSONArray json){
		try {
			//更新流程
			Map<String, Object> map = (Map<String, Object>) json.get(0);
			Long id = Long.parseLong( map.get("id") + "");		
			List<Map<String, Object>> list = getInfoById(id);
			String state = "";
			if(judge.equals("true"))
				state = "已同意";
			else 
				state = "已驳回";
				list.get(0).put("state", state);
			
			list.get(0).put("reviewID", map.get("reviewID"));
			list.get(0).put("reviewDate", map.get("reviewDate"));
			
			//更新活动通知
			
			List<Map<String, Object>> activeList = tel_And_ActService.getInfoById(Long.parseLong(list.get(0).get("telAndActID")+""));
			list.get(0).put("state", state);
			tel_And_ActService.update(JsonToObject.jsonToObj("Tel_And_Act", JSONArray.fromObject(list)));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 新增活动通知时新增流程通知
	 * */
	public void add(Map<String, Object> map){
		try {
			Process process = new Process();
			process.setId(map.get("id") + "");
			process.setTelAndActID(map.get("id") + "");
			process.setReleaseDate(map.get("releaseDate") + "");
			process.setSubmitID(map.get("userID") + "");
			process.setState("已提交");
			
			save(process);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Map<String, Object>> getInfoByPage(int page){
		try {
			List<Map<String, Object>> processList = processDao.getPageInfo(page);		
			List<Map<String, Object>> pageList = processDao.getAllInfoPage();
			
			processList.add(pageList.get(0));
			return processList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Map<String, Object>> getInfoById(Long id) throws Exception{		
		try {
			return processDao.getInfoById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	public int delete(Long id) throws Exception{
		try {
			return processDao.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}		
	}
	
	public List<Map<String, Object>> findByKeyAndValue(String key, String value, int page){
		try {
			List<Map<String, Object>> list = processDao.findByKeyAndValue(key, value, page);
			List<Map<String, Object>> list1 = processDao.findByKeyAndValuePage(key, value, page);
			list.add(list1.get(0));
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return null;
	}
}