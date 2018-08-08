package com.wmsys.servlet.depot;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wmsys.services.depot.impl.InBoundServImpl;
import com.wmsys.system.db.DBUtils;


@WebServlet("/InbAddServlet")
public class InbAddServlet extends HttpServlet 
{
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Map<String,Object> dto=new HashMap<String, Object>();
		dto.put("plid", request.getParameter("plid"));
		
		InBoundServImpl ibs=new InBoundServImpl();
		ibs.setMapDto(dto);
		try {
			Map<String,Object> ins=ibs.findByno();
			request.setAttribute("ins", ins);
			System.out.println(ins);
		} catch (Exception e) {
			request.setAttribute("msg","该数据已被删除或禁止访问");
			e.printStackTrace();
		}
	
		request.getRequestDispatcher("/iniall.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		doGet(request, response);
	}

}
