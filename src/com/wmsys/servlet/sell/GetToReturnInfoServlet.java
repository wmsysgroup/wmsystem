package com.wmsys.servlet.sell;

import com.wmsys.services.sell.SellServicesImpl;
import com.wmsys.web.support.BaseServlet;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetToReturnInfoServlet
 */
@WebServlet("/GetToReturnInfo.html")
public class GetToReturnInfoServlet extends BaseServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try
		{
			//1.��ȡҳ������
			Map<String,Object> dto=this.createDto(request);
			SellServicesImpl services=new SellServicesImpl(dto);
			
			//���ò�ѯ����
			Map<String, String> row=services.queryForSale();
			//�жϲ�ѯ���
			if(row!=null)
			{
				//����ѯ���������JSP
				request.setAttribute("row", row);
			}
			else
			{
				request.setAttribute("msg", "��������ɾ�����ֹ����!");
			}
			
		}
		catch(Exception ex)
		{
			request.setAttribute("msg", "�������!");
			ex.printStackTrace();
		}
		request.getRequestDispatcher("/saleJSP/Return.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		this.doGet(request, response);
	}
}
