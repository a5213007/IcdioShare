package service.Permissions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import service.baseService.IBaseService;
import util.operateObject.Tool;
import dao.Permissions.PermissionsDao;

public class PermissionsService implements IBaseService{
	private PermissionsDao perssionsDao = new PermissionsDao();

	public int save(Object object) throws Exception{
		try {
			return perssionsDao.save(object);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}				
	}
	
	public int update(Object object) throws Exception{		
		try {
			return perssionsDao.update(object);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	public List<Map<String, Object>> getAllInfo(){
		try {
			return perssionsDao.getAllInfo();	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Map<String, Object>> getInfoById(Long id) throws Exception{		
		try {
			return perssionsDao.getInfoById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	public List<Map<String, Object>> getInfoByPage(int page){
		try {
			List<Map<String, Object>> perssionsList = perssionsDao.getPageInfo(page);		
			List<Map<String, Object>> pageList = perssionsDao.getAllInfoPage();
			
			perssionsList.add(pageList.get(0));
			return perssionsList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public int delete(Long id) throws Exception{
		try {
			return perssionsDao.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}		
	}
	
	/**
	 * 获取所有权限
	 * */
	public List<Map<String, Object>> hasPerssions(Long userId){
		try {
			List<Map<String, Object>> list = perssionsDao.hasPermissions(userId);
			List<Map<String, Object>> temList = new ArrayList<Map<String,Object>>();
			
			if(list.size() != 0){
				String[] permissions = new String[list.size()];
				Map<String, Object> map = new HashMap<String, Object>();
				
				for(int i = 0; i < list.size(); i++){
					permissions[i] = list.get(i).get("sign").toString();
				}
				list.clear();
				map.put("sign", permissions);
				list.add(map);
			}
			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return null;
	}
	
	/**
	 * 判断有无权限
	 * */
	public boolean hasPerssions(String signName, Long userId){
		try {
			if(perssionsDao.hasPermissions(signName, userId).size() != 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return false;
	}
	
	public List<Map<String, Object>> findByKeyAndValue(String key, String value, int page){
		try {
			List<Map<String, Object>> list = perssionsDao.findByKeyAndValue(key, value, page);
			List<Map<String, Object>> list1 = perssionsDao.findByKeyAndValuePage(key, value, page);
			list.add(list1.get(0));
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return null;
	}
	
	/**
	 * 通过角色ID获取权限
	 * */
	public List<Map<String, Object>> getPerssionsByRoleId(Long roleId){
		try {
			List<Map<String, Object>> list = perssionsDao.getPerssionsByRoleId(roleId);
			Set<String> permissionsSet = new HashSet<String>();
			
			for (int i = 0; i < list.size(); i++) 
				permissionsSet.add(list.get(i).get("perssionsID") + "");
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("permissions", permissionsSet.toArray());
			
			list.clear();
			list.add(map);
			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 将权限分配给角色
	 * */
	public void addPermissionsToRole(Long roleId, JSONArray json) throws Exception{
		try {
			Map<String, Object> map = (Map<String, Object>) json.get(0);
			
			for(int i = 0; i < map.size(); i++){
				perssionsDao.addPermissionsToRole(Tool.getID(), map.get(i+"")+"", roleId);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	/**
	 * 将角色权限移除
	 * */
	public void removeAllPermissions(Long roleId) throws Exception{
		try {
			perssionsDao.removeAllPermissions(roleId);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
}