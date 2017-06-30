package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.bcel.internal.generic.Type;

import net.sf.json.JSONArray;
import service.Tel_And_Act.Tel_And_ActService;
import util.sendToHtml.SendToHtml;

/**
 * Servlet implementation class DividePageServlet
 */
@WebServlet("/DividePageServlet")
public class DividePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DividePageServlet() {
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
		String type = request.getParameter("type");
		if(type.equals("AllPage")) {
			Tel_And_ActService tel_And_ActService = new Tel_And_ActService();
			List<Map<String, Object>> activeList = tel_And_ActService.getAllActiveInfo();
			List<Map<String, Object>> technologyList = tel_And_ActService.getAllTechnologyInfo();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("活动通知", (activeList.size() / 10) + 1);
			map.put("技术分享", (technologyList.size() / 10) + 1);
			JSONArray json = JSONArray.fromObject(map);
			SendToHtml.send(json, response);
		}
	}

}
