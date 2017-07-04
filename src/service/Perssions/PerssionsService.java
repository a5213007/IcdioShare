package service.Perssions;

import java.util.List;
import java.util.Map;
import service.baseService.IBaseService;
import dao.Perssions.PerssionsDao;

public class PerssionsService implements IBaseService{
	private PerssionsDao perssionsDao = new PerssionsDao();

	public int save(Object object) throws Exception{
		try {
			return perssionsDao.save(object);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}				
	}
	
	public int update(Object object) throws Exception{		
		try {
			return perssionsDao.update(object);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	public List<Map<String, Object>> getAllInfo() throws Exception{
		try {
			return perssionsDao.getAllInfo();	
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	public List<Map<String, Object>> getInfoById(Long id) throws Exception{		
		try {
			return perssionsDao.getInfoById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	public int delete(Long id) throws Exception{
		try {
			return perssionsDao.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}		
	}
	
	public boolean hasPerssions(String signName, Long userId){
		try {
			if(perssionsDao.hasPerssions(signName, userId).size() != 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return false;
	}
}