package service.Role;

import java.util.List;
import java.util.Map;

import service.baseService.IBaseService;
import dao.Role.RoleDao;

public class RoleService implements IBaseService{
	private RoleDao roleDao = new RoleDao();

	public int save(Object object) throws Exception{
		try {
			return roleDao.save(object);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}				
	}
	
	public int update(Object object) throws Exception{		
		try {
			return roleDao.update(object);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	public List<Map<String, Object>> getAllInfo(){
		try {
			return roleDao.getAllInfo();	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Map<String, Object>> getInfoByPage(int page){
		try {
			List<Map<String, Object>> roleList = roleDao.getPageInfo(page);		
			List<Map<String, Object>> pageList = roleDao.getAllInfoPage();
			
			roleList.add(pageList.get(0));
			return roleList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Map<String, Object>> getInfoById(Long id) throws Exception{		
		try {
			return roleDao.getInfoById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	public int delete(Long id) throws Exception{
		try {
			return roleDao.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}		
	}
	
	
	public List<Map<String, Object>> findByKeyAndValue(String key, String value, int page){
		try {
			List<Map<String, Object>> list = roleDao.findByKeyAndValue(key, value, page);
			List<Map<String, Object>> list1 = roleDao.findByKeyAndValuePage(key, value, page);
			list.add(list1.get(0));
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return null;
	}
}