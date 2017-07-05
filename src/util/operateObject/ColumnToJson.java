package util.operateObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import util.codeGenerate.Column;

public class ColumnToJson {
	public static JSONArray viewToJson(String className) throws ClassNotFoundException{
		Class clazz = Class.forName("entity." + className + "." + className);		
		List<Map<String, Object>> info = new ArrayList<Map<String,Object>>();
		Field[] fields = clazz.getDeclaredFields();
		
		for (int i = 0; i <fields.length; i++) 			
			info.add(setInfo(fields[i]));
				
		return JSONArray.fromObject(info);
	}
	
	private static Map<String, Object> setInfo(Field field){
		Column column = field.getAnnotation(Column.class);
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("name", column.name());
		map.put("chineseName", column.chineseName());
		map.put("nullAble", column.nullAble());
		map.put("type", column.type());
		map.put("length", column.length());
		map.put("note", column.note());
		map.put("editAble", column.editAble());
		return map;
	
	}
}
