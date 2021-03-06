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
import service.Tel_And_Act.Tel_And_ActService;
import util.sendToHtml.SendToHtml;


/**
 * Servlet implementation class ManageIndexServlet
 */
@WebServlet("/ManageIndexServlet")
public class ManagerINdexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerINdexServlet() {
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
		
		int activeIndex = Integer.parseInt(request.getParameter("activeIndex"));
		int technologyIndex = Integer.parseInt(request.getParameter("technologyIndex"));
		Tel_And_ActService tel_And_ActService = new Tel_And_ActService();
		List<Map<String, Object>> activeList = tel_And_ActService.getAllActiveInfoByIndex(activeIndex);
		List<Map<String, Object>> technologyList = tel_And_ActService.getAllTechnologyInfoByIndex(technologyIndex);
		activeList.addAll(technologyList);
		JSONArray json = JSONArray.fromObject(activeList);
		SendToHtml.send(json, response);
	}

}
