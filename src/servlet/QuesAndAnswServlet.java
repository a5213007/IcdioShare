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
import service.Question.QuestionService;
import service.Tel_And_Act.Tel_And_ActService;
import util.sendToHtml.SendToHtml;

/**
 * Servlet implementation class QuesAndAnswServlet
 */
@WebServlet("/QuesAndAnswServlet")
public class QuesAndAnswServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuesAndAnswServlet() {
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
		
		Long technologyId = Long.parseLong(request.getParameter("id")+"");
		Tel_And_ActService tel_And_ActService = new Tel_And_ActService();
		
		List<Map<String, Object>> list = tel_And_ActService.getTechnologyWithQuestionAndAnswer(technologyId);
		JSONArray json = JSONArray.fromObject(list);
		SendToHtml.send(json, response);
	}

}
