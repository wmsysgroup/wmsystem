package com.wmsys.servlet.sys;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wmsys.services.sys.ChangepwdServices;

@WebServlet(value="/changepwd")
public class ChangepwdServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String toPath="changepwd.jsp";
		try
		{
			//获取页面数据
			String oldpwd=request.getParameter("oldpwd");
			String newpwd=request.getParameter("newpwd");
			String newpwd_confirm=request.getParameter("newpwd_confirm");
			
			//实例化Services
			ChangepwdServices services=new ChangepwdServices();
			HttpSession session=request.getSession();
			Map<String,String> userInfo=(Map<String, String>) session.getAttribute("USERINFO");
			String id=userInfo.get("uid");
			
			if(newpwd!=null && oldpwd!=null && newpwd_confirm!=null)
			{
				if(newpwd.equals(newpwd_confirm))
				{
					if(services.changepwd(id,oldpwd,newpwd))
					{
						request.setAttribute("msg", "修改成功!");
					}
					else
					{
						request.setAttribute("msg", "原密码输入有误!");
					}
				}
				else
				{
					request.setAttribute("msg", "新密码前后输入不一致！");
				}
			}
			else
			{
				request.setAttribute("msg", "密码栏不能为空!");
			}
			
			request.getRequestDispatcher("/"+toPath).forward(request, response);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		this.doGet(request, response);
	}
}
