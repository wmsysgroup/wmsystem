package com.neusoft.web;

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
			//判断
			
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
	//获取dto
		private final Map<String,Object> createDto(HttpServletRequest request)
	  {
			Map<String,String[]> tem=request.getParameterMap();
			//1.获取所有的键值对集合(EntrySet)
			Set<Entry<String,String[]>> entrySet=tem.entrySet();
			//定义字符串数值表示Entry的value
			String value[]=null;
			//实例化DTO
			Map<String,Object> dto=new HashMap<>();
			//循环获取每个键值对
			for(Entry<String,String[]>  entry:entrySet)
			{
				//获取键值对的value部分
				value=entry.getValue();
				//判断value数组的长度,如果是1,表示该控件是非checkbox,如果长度大于1,表示checkbox
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

