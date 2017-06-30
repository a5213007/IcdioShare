package util.sendToHtml;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

public class SendToHtml {
	public static void send(JSONArray json, HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		out.write(json.toString());
		out.flush();
		out.close();
	}
}
