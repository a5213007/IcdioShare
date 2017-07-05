package util.operateObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Tool {
	public static String replace(String text){
		return text.replace("\"", "“");
	}
	
	public static String getID(){
		 SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		 return df.format(new Date());
	}
}
