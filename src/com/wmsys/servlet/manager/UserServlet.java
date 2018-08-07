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
 * Servlet implementation class UserServlet
 */
@WebServlet("/userc.html")
public class UserServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		try 
		{
			final UserImpl services=new UserImpl();
			//dto
			services.setMapDto(this.createDto(request));
			//
			String msg = services.modify()?"修改成功":"修改失败";
			
				request.setAttribute("msg", msg);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/User.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doGet(request, response);
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
	private final boolean isNotNull(final Object args)
    {
    	return args!=null && !args.equals("");
    }

}

