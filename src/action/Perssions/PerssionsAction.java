package action.Perssions;

import java.util.List;
import java.util.Map;
import action.baseAction.IBaseAction;
import service.Perssions.PerssionsService;

public class PerssionsAction implements IBaseAction{
	private PerssionsService perssionsservice = new PerssionsService();

	public int save(Object object) throws Exception{
		return perssionsservice.save(object);		
	}
	
	public int update(Object object) throws Exception{
		return perssionsservice.update(object);
	}
	
	public List<Map<String, Object>> getAllInfo() throws Exception{
		return perssionsservice.getAllInfo();
	}
	
	public List<Map<String, Object>> getInfoById(Long id) throws Exception{
		return perssionsservice.getInfoById(id);
	}
	
	public int delete(Long id) throws Exception{
		return perssionsservice.delete(id);
	}
}