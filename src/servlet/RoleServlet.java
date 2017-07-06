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
import service.Permissions.PermissionsService;
import service.Role.RoleService;
import util.sendToHtml.SendToHtml;

/**
 * Servlet implementation class RoleServlet
 */
@WebServlet("/RoleServlet")
public class RoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoleServlet() {
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
			int page = Integer.parseInt(request.getParameter("page") + "");
			RoleService roleService = new RoleService();
			List<Map<String, Object>> list = roleService.getInfoByPage(page);
			
			if(list != null){
				JSONArray json = JSONArray.fromObject(list);
				SendToHtml.send(json, response);
			}
		}else if("find".equals(request.getParameter("info"))){
			int page = Integer.parseInt(request.getParameter("page"));
			String key = request.getParameter("key");
			String value = request.getParameter("value");
			
			RoleService roleService = new RoleService();
			List<Map<String, Object>> list = roleService.findByKeyAndValue(key, value, page);
			
			if(list != null){
				JSONArray json = JSONArray.fromObject(list);
				SendToHtml.send(json, response);
			}
			
		}else if("getAllRole".equals(request.getParameter("info"))){
			RoleService roleService = new RoleService();
			List<Map<String, Object>> list = roleService.getAllInfo();
			
			if(list != null){
				JSONArray json = JSONArray.fromObject(list);
				SendToHtml.send(json, response);
			}
			
		}else if("getRoleById".equals(request.getParameter("info"))){
			Long id = Long.parseLong(request.getParameter("id"));
			RoleService roleService = new RoleService();
			List<Map<String, Object>> list = roleService.getRoleByUserId(id);
			
			if(list != null){
				JSONArray json = JSONArray.fromObject(list);
				SendToHtml.send(json, response);
			}
			
		}else if("addRoleTo".equals(request.getParameter("info"))){
			Long roleId = Long.parseLong(request.getParameter("id"));
			JSONArray json = JSONArray.fromObject("["+request.getParameter("object")+"]");
			
			RoleService roleService = new RoleService();
			try {
				roleService.addRoleToUser(roleId, json);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException();
			}
			
		}else if("removeAll".equals(request.getParameter("info"))){
			Long roleId = Long.parseLong(request.getParameter("id"));
			RoleService roleService = new RoleService();
			
			try {
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException();
			}
		}
	}

}
