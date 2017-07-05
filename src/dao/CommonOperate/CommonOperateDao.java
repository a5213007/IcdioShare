package dao.CommonOperate;

import java.util.List;
import java.util.Map;

import util.connectUtil.CommonDAO;

public class CommonOperateDao extends CommonDAO {
	public List<Map<String, Object>> getInfoById(Long id, String tableName) throws Exception{
		String sql = "select * from " + tableName.toLowerCase() + " where id = " + id;
		System.out.println("----------------------------------------");
		System.out.println("SQL:" + sql);
		return excuteQuery(sql, null);
	}
}
