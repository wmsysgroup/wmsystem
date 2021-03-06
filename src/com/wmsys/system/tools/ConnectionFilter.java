package com.wmsys.system.tools;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;

import com.wmsys.system.db.DBUtils;



/**
 * Servlet Filter implementation class ConnectionFilter
 */
@WebFilter("/*")
public class ConnectionFilter extends HttpServlet implements Filter 
{
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{
			try
			{
				/**
				 * A1010.jsp---查询---a1011.html-------A1010ServicesImpl.query()
				 *                      |
				 *                      |
				 *                    A1010.jsp  
				 */
				//过滤链---将请求向目标地址进行传递
				chain.doFilter(request, response);
			}
			finally
			{
				DBUtils.close();
			}
	}

	public void init(FilterConfig fConfig) throws ServletException 
	{
	}
}
