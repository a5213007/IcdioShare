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
import service.Answer.AnswerService;
import service.Question.QuestionService;
import service.Tel_And_Act.Tel_And_ActService;
import util.sendToHtml.SendToHtml;

/**
 * Servlet implementation class QuestionAnswer
 */
@WebServlet("/QuestionAnswer")
public class QuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionServlet() {
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
			Long userId = Long.parseLong(request.getParameter("userId") + "");
			QuestionService questionService = new QuestionService();
			
			List<Map<String, Object>> list = questionService.getInfoByPage(page, userId);
			
			if(list != null){
				JSONArray json = JSONArray.fromObject(list);
				SendToHtml.send(json, response);
			}
		}else if("find".equals(request.getParameter("info"))){
			int page = Integer.parseInt(request.getParameter("page"));
			String key = request.getParameter("key");
			String value = request.getParameter("value");
			String type = request.getParameter("type");
			Long userId = Long.parseLong(request.getParameter("id") + "");
			
			QuestionService questionService = new QuestionService();
			List<Map<String, Object>> list = questionService.findByKeyAndValue(key, value,type, page,  userId);
			
			
			if(list != null){
				JSONArray json = JSONArray.fromObject(list);
				SendToHtml.send(json, response);
			}
		}
	}

}
