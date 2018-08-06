package com.wmsys.servlet.sell;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wmsys.web.support.BaseServlet;
import com.wmsys.services.sell.SellServicesImpl;

/**
 * Servlet implementation class SellListServlet
 */
@WebServlet("/SellList.html")
public class SellListServlet extends BaseServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try
		{
			//1.获取页面数据
			Map<String,Object> dto=this.createDto(request);
			SellServicesImpl services=new SellServicesImpl(dto);
			//调用查询方法
			List<Map<String,String>> rows=services.queryForOutList();
			//判断查询结果
			if(rows.size()>0)
			{
				//将查询结果反馈给JSP
				request.setAttribute("rows", rows);
			}
			else
			{
				request.setAttribute("msg", "没有符合条件的数据!");
			}	
			
		}
		catch(Exception ex)
		{
			request.setAttribute("msg", "网络故障!");
			ex.printStackTrace();
		}
		request.getRequestDispatcher("/saleJSP/SellList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		this.doGet(request, response);
	}


}
