package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Evaluation.EvaluationService;
import util.operateObject.JsonToObject;
import util.sendToHtml.SendToHtml;
import net.sf.json.JSONArray;

/**
 * Servlet implementation class EvaluationServlet
 */
@WebServlet("/EvaluationServlet")
public class EvaluationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EvaluationServlet() {
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
		
		if("addEvaluation".equals(request.getParameter("info"))){
			JSONArray json = JSONArray.fromObject("[" + request.getParameter("object") + "]");
			try {
				Object object = JsonToObject.jsonToObj("Evaluation", json);
				EvaluationService evaluationService = new EvaluationService();
				evaluationService.save(object);
			} catch (Exception e) {
				
				e.printStackTrace();
				throw new ServletException();
			}
		}else if("display".equals(request.getParameter("info"))){
			int page = Integer.parseInt(request.getParameter("page")+"");
			EvaluationService evaluationService = new EvaluationService();
			List<Map<String, Object>> list = evaluationService.getInfoByPage(page);
			
			if(list != null){
				JSONArray json = JSONArray.fromObject(list);
				SendToHtml.send(json, response);
			}
		}
	}

}
