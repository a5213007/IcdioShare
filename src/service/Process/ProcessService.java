package service.Process;

import java.util.List;
import java.util.Map;

import service.baseService.IBaseService;
import dao.Process.ProcessDao;
import entity.Process.Process;

public class ProcessService implements IBaseService{
	private ProcessDao processDao = new ProcessDao();

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