package action.User;

import java.util.List;
import java.util.Map;
import action.baseAction.IBaseAction;
import service.User.UserService;

public class UserAction implements IBaseAction{
	private UserService userservice = new UserService();

	public int save(Object object) throws Exception{
		return userservice.save(object);		
	}
	
	public int update(Object object) throws Exception{
		return userservice.update(object);
	}
	
	public List<Map<String, Object>> getAllInfo() throws Exception{
		return userservice.getAllInfo();
	}
	
	public List<Map<String, Object>> getInfoById(Long id) throws Exception{
		return userservice.getInfoById(id);
	}
	
	public int delete(Long id) throws Exception{
		return userservice.delete(id);
	}
}