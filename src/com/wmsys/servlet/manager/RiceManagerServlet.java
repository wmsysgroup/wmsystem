package com.wmsys.servlet.manager;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wmsys.services.manager.RiceImpl;

/**
 * Servlet implementation class RiceManagerServlet
 */
@WebServlet("/RiceManagerServlet")
public class RiceManagerServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		
		final RiceImpl services =new RiceImpl();
		try 
		{
			
			services.setMapDto(this.createDto(request));
			//
			this.excute(services, request);
	
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		request.getRequestDispatcher("/Ricemanager.jsp").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doGet(request, response);
	}
	
	//鏌ヨ
	private final void excute(RiceImpl services,HttpServletRequest request)throws Exception
	{
		//
		List<Map<String,String>> rows=services.query();
		if(rows.size()>0)
		{
			request.setAttribute("rows", rows);
		}
		else
		{
			request.setAttribute("msg", "不存在满足条件的数据!");
		}
	}
	
	
	
	
	
	//
	private final Map<String,Object> createDto(HttpServletRequest request)
    {
		Map<String,String[]> tem=request.getParameterMap();
		//
		Set<Entry<String,String[]>> entrySet=tem.entrySet();
		//
		String value[]=null;
		//
		Map<String,Object> dto=new HashMap<>();
		//
		for(Entry<String,String[]>  entry:entrySet)
		{
			
			value=entry.getValue();
			//checkbox
			if(value.length==1)
			{
				dto.put(entry.getKey(), value[0]);
			}
			else
			{
				dto.put(entry.getKey(), value);
			}	
		 }
		 return dto;
    }

}

