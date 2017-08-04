package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.sendToHtml.SendToHtml;

/**
 * Servlet Filter implementation class IsLogin
 */
@WebFilter("/IsLogin")
public class IsLoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public IsLoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		// 设置编码
		httpServletRequest.setCharacterEncoding("utf-8");
		// 获取请求方法
		String url = httpServletRequest.getServletPath();
		// 静态资源直接放行
		
		HttpSession session = httpServletRequest.getSession();
		String ip = SendToHtml.getIpAddress(httpServletRequest);
		
		
		if(url.indexOf("/servlet/") == 0){
			if(url.equals("/servlet/LoginServlet") && !"lo".equals(request.getParameter("info"))){
				System.out.println("IP地址：" + ip + ",请求路径：" + url);
				chain.doFilter(request, response);
			}
			
			System.out.println("IP地址：" + ip + ",请求路径：" + url);
			if(session == null || session.getAttribute("user_" + ip) == null)
				return ;
			else
				chain.doFilter(request, response);
		}else {
			System.out.println("IP地址：" + ip + ",请求路径：" + url);
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
