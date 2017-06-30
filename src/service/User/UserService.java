package service.User;

import java.util.List;
import java.util.Map;
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
	
	public int update(Object object) throws Exception{		
		try {
			return userDao.update(object);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	public List<Map<String, Object>> getAllInfo() throws Exception{
		try {
			return userDao.getAllInfo();	
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
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
				list.get(0).remove("password");
				return list;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}