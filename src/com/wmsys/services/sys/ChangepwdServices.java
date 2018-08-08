package com.wmsys.services.sys;

import com.wmsys.services.support.JdbcServicesSupport;

public class ChangepwdServices extends JdbcServicesSupport {

	/**
	 * 修改用户密码
	 * @param oldpwd
	 * @param newpwd
	 * @throws Exception
	 */
	public boolean changepwd(String id,String oldpwd,String newpwd)throws Exception
	{
		//定义SQL语句
		StringBuilder sql=new StringBuilder()
				.append("update user")
				.append("   set upassword=?")
				.append(" where ustate=?")
				.append("   and upassword=?")
				.append("   and uid=?")
				;
		Object args[]={newpwd,"1",oldpwd,id};
		return this.executeUpdate(sql.toString(),args)>0;
	}
}
