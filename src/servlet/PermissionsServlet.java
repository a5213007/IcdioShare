package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import service.Evaluation.EvaluationService;
import service.Permissions.PermissionsService;
import util.sendToHtml.SendToHtml;

/**
 * Servlet implementation class PermissionsServlet
 */
@WebServlet("/PermissionsServlet")
public class PermissionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PermissionsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("textml;charset=utf-8");		
		response.setContentType("text/html");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		
		if("display".equals(request.getParameter("info"))){
			int page = Integer.parseInt(request.getParameter("page")+"");
			PermissionsService permissionsService = new PermissionsService();
			List<Map<String, Object>> list = permissionsService.getInfoByPage(page);
			
			if(list != null){
				JSONArray json = JSONArray.fromObject(list);
				SendToHtml.send(json, response);
			}
		}else if("getPermissions".equals(request.getParameter("info"))){
			Long id = Long.parseLong(request.getParameter("id") + "");
			PermissionsService permissionsService = new PermissionsService();
			List<Map<String, Object>> list = permissionsService.hasPerssions(id);
			
			if(list != null){
				JSONArray json = JSONArray.fromObject(list);
				SendToHtml.send(json, response);
			}
		}else if("find".equals(request.getParameter("info"))){
			int page = Integer.parseInt(request.getParameter("page"));
			String key = request.getParameter("key");
			String value = request.getParameter("value");
			PermissionsService permissionsService = new PermissionsService();
			List<Map<String, Object>> list = permissionsService.findByKeyAndValue(key, value, page);
			if(list != null){
				JSONArray json = JSONArray.fromObject(list);
				SendToHtml.send(json, response);
			}
		}else if("getAllPermissions".equals(request.getParameter("info"))){
			PermissionsService permissionsService = new PermissionsService();
			List<Map<String, Object>> list = permissionsService.getAllInfo();
			if(list != null){
				JSONArray json = JSONArray.fromObject(list);
				SendToHtml.send(json, response);
			}
		}else if("getPermissionsById".equals(request.getParameter("info"))){
			Long id = Long.parseLong(request.getParameter("id"));
			PermissionsService permissionsService = new PermissionsService();
			List<Map<String, Object>> list = permissionsService.getPerssionsByRoleId(id);
			
			if(list != null){
				JSONArray json = JSONArray.fromObject(list);
				SendToHtml.send(json, response);
			}
		}else if("addPermissionsTo".equals(request.getParameter("info"))){
			Long roleId = Long.parseLong(request.getParameter("id"));
			JSONArray json = JSONArray.fromObject("["+request.getParameter("object")+"]");
			
			PermissionsService permissionsService = new PermissionsService();
			try {
				permissionsService.addPermissionsToRole(roleId, json);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException();
			}
		}else if("removeAll".equals(request.getParameter("info"))){
			Long roleId = Long.parseLong(request.getParameter("id"));
			PermissionsService permissionsService = new PermissionsService();
			
			try {
				permissionsService.removeAllPermissions(roleId);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException();
			}
		}
	}

}
