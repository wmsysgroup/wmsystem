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

import com.neusoft.services.RiceImpl;


@WebServlet("/RiceEditServlet")
public class RiceEditServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		final RiceImpl services =new RiceImpl();
		try 
		{
			//植入dto
			services.setMapDto(this.createDto(request));
			//
			
				this.execute(services, request);
				
			
		
				
						
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/Riceedit.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doGet(request, response);
	}
	//单一实力查询
	private final void execute(RiceImpl services,HttpServletRequest request)throws Exception
	{
		Map<String,String> ins=services.findById();
		if(ins!=null)
		{
			System.out.println(ins);
			request.setAttribute("ins", ins);
		}
		else
		{
			request.setAttribute("msg", "该数据已删除或禁止访问!");
		}	
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