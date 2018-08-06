package com.wmsys.servlet.manager;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mywq.util.LabelValueBean;

import com.wmsys.services.manager.AddRiceServices;
import com.wmsys.services.manager.MenuServices;
import com.wmsys.system.tools.Tools;


@WebServlet("/AddRiceServlet")
public class AddRiceServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final MenuServices services01 = new MenuServices();
		final AddRiceServices services = new AddRiceServices();
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
			List<LabelValueBean> expMenu = allList.get("bgexp");

			

			request.setAttribute("levelMenu", levelMenu);
			request.setAttribute("specMenu", specMenu);
			request.setAttribute("typeMenu", typeMenu);
			request.setAttribute("expMenu", expMenu);
	
			
			String msg = services.addRice()?"添加成功":"网络故障";
			request.setAttribute("msg", msg);
		}
		catch(Exception e) 
		{
			String msg = "添加失败，您输入的数据有误！";
			request.setAttribute("msg", msg);
			e.printStackTrace();
		}
		request.getRequestDispatcher("/AddRice.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
