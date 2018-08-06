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

import org.mywq.util.LabelValueBean;

import com.wmsys.services.manager.DepotInquireServices;
import com.wmsys.services.manager.MenuServices;
import com.wmsys.system.tools.Tools;

@WebServlet("/DepotInquireServlet")
public class DepotInquireServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final MenuServices services01 = new MenuServices();
		final DepotInquireServices services = new DepotInquireServices();
		Map<String, String[]> tem = request.getParameterMap();
		Set<Entry<String,String[]>> entrySet = tem.entrySet();
		String val[] = null;
		Map<String, Object> dto = new HashMap<>();
		
		for(Entry<String,String[]> entry:entrySet) 
		{
			val = entry.getValue();
			dto.put(entry.getKey(),val[0]);
		}
		services.setDto(dto);
		
		try 
		{
			Map<String,List<LabelValueBean>> allList = services01.getMenuList();
			List<LabelValueBean> levelMenu = allList.get("bglevel");
			List<LabelValueBean> specMenu = allList.get("bgspec");
			List<LabelValueBean> typeMenu = allList.get("bgtype");
			List<LabelValueBean> wMenu = allList.get("wnumber");
			
			request.setAttribute("levelMenu", levelMenu);
			request.setAttribute("specMenu", specMenu);
			request.setAttribute("typeMenu", typeMenu);
			request.setAttribute("wMenu", wMenu);
			
			List<Map<String,String>> rows = services.salesInquire();
			System.out.println(rows);
			if(rows.size()==0) 
			{
				String msg = "没有符合条件的数据";
				request.setAttribute("msg", msg);
			}
			request.setAttribute("rows", rows);
		}
		catch (Exception e) 
		{
			String msg = "网络故障";
			request.setAttribute("msg", msg);
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/DepotInquire.jsp").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
