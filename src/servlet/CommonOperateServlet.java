package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.operateObject.ColumnToJson;
import util.sendToHtml.SendToHtml;

/**
 * Servlet implementation class DisplayIndexServlet
 */
@WebServlet("/DisplayIndexServlet")
public class CommonOperateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommonOperateServlet() {
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
		
		if("columnInfo".equals(request.getParameter("info"))){
			String className = request.getParameter("class");
			try {
				SendToHtml.send(ColumnToJson.viewToJson(className), response);
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
				throw new ServletException();
			}
		}
	}

}
