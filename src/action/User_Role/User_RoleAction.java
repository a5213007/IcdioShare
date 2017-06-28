package action.User_Role;

import java.util.List;
import java.util.Map;
import action.baseAction.IBaseAction;
import service.User_Role.User_RoleService;

public class User_RoleAction implements IBaseAction{
	private User_RoleService user_roleservice = new User_RoleService();

	public int save(Object object) throws Exception{
		return user_roleservice.save(object);		
	}
	
	public int update(Object object) throws Exception{
		return user_roleservice.update(object);
	}
	
	public List<Map<String, Object>> getAllInfo() throws Exception{
		return user_roleservice.getAllInfo();
	}
	
	public List<Map<String, Object>> getInfoById(Long id) throws Exception{
		return user_roleservice.getInfoById(id);
	}
	
	public int delete(Long id) throws Exception{
		return user_roleservice.delete(id);
	}
}