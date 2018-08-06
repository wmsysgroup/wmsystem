package com.wmsys.servlet.sell;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wmsys.services.sell.SellServicesImpl;
import com.wmsys.web.support.BaseServlet;

/**
 * Servlet implementation class getToSaleInfo
 * �ӳ�������ȡ��ش����۲�Ʒ������Ϣ
 */
@WebServlet("/getToSaleInfo.html")
public class GetToSaleInfoServlet extends BaseServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try
		{
			//1.��ȡҳ������
			Map<String,Object> dto=this.createDto(request);
			SellServicesImpl services=new SellServicesImpl(dto);
			//���ò�ѯ����
			Map<String, String> rows=services.queryForOut();
			//�жϲ�ѯ���
			if(rows.size()>0)
			{
				//����ѯ���������JSP
				request.setAttribute("rows", rows);
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
		request.getRequestDispatcher("/saleJSP/Sell.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		this.doGet(request, response);
	}

}
