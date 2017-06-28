package action.Peocess;

import java.util.List;
import java.util.Map;
import action.baseAction.IBaseAction;
import service.Peocess.PeocessService;

public class PeocessAction implements IBaseAction{
	private PeocessService peocessservice = new PeocessService();

	public int save(Object object) throws Exception{
		return peocessservice.save(object);		
	}
	
	public int update(Object object) throws Exception{
		return peocessservice.update(object);
	}
	
	public List<Map<String, Object>> getAllInfo() throws Exception{
		return peocessservice.getAllInfo();
	}
	
	public List<Map<String, Object>> getInfoById(Long id) throws Exception{
		return peocessservice.getInfoById(id);
	}
	
	public int delete(Long id) throws Exception{
		return peocessservice.delete(id);
	}
}