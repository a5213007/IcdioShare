package util.codeGenerate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Properties;

public class CodeGenerate {
	
	public static String getPath(String type) throws Exception{
		//文件真实路径
        String fileName="D:/angular/workspace/IcdioShare/config.properties";
        Properties p = new Properties();
        
        InputStream is = new FileInputStream(new File(fileName));
		p.load(is);
		
		return p.get(type).toString();

    }
	
	public void save(String className, String text, String type){
		String path = "";
		String fileName = className + (type.equals("Entity") ? "" : type) + ".java";
		try {
			path = getPath(type + "Path");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		File dir = new File(path + className);
		
		if(dir.mkdirs()){
			File file = new File(path + "/" + className + "/" + fileName);
			try {
				PrintWriter write = new PrintWriter(file);
				write.write(text);
				write.flush();
				write.close();
				
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
		}
	}
}
