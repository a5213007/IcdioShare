package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.User.UserService;
import util.sendToHtml.SendToHtml;
import net.sf.json.JSONArray;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("textml;charset=utf-8");		
		response.setContentType("text/html");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		UserService userService = new UserService();

		if ("changeInfo".equals(request.getParameter("type"))) {
			JSONArray json = JSONArray.fromObject("[" + request.getParameter("data") + "]");
			@SuppressWarnings("unchecked")
			Map<String, Object> data = (Map<String, Object>)json.get(0);
			try {
				userService.updateTo(data);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if ("changePd".equals(request.getParameter("type"))) {
			String account = request.getParameter("account");
			String password = request.getParameter("password");
			try {
				userService.updatePd(account, password);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("display".equals(request.getParameter("info"))){
			int page = Integer.parseInt(request.getParameter("page")+"");
			List<Map<String, Object>> list = userService.getInfoByPage(page);
			
			if(list != null){
				JSONArray json = JSONArray.fromObject(list);
				SendToHtml.send(json, response);
			}
		}
		
	}

}
