package com.wmsys.servlet.sell;

import com.wmsys.services.sell.SellServicesImpl;
import com.wmsys.web.support.BaseServlet;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SellServlet
 */
@WebServlet("/Sell.html")
public class SellServlet extends BaseServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try
		{
			//1.��ȡҳ������
			Map<String,Object> dto=this.createDto(request);
			//��ȡ�û�ID
			HttpSession session = request.getSession(); 
			Map<String,String> user=(Map<String, String>) session.getAttribute("USERINFO");
			dto.put("sprincipal", user.get("uaccount"));
			SellServicesImpl services=new SellServicesImpl(dto);
			//��������ִ�з���
			services.executeTrans();
			request.setAttribute("msg", "�ύ�ɹ�!");
		}
		catch(Exception ex)
		{
			request.setAttribute("msg", "�������!");
			ex.printStackTrace();
		}
		request.getRequestDispatcher("/Sell.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		this.doGet(request, response);
	}

}
