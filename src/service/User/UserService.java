package service.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.org.apache.bcel.internal.generic.NEW;

import oracle.net.aso.a;
import oracle.net.aso.u;
import service.baseService.IBaseService;
import dao.User.UserDao;

public class UserService implements IBaseService{
	private UserDao userDao = new UserDao();

	public int save(Object object) throws Exception{
		try {
			return userDao.save(object);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}				
	}
	
	public int updateTo(Map<String, Object> map) throws Exception{		
		try {
			return userDao.updateTo(map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	public int updatePd(String account, String password) throws Exception{		
		try {
			return userDao.updatePd(account,password);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	public List<Map<String, Object>> getInfoByPage(int page){
		try {
			List<Map<String, Object>> userList = userDao.getPageInfo(page);		
			List<Map<String, Object>> pageList = userDao.getAllInfoPage();
			
			userList.add(pageList.get(0));
			return userList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public int update(Object object) throws Exception{		
		try {
			return userDao.update(object);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	public List<Map<String, Object>> getAllInfo(){
		try {
			return userDao.getAllInfo();	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Map<String, Object>> getInfoById(Long id) throws Exception{		
		try {
			return userDao.getInfoById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	public int delete(Long id) throws Exception{
		try {
			return userDao.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}		
	}
	
	public List<Map<String, Object>> getInfoByAccount(String account){
		try {
			return userDao.getInfoByAccount(account);
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return null;
	}
	
	public List<Map<String, Object>> login(String account, String password){
		try {
			List<Map<String, Object>> list = getInfoByAccount(account);
			
			if(list.size() == 0)
				return null;
			
			if(password.equals(list.get(0).get("password"))){
				Map<String, Object> map = list.get(0);
				map.remove("password");
				
				List<Map<String, Object>> userInfo = userDao.getViewByAccount(account);
				if(userInfo.size() != 0){
					String[] permissions = new String[userInfo.size()];
					
					for (int i = 0; i < userInfo.size(); i++) 
						permissions[i] = userInfo.get(i).get("sign") + "";
	
					map.put("sign", permissions);
						
					list.clear();
					list.add(map);
				}else{
					list.get(0).put("sign", "");
				}
				
				return list;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}