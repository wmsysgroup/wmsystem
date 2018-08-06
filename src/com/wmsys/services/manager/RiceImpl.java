package com.wmsys.services.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wmsys.services.support.JdbcServicesSupport;

public class RiceImpl extends JdbcServicesSupport 
{
	@Override
	public List<Map<String, String>> query() throws Exception 
	{
		//还原查询条件
		Object bgname=this.get("bgname");
		Object bglevel=this.get("bglevel");
		Object bgchandi=this.get("bgchandi");
		Object bgtype=this.get("bgtype");
		//sql
		StringBuilder sql =new StringBuilder()
				.append("select a.bgnumber,a.bgname,b.sysvalue bglevel,c.sysvalue bgexp,d.sysvalue bgspec,a.bgprice,a.bgchandi,a.bgtype,a.remarks")
				.append("  from basicGoods a,syscode b,syscode c,syscode d")
				.append(" where a.bglevel = b.syscode and b.sysname ='bglevel'")
				.append("   and a.bgexp = c.syscode and c.sysname ='bgexp'")
				.append("   and a.bgspec = d.syscode and d.sysname = 'bgspec'");
		
		List<Object> paramList=new ArrayList<>(20);
		paramList.add("x");
		if(this.isNotNull(bgname))
		{
			sql.append("and bgname like ?");
			paramList.add("%"+bgname+"%");
		}
		if(this.isNotNull(bglevel))
		{
			sql.append("and bglevel = ?");
			paramList.add(bglevel);
		}
		if(this.isNotNull("bgchandi"))
		{
			sql.append("and bgchandi like ?");
			paramList.add("%"+bgchandi+"%");
		}
		if(this.isNotNull(bgtype))
		{
			sql.append("and bgtype =?");
			paramList.add(bgtype);
		}
		//控制显示条数
		sql.append(" limit  10");
		
		//执行
	    return this.queryForList(sql.toString(), paramList.toArray());
	}

}
