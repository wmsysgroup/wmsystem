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
import javax.servlet.http.HttpSession;

import com.wmsys.services.depot.impl.InBoundServImpl;
import com.wmsys.system.db.DBUtils;
import com.wmsys.system.tools.Tools;



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
		HttpSession session = request.getSession(); 
		Map<String,String> user=(Map<String, String>) session.getAttribute("USERINFO");
		
		
		//填充dto
		dto.put("indate", Tools.getDate());
		dto.put("inid", Tools.listno("B"));
		dto.put("indid", dto.get("inid"));
		dto.put("inprincipal", user.get("uaccount"));
		dto.put("number", dto.get("innumber"));
		InBoundServImpl ibs=new InBoundServImpl();
	
		ibs.setMapDto(dto);
		
		try {
			//添加入库清单
			ibs.insertin();
			//修改已入库清单状态
			ibs.updateSt();
			//添加货物存量
			ibs.indetp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/InListServlet").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
