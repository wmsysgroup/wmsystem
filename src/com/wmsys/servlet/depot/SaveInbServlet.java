package com.wmsys.servlet.depot;

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

import com.wmsys.services.depot.impl.InBoundServImpl;
import com.wmsys.system.db.DBUtils;



/**
 * Servlet implementation class SaveInbServlet
 */
@WebServlet("/SaveInbServlet")
public class SaveInbServlet extends HttpServlet 
{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//获取表单中的所有值并放入数据存储对象dto中
		Map<String, String[]> tem = request.getParameterMap();
		Set<Entry<String,String[]>> entrySet = tem.entrySet();
		String val[] = null;
		Map<String, Object> dto = new HashMap<>();
		
		for(Entry<String,String[]> entry:entrySet) 
		{
			val = entry.getValue();
			dto.put(entry.getKey(),val[0]);
		}
		
		//填充dto
		dto.put("indate", "2015-06-06");
		dto.put("inid", "B00000000");
		dto.put("inprincipal", "12345");
		dto.put("wnumber", "2");
		InBoundServImpl ibs=new InBoundServImpl();
		ibs.setMapDto(dto);
		
		try {
			ibs.insertin();
			ibs.updateSt();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			DBUtils.close();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
