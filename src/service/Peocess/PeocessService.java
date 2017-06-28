package service.Peocess;

import java.util.List;
import java.util.Map;
import service.baseService.IBaseService;
import dao.Peocess.PeocessDao;

public class PeocessService implements IBaseService{
	private PeocessDao peocessDao = new PeocessDao();

	public int save(Object object) throws Exception{
		try {
			return peocessDao.save(object);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}				
	}
	
	public int update(Object object) throws Exception{		
		try {
			return peocessDao.update(object);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	public List<Map<String, Object>> getAllInfo() throws Exception{
		try {
			return peocessDao.getAllInfo();	
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	public List<Map<String, Object>> getInfoById(Long id) throws Exception{		
		try {
			return peocessDao.getInfoById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	public int delete(Long id) throws Exception{
		try {
			return peocessDao.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}		
	}
}