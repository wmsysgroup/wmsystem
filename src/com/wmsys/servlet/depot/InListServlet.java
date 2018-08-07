package com.wmsys.servlet.depot;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wmsys.services.depot.impl.InBoundServImpl;
import com.wmsys.system.db.DBUtils;

/**
 * Servlet implementation class InListServlet
 */
@WebServlet("/InListServlet")
public class InListServlet extends HttpServlet 
{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		InBoundServImpl ibs=new InBoundServImpl();
		try {
			List<Map<String,Object>> rows=ibs.findAll();
			request.setAttribute("rows", rows);
		} catch (Exception e) {
			request.setAttribute("msg", "≤È—Ø ß∞‹");
			e.printStackTrace();
		}
		finally
		{
			DBUtils.close();
		}
		request.getRequestDispatcher("/ini.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		doGet(request, response);
	}

}
