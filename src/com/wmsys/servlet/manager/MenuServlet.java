package com.wmsys.servlet.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mywq.util.LabelValueBean;

import com.wmsys.system.tools.Tools;


@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Object path = request.getAttribute("path");
		String toPath = "/DepotInquire.jsp";
		if(path!=null && !path.equals(""))
		{
			toPath=path.toString();
		}
		try 
		{
			List<LabelValueBean> levelMenu = Tools.getOptions("bglevel");
			request.setAttribute("levelMenu", levelMenu);
			
			List<LabelValueBean> specMenu = Tools.getOptions("bgspec");
			request.setAttribute("specMenu", specMenu);
			
			List<LabelValueBean> typeMenu = Tools.getOptions("bgtype");
			request.setAttribute("typeMenu", typeMenu);
			
			List<LabelValueBean> expMenu = Tools.getOptions("bgexp");
			request.setAttribute("expMenu", expMenu);
			
			List<LabelValueBean> wMenu = Tools.getOptions("wnumber");
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
