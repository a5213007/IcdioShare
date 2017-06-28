package action.Tel_And_Act;

import java.util.List;
import java.util.Map;
import action.baseAction.IBaseAction;
import service.Tel_And_Act.Tel_And_ActService;

public class Tel_And_ActAction implements IBaseAction{
	private Tel_And_ActService tel_and_actservice = new Tel_And_ActService();

	public int save(Object object) throws Exception{
		return tel_and_actservice.save(object);		
	}
	
	public int update(Object object) throws Exception{
		return tel_and_actservice.update(object);
	}
	
	public List<Map<String, Object>> getAllInfo() throws Exception{
		return tel_and_actservice.getAllInfo();
	}
	
	public List<Map<String, Object>> getInfoById(Long id) throws Exception{
		return tel_and_actservice.getInfoById(id);
	}
	
	public int delete(Long id) throws Exception{
		return tel_and_actservice.delete(id);
	}
}