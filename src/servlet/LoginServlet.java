package servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import service.User.UserService;
import util.sendToHtml.SendToHtml;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
			
		String info = request.getParameter("info");
		
		if("login".equals(info)){
			String account = request.getParameter("account");
			String password = request.getParameter("password");
			
			List<Map<String, Object>> list = new UserService().login(account, password);
			HttpSession session = request.getSession();
					
			if(list != null){
				try {
					JSONArray json = JSONArray.fromObject(list);
					SendToHtml.send(json, response);
					
					String ip = SendToHtml.getIpAddress(request);
					session.setAttribute("user_" + ip, list.get(0));
					
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException();
				}			
			}
		}else if("getLoginIngo".equals(info)){
			
		}else if("lo".equals(info)){
			OutputStream write = response.getOutputStream();
			
			write.write("true".getBytes());
			write.flush();
			write.close();
		}
		
	}

}
