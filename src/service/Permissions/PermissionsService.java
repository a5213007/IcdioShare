package service.Permissions;

import java.util.List;
import java.util.Map;

import service.baseService.IBaseService;
import dao.Permissions.PermissionsDao;

public class PermissionsService implements IBaseService{
	private PermissionsDao perssionsDao = new PermissionsDao();

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