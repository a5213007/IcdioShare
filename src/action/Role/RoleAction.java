package action.Role;

import java.util.List;
import java.util.Map;
import action.baseAction.IBaseAction;
import service.Role.RoleService;

public class RoleAction implements IBaseAction{
	private RoleService roleservice = new RoleService();

	public int save(Object object) throws Exception{
		return roleservice.save(object);		
	}
	
	public int update(Object object) throws Exception{
		return roleservice.update(object);
	}
	
	public List<Map<String, Object>> getAllInfo() throws Exception{
		return roleservice.getAllInfo();
	}
	
	public List<Map<String, Object>> getInfoById(Long id) throws Exception{
		return roleservice.getInfoById(id);
	}
	
	public int delete(Long id) throws Exception{
		return roleservice.delete(id);
	}
}