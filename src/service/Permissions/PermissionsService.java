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
	
	public List<Map<String, Object>> getInfoByPage(int page){
		try {
			List<Map<String, Object>> perssionsList = perssionsDao.getPageInfo(page);		
			List<Map<String, Object>> pageList = perssionsDao.getAllInfoPage();
			
			perssionsList.add(pageList.get(0));
			return perssionsList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public int delete(Long id) throws Exception{
		try {
			return perssionsDao.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}		
	}
	
	public List<Map<String, Object>> hasPerssions(Long userId){
		try {
			List<Map<String, Object>> list = perssionsDao.hasPerssions(userId);
			if(list.size() != 0){
				String[] permissions = new String[list.size()];
				
				
			}
			
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return null;
	}
}