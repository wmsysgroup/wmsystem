package com.wmsys.services.manager;

import java.util.Map;

import com.wmsys.services.support.JdbcServicesSupport;

/**
 * @author fire
 *
 */
public final class UserImpl extends JdbcServicesSupport {
	
	//ÐÂÕËºÅ
	private String newAccount;
	public String getNewAccount() {
		return newAccount;
	}
	private void setNewaccount()throws Exception
	{
		this.newAccount=this.getAccount();
	}

	/* 
	 *
	 * uname,udate,usex,utype,ustate,remarks
	 * according account to get msg type of map 
	 * 
	 */
	public final Map<String,String> findById()throws Exception
	{
		//1
		StringBuilder sql = new StringBuilder()
				.append("select a.uname,a.udate,a.usex,a.utype,a.ustate,a.remarks")
				.append("  from user a")
				.append(" where a.uaccount=?");
		//2
		Object args = this.get("uaccount");
		//3
		return this.queryForMap(sql.toString(), args);
	}
	
	/**
	 * @throws Exception
	 */
	public final boolean modify() throws Exception
	{
		//1sql
		StringBuilder sql = new StringBuilder()
				.append("update user ")
				.append("   set uname=?,udate=?,usex=?,utype=?,ustate=?,remarks=?")
				.append(" where uaccount = ?");
		//2
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
		//3
		
		this.showDto();
		
		return this.executeUpdate(sql.toString(), args)>0;
				
	}
	/**
	 * 
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
		
		//
		this.setNewaccount();
		Object args[]= {
				this.get("upassword"),
				this.get("uname"),
				this.get("usex"),
		        this.get("udate"),
				this.get("utype"),
				1,
				this.get("remarks"),
				newAccount
				
		};
		return this.executeUpdate(sql.toString(), args)>0;
	}

	private final String getAccount()throws Exception
	{
		long now = System.currentTimeMillis();
		String account = now+"";
		
		return account.substring(0,11);
	}
	

}
