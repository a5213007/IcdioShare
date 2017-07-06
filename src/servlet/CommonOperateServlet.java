package servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import service.Tel_And_Act.Tel_And_ActService;
import util.operateObject.ColumnToJson;
import util.operateObject.JsonToObject;
import util.sendToHtml.SendToHtml;

/**
 * Servlet implementation class DisplayIndexServlet
 */
@WebServlet("/CommonOperateServlet")
public class CommonOperateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommonOperateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("textml;charset=utf-8");		
		response.setContentType("text/html");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		
		if("columnInfo".equals(request.getParameter("info"))){
			String className = request.getParameter("class");
			try {
				SendToHtml.send(ColumnToJson.viewToJson(className), response);
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
				throw new ServletException();
			}
		}else if("restore".equals(request.getParameter("info"))){
			Long id = Long.parseLong(request.getParameter("id"));
			String className = request.getParameter("className");
			try {
				Class service = Class.forName("service." + className + "." + className  +"Service");
				Object object = service.newInstance();
				@SuppressWarnings("unchecked")
				Method method = service.getMethod("getInfoById", Long.class);
				List<Map<String, Object>> list = (List<Map<String, Object>>) method.invoke(object, id);
				
				if(list != null){
					JSONArray json = JSONArray.fromObject(list);
					SendToHtml.send(json, response);
				}
			
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException();
			}
		}else if("delete".equals(request.getParameter("info"))) {
			Long id = Long.parseLong(request.getParameter("id"));
			String className = request.getParameter("className");
			try {
				Class service = Class.forName("service." + className + "." + className  +"Service");
				Object object = service.newInstance();
				@SuppressWarnings("unchecked")
				Method method = service.getMethod("delete", Long.class);
				method.invoke(object, id);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException();
			}
		}else if("columnInfo".equals(request.getParameter("info"))){
			String className = request.getParameter("class");
			
			try {
				SendToHtml.send(ColumnToJson.viewToJson(className), response);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new ServletException();
			}
		}else if("edit".equals(request.getParameter("info"))){
			String className = request.getParameter("className");
			JSONArray json= JSONArray.fromObject("["+request.getParameter("object")+"]");
			try {
				Class clazz = Class.forName("service." + className + "." + className  +"Service");
				Object object = clazz.newInstance();
				Method method = clazz.getMethod("update", Object.class);
				Object obj = JsonToObject.jsonToObj(className, json);
				method.invoke(object, obj);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException();
			}	
			
		}else if("add".equals(request.getParameter("info"))){
			String className = request.getParameter("className");
			JSONArray json= JSONArray.fromObject("["+request.getParameter("object")+"]");
			try {
				Class clazz = Class.forName("service." + className + "." + className  +"Service");
				Object object = clazz.newInstance();
				Method method = clazz.getMethod("save", Object.class);
				Object obj = JsonToObject.jsonToObj(className, json);
				method.invoke(object, obj);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException();
			}
		}else if("changeState".equals(request.getParameter("info"))){
			Long id = Long.parseLong(request.getParameter("id"));
			String state = request.getParameter("state");
			
			Tel_And_ActService tel_And_ActService = new Tel_And_ActService();
			
			try {
				tel_And_ActService.changeState(state, id);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException();
			}
		}
	}

}
