package com.wmsys.servlet.depot;

import java.io.IOException;
import java.util.HashMap;
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
 * Servlet implementation class OutListServlet
 */
@WebServlet("/OutListServlet")
public class OutListServlet extends HttpServlet 
{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Map<String,Object> dto=new HashMap<String, Object>();
		dto.put("bgnumber", request.getParameter("bgnumber"));
		
		InBoundServImpl ibs=new InBoundServImpl();
		ibs.setMapDto(dto);
		try {
			List<Map<String,Object>> rows=ibs.beforeout();
			System.out.println(rows);
			request.setAttribute("rows", rows);
			request.setAttribute("instad", request.getParameter("bgnumber"));
		} catch (Exception e) {
			request.setAttribute("msg","该品种大米库中暂无，请及时进货");
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/out.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
