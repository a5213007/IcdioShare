package service.Role;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import service.baseService.IBaseService;
import util.operateObject.Tool;
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
	
	/**
	 * 通过用户ID获取权限
	 * */
	public List<Map<String, Object>> getRoleByUserId(Long userId){
		try {
			List<Map<String, Object>> list = roleDao.getRoleByUserId(userId);
			Set<String> roleSet = new HashSet<String>();
			
			for (int i = 0; i < list.size(); i++) 
				roleSet.add(list.get(i).get("roleID") + "");
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("role", roleSet.toArray());
			
			list.clear();
			list.add(map);
			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 将角色分配给用户
	 * */
	public void addRoleToUser(Long userId, JSONArray json) throws Exception{
		try {
			Map<String, Object> map = (Map<String, Object>) json.get(0);
			
			for(int i = 0; i < map.size(); i++){
				roleDao.addRoleToUser(Tool.getID(), map.get(i+"")+"", userId);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	/**
	 * 将用户角色移除
	 * */
	public void removeAllRole(Long userId) throws Exception{
		try {
			roleDao.removeAllRole(userId);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
}