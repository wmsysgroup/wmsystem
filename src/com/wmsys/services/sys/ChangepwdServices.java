package com.wmsys.services.sys;

import com.wmsys.services.support.JdbcServicesSupport;

public class ChangepwdServices extends JdbcServicesSupport {

	/**
	 * �޸��û�����
	 * @param oldpwd
	 * @param newpwd
	 * @throws Exception
	 */
	public boolean changepwd(String id,String oldpwd,String newpwd)throws Exception
	{
		//����SQL���
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
