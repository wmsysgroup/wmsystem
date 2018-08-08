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
			//获取页面数据
			String lname=request.getParameter("lname");
			String pwd=request.getParameter("pwd");
			
			//实例化Services
			LoginServices services=new LoginServices();
			//获取当前用户信息
			Map<String,String> userInfo=services.checkUser(lname, pwd);
			if(userInfo!=null)
			{
				//将当前用户的信息存储到session中
				request.getSession().setAttribute("USERINFO", userInfo);
				
				//依据用户类别获取用户菜单
				List<Map<String,String>> menuList=services.getUserMenu(userInfo.get("utype"));
				if(menuList.size()>0)
				{
					//将用户菜单存入session
					request.getSession().setAttribute("menuList", menuList);
				}
				
				toPath="main.jsp";
			}
			else
			{
				request.setAttribute("msg", "用户名或密码错误!");
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
