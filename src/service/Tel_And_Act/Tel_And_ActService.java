package service.Tel_And_Act;

import java.util.List;
import java.util.Map;
import service.baseService.IBaseService;
import dao.Tel_And_Act.Tel_And_ActDao;

public class Tel_And_ActService implements IBaseService{
	private Tel_And_ActDao tel_and_actDao = new Tel_And_ActDao();

	public int save(Object object) throws Exception{
		try {
			return tel_and_actDao.save(object);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}				
	}
	
	public int update(Object object) throws Exception{		
		try {
			return tel_and_actDao.update(object);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	public List<Map<String, Object>> getAllInfo() throws Exception{
		try {
			return tel_and_actDao.getAllInfo();	
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	public List<Map<String, Object>> getInfoById(Long id) throws Exception{		
		try {
			return tel_and_actDao.getInfoById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	public int delete(Long id) throws Exception{
		try {
			return tel_and_actDao.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}		
	}
}