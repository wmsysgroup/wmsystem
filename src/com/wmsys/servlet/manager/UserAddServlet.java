package com.wmsys.servlet.manager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wmsys.services.manager.UserImpl;

/**
 * Servlet implementation class UserAddServlet
 */
@WebServlet("/useradd.html")
public class UserAddServlet extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		final UserImpl services = new UserImpl();
		
		try {
			services.setMapDto(this.createDto(request));
			
			String msg =services.addUser()?"添加成功！你的账号是":"添加失败";
			String account =services.getNewAccount();
			
			request.setAttribute("msg", msg+account);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/Useradd.jsp").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doGet(request, response);
	}
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
				//
				value=entry.getValue();
				//
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
