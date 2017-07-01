package util.operateObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import util.codeGenerate.Column;
import net.sf.json.JSONArray;

public class JsonToObject {
	/**
	 * 将传来的josn字符串还原成对象
	 * */
	public static Object jsonToObj(String entityName, JSONArray json) throws Exception{
		Class clazz = Class.forName("entity." + entityName + "." + entityName);
		Object object = clazz.newInstance();
		Map<String, Object> map = (Map<String, Object>) json.get(0);
		Field[] fields = clazz.getDeclaredFields();
		
		for (Field field : fields) {
			if(field.getName().equals("id")){
				Method method = hasSetMethod(field, clazz);
				method.invoke(object, map.get("id") + "");
				
			}else {			
				Column column = field.getAnnotation(Column.class);
				if(column.nullAble() && (map.get(column.name()) == null || 
						map.get(column.name()).toString().length() == 0))
					continue;
				
				Method method = hasSetMethod(field, clazz);			
				
				if(method != null){
					if(column.type().equals("String"))
						method.invoke(object, map.get(column.name()) + "");
					else if(column.type().equals("Double"))
						method.invoke(object, Double.parseDouble(map.get(column.name()) + ""));
					else if(column.type().equals("Integer"))
						method.invoke(object, Integer.parseInt(map.get(column.name()) + ""));
					
				}
			}			
		}
		
		return object;
	}
	
	public static Method hasSetMethod(Field field, Class clazz){
		Method[] methods = clazz.getMethods();
		
		for (Method method : methods) {
			if(method.getName().equals("set" + firstCharToUp(field.getName())))
				return method;
		}
		
		return null;
	}
	
	public static String firstCharToUp(String name){
		String change = "";
			
		change += name.toUpperCase().charAt(0);
		change += name.substring(1);
		
		return change;
	}
	

}
