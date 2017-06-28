package util.operateObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import util.codeGenerate.Column;

public class ObjectToSQL {
	/**
	 * 将对象转化为SQL语句
	 * */
	public static String toSqlForSave(Object object) throws Exception{
		Class clazz = object.getClass();
		Field[] fields = clazz.getDeclaredFields();
		String sql = "insert into " + clazz.getSimpleName().toLowerCase() + "(";
		String sqlArg = ") values(";
		
		for (Field field : fields) {
			Method method = hasGetMethod(field, clazz);
			
			if(method == null)
				continue;
			
			if(field.getName().equals("id")){
				sql += "id,";
				sqlArg += method.invoke(object, null) + ",";
			}else{
				Column column = field.getAnnotation(Column.class);
				sql += column.name() + ",";	
				if(column.type().equals("String"))
					sqlArg += "\"" + method.invoke(object, null) + "\",";
				else 
					sqlArg += method.invoke(object, null) + ",";
			}
			
		}
		
		return sql.substring(0, sql.length() - 1) + sqlArg.substring(0, sqlArg.length() - 1) + ")";
	}
	
	/**
	 * 将对象转化为SQL语句
	 * */
	public static String toSqlForSave2(Object object, String tableName) throws Exception{
		Class clazz = object.getClass();
		Field[] fields = clazz.getDeclaredFields();
		String sql = "insert into " + tableName + "(";
		String sqlArg = ") values(";
		
		for (Field field : fields) {
			Method method = hasGetMethod(field, clazz);
			
			if(method == null)
				continue;
			
			if(field.getName().equals("id")){
				sql += "id,";
				sqlArg += method.invoke(object, null) + ",";
			}else{
				Column column = field.getAnnotation(Column.class);
				sql += column.name() + ",";	
				if(column.type().equals("String"))
					sqlArg += "\"" + method.invoke(object, null) + "\",";
				else 
					sqlArg += method.invoke(object, null) + ",";
			}
			
		}
		
		return sql.substring(0, sql.length() - 1) + sqlArg.substring(0, sqlArg.length() - 1) + ")";
	}
	
	public static String toSqlForUpdate(Object object) throws Exception{
		Class clazz = object.getClass();
		Field[] fields = clazz.getDeclaredFields();
		String sql = "update " + clazz.getSimpleName().toLowerCase() + " set ";
		String id = "";
		
		for (Field field : fields) {
			Method method = hasGetMethod(field, clazz);
			
			if(method == null)
				continue;
			
			if(field.getName().equals("id"))
				id = method.invoke(object, null) + "";
			else{
				Column column = field.getAnnotation(Column.class);
					
				if(column.type().equals("String"))
					sql += column.name() + " = \"" + method.invoke(object, null) + "\",";
				else 
					sql += column.name() + " = " + method.invoke(object, null) + ",";
			}
		}
		
		return sql.substring(0, sql.length() - 1) + " where id = " + id;
	}
	
	public static String toSqlForUpdate2(Object object, String tableName) throws Exception{
		Class clazz = object.getClass();
		Field[] fields = clazz.getDeclaredFields();
		String sql = "update " + tableName + " set ";
		String id = "";
		
		for (Field field : fields) {
			Method method = hasGetMethod(field, clazz);
			
			if(method == null)
				continue;
			
			if(field.getName().equals("id"))
				id = method.invoke(object, null) + "";
			else{
				Column column = field.getAnnotation(Column.class);
					
				if(column.type().equals("String"))
					sql += column.name() + " = \"" + method.invoke(object, null) + "\",";
				else 
					sql += column.name() + " = " + method.invoke(object, null) + ",";
			}
		}
		
		return sql.substring(0, sql.length() - 1) + " where id = " + id;
	}
	
	public static Method hasGetMethod(Field field, Class clazz){
		Method[] methods = clazz.getMethods();
		
		for (Method method : methods) {
			if(method.getName().equals("get" + JsonToObject.firstCharToUp(field.getName())))
				return method;
		}
		
		return null;
	}
}
