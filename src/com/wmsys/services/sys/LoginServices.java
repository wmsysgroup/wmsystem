package com.wmsys.services.sys;

import java.util.*;

import com.wmsys.services.support.JdbcServicesSupport;

public class LoginServices extends JdbcServicesSupport 
{
	
	public Map<String,String> checkUser(String lname,String pwd)throws Exception
	{
		//1.定义SQL语句
		StringBuilder sql=new StringBuilder()
				.append("select a.uid,a.uname,a.utype")
				.append("  from user a")
				.append(" where a.ustate=?")
				.append("   and a.upassword=?")
				.append("   and a.uaccount=?")
				;
		Object args[]={"1",pwd,lname};
		return this.queryForMap(sql.toString(), args);
	}
	
	/**
	 * 检索用户菜单
	 * @param utype
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> getUserMenu(String utype)throws Exception
	{
		//1.定义SQL语句
		StringBuilder sql=new StringBuilder()
				.append("select a.mname,a.mopenpath")		
				.append("  from menu a")		
		;
		List<Object> paramList=new ArrayList<>(1);
		
		if(!utype.equals("0"))
		{
			sql.append(" where mtype=?");
			paramList.add(utype);
		}
		return this.queryForList(sql.toString(), paramList.toArray());
		  
	}
}








