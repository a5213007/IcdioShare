package service.baseService;

import java.util.List;
import java.util.Map;

public interface IBaseService {
	public int save(Object object) throws Exception;
	
	public int update(Object object) throws Exception;
	
	public List<Map<String, Object>> getAllInfo() throws Exception;
	
	public List<Map<String, Object>> getInfoById(Long id) throws Exception;
	
	public int delete(Long id) throws Exception;
}
