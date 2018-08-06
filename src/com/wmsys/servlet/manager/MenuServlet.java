package com.wmsys.servlet.manager;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mywq.util.LabelValueBean;

import com.wmsys.services.manager.MenuServices;
import com.wmsys.system.tools.Tools;


@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		MenuServices services = new MenuServices();
		
		Object path = request.getAttribute("path");
		String toPath = "/DepotInquire.jsp";
		if(path!=null && !path.equals(""))
		{
			toPath=path.toString();
		}
		
		try 
		{
			Map<String,List<LabelValueBean>> allList = services.getMenuList();
			
			List<LabelValueBean> levelMenu = allList.get("bglevel");
			List<LabelValueBean> specMenu = allList.get("bgspec");
			List<LabelValueBean> typeMenu = allList.get("bgtype");
			List<LabelValueBean> expMenu = allList.get("bgexp");
			List<LabelValueBean> wMenu = allList.get("wnumber");
			
			//this.getServletContext().setAttribute("levelMenu", levelMenu);
			//request.getSession().setAttribute("levelMenu", levelMenu);
			request.setAttribute("levelMenu", levelMenu);
			request.setAttribute("specMenu", specMenu);
			request.setAttribute("typeMenu", typeMenu);
			request.setAttribute("expMenu", expMenu);
			request.setAttribute("wMenu", wMenu);
			
		} 
		catch (Exception e) {
			
			e.printStackTrace();
		}
		request.getRequestDispatcher(toPath).forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
