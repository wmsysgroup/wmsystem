package com.wmsys.services.manager;

import java.util.Map;

import com.wmsys.services.support.JdbcServicesSupport;

/**
 * @author fire
 *
 */
public final class UserImpl extends JdbcServicesSupport {
	

	

	/* 
	 * 人员查询
	 * 
	 * uname,udate,usex,utype,ustate,remarks
	 * according account to get msg type of map 
	 * 使用了dto中的 uaccount
	 */
	public final Map<String,String> findById()throws Exception
	{
		//1定义sql语句
		StringBuilder sql = new StringBuilder()
				.append("select a.uname,a.udate,a.usex,a.utype,a.ustate,a.remarks")
				.append("  from user a")
				.append(" where a.uaccount=?");
		//2获取要查的账户
		Object args = this.get("uaccount");
		//3调用父类方法返回map
		return this.queryForMap(sql.toString(), args);
	}
	
	/**
	 * 人员信息修改
	 * @return 是否修改成功
	 * @throws Exception
	 */
	public final boolean modify() throws Exception
	{
		//1构建sql
		StringBuilder sql = new StringBuilder()
				.append("update user ")
				.append("   set uname=?,udate=?,usex=?,utype=?,ustate=?,remarks=?")
				.append("where uaccount = ?");
		//2获取参数列表
		Object args[] = 
			{
				this.get("uname"),
				this.get("udate"),
				this.get("usex"),
				this.get("utype"),
				this.get("ustate"),
				this.get("remarks"),
				this.get("uaccount")
			};
		//3返回修改结果
		return this.executeUpdate(sql.toString(), args)>0;
				
	}
	/**
	 * 人员添加
	 * @return
	 * @throws Exception
	 */
	public final boolean addUser()throws Exception
	{
		//sql
		StringBuilder sql = new StringBuilder()
				.append("insert into user (upassword,uname,usex,udate,utype,ustate,remarks,uaccount)")  
				.append("          values (?,?,?,?,?,?,?,?)");
		                                  // 1 2 3 4 5 6 7 8 
		
		//构建参数数组
		Object args[]= {
				this.get("upassword"),
				this.get("uname"),
				this.get("usex"),
		        this.get("udate"),
				this.get("utype"),
				1,
				this.get("remarks"),
				this.get("uaccount")
				
		};
		return this.executeUpdate(sql.toString(), args)>0;
	}
//	/*
//	 * 获取随机账号 11位
//	 * 
//	 */
//	private static final String getAccount()throws Exception
//	{
//		long now = System.currentTimeMillis();
//		String account = now+"";
//		
//		return account.substring(0,11);
//	}
	

}
