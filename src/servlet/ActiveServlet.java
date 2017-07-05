package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Process.Process;
import net.sf.json.JSONArray;
import service.Process.ProcessService;
import service.Tel_And_Act.Tel_And_ActService;
import util.operateObject.JsonToObject;
import util.sendToHtml.SendToHtml;

/**
 * Servlet implementation class ActiveServlet
 */
@WebServlet("/ActiveServlet")
public class ActiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActiveServlet() {
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
		
		if("withEvaluation".equals(request.getParameter("info"))){
			Long activeId = Long.parseLong(request.getParameter("activeId") + "");
			Tel_And_ActService tel_And_ActService = new Tel_And_ActService();
			List<Map<String, Object>> list = tel_And_ActService.getActiveWithEvaluation(activeId);
			
			if(list != null){
				JSONArray json = JSONArray.fromObject(list);
				SendToHtml.send(json, response);
			}
		}else if("display".equals(request.getParameter("info"))){
			int page = Integer.parseInt(request.getParameter("page")+"");
			Long userId = Long.parseLong(request.getParameter("userId") + "");
			Tel_And_ActService tel_And_ActService = new Tel_And_ActService();
			
			List<Map<String, Object>> list = tel_And_ActService.getAllActiveByPage(page, userId);
			
			if(list != null){
				JSONArray json = JSONArray.fromObject(list);
				SendToHtml.send(json, response);
			}

		}else  if("addActive".equals(request.getParameter("info"))){
			JSONArray json = JSONArray.fromObject("[" + request.getParameter("object") + "]");
			try {
				Object object = JsonToObject.jsonToObj("Tel_And_Act", json);
				Tel_And_ActService tel_And_ActService = new Tel_And_ActService();
				ProcessService processService = new ProcessService();
				Map<String, Object> map = (Map<String, Object>) json.get(0);
				tel_And_ActService.save(object);
				processService.add(map);
			
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException();
			}						

		}else if("find".equals(request.getParameter("info"))){
			int page = Integer.parseInt(request.getParameter("page"));
			String key = request.getParameter("key");
			String value = request.getParameter("value");
			Tel_And_ActService tel_And_ActService = new Tel_And_ActService();
			List<Map<String, Object>> list = tel_And_ActService.findByKeyAndValue(key, value, page);
			if(list != null){
				JSONArray json = JSONArray.fromObject(list);
				SendToHtml.send(json, response);
			}
		}
	}

}
