package com.wmsys.servlet.sys;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wmsys.services.sys.LoginServices;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(value="/login")
public class LoginServlet extends HttpServlet
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String toPath="login.jsp";
		try
		{
			//��ȡҳ������
			String lname=request.getParameter("lname");
			String pwd=request.getParameter("pwd");
			
			//ʵ����Services
			LoginServices services=new LoginServices();
			//��ȡ��ǰ�û���Ϣ
			Map<String,String> userInfo=services.checkUser(lname, pwd);
			if(userInfo!=null)
			{
				//����ǰ�û�����Ϣ�洢��session��
				request.getSession().setAttribute("USERINFO", userInfo);
				
				//�����û�����ȡ�û��˵�
				List<Map<String,String>> menuList=services.getUserMenu(userInfo.get("utype"));
				if(menuList.size()>0)
				{
					//���û��˵�����session
					request.getSession().setAttribute("menuList", menuList);
				}
				
				toPath="main.jsp";
			}
			else
			{
				request.setAttribute("msg", "�û������������!");
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		request.getRequestDispatcher("/"+toPath).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		this.doGet(request, response);
	}

}
