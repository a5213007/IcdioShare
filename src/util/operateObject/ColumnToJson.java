package util.operateObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.codeGenerate.Column;

public class ColumnToJson {
	public static List<Map<String, Object>> viewToJson(List<Map<String, Object>> list) throws ClassNotFoundException{
		Class clazz = Class.forName("Entity." + list.get(0).get("EntityName"));
		
		List<Map<String, Object>> info = new ArrayList<Map<String,Object>>();
		Field[] fields = clazz.getDeclaredFields();

		Map<String, Object> title = new HashMap<String, Object>();
		title.put("ChineseName", list.get(0).get("ChineseName"));
		title.put("EntityName", list.get(0).get("EntityName"));
		title.put("TID", list.get(0).get("TID"));
		info.add(title);
		
		for (int i = 1; i <fields.length; i++) 			
			info.add(setInfo(fields[i], list));
				
		return info;
	}
	
	private static Map<String, Object> setInfo(Field field, List<Map<String, Object>> list){
		Column column = field.getAnnotation(Column.class);
		Map<String, Object> map = new HashMap<String, Object>();
		
		
		
		return map;
	
	}
}
