package service.CommonOperate;

import java.util.List;
import java.util.Map;

import dao.CommonOperate.CommonOperateDao;

public class CommonOperateService {
	CommonOperateDao commonOperateDao = new CommonOperateDao();
	
	public List<Map<String, Object>> getInfoById(Long id, String tableName){
		try {
			return commonOperateDao.getInfoById(id, tableName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
