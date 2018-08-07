package com.neusoft.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.neusoft.services.support.JdbcServicesSupport;

public class RiceImpl extends JdbcServicesSupport 
{
	@Override
	public List<Map<String, String>> query() throws Exception 
	{
		//
		Object bgname=this.get("bgname");
		Object bglevel=this.get("bglevel");
		Object bgchandi=this.get("bgchandi");
		Object bgtype=this.get("bgtype");
		//sql
		StringBuilder sql =new StringBuilder()
				.append("select a.bgnumber,a.bgname,b.sysvalue bglevel,c.sysvalue bgexp,d.sysvalue bgspec,a.bgprice,a.bgchandi,e.sysvalue bgtype,a.remarks")
				.append("  from basicGoods a,syscode b,syscode c,syscode d,syscode e")
				.append(" where a.bglevel = b.syscode and b.sysname ='bglevel'")
				.append("   and a.bgexp = c.syscode and c.sysname ='bgexp'")
				.append("   and a.bgspec = d.syscode and d.sysname = 'bgspec'")
		        .append("   and a.bgtype = e.syscode and e.sysname = 'bgtype'");
		
		List<Object> paramList=new ArrayList<>(20);
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
		if(this.isNotNull(bgchandi))
		{
			sql.append("and bgchandi like ?");
			paramList.add("%"+bgchandi+"%");
		}
		if(this.isNotNull(bgtype))
		{
			sql.append("and bgtype =?");
			paramList.add(bgtype);
		}
		//
		sql.append(" limit  10");
		//¡Œ
//		List<Map<String,String>> map =this.queryForList(sql.toString(), paramList.toArray());
//		System.out.println(map);
		return this.queryForList(sql.toString(), paramList.toArray());
	}
	//
	public final boolean modify()throws Exception
	{
		//sql
		StringBuilder sql = new StringBuilder()
				.append("update basicGoods ")
				.append("  set bgname= ?,bglevel=?,bgspec=?,bgprice=?,bgtype=?,bgexp=?,bgchandi=?,remarks=?")
				.append("where bgnumber=?");
		//
		Object args[]=
			{
					this.get("bgname"),
					this.get("bglevel"),
					this.get("bgspec"),
					this.get("bgprice"),
					this.get("bgtype"),
					this.get("bgexp"),
					this.get("bgchandi"),
					this.get("remarks"),
					this.get("bgnumber"),
			};
		//
		return this.executeUpdate(sql.toString(), args)>0;
		
	}
	
	//¯¢
	public final Map<String,String> findById()throws Exception
	{
		StringBuilder sql = new StringBuilder()
				.append("select bgname,bglevel,bgexp,bgspec,bgchandi,bgprice,bgtype,remarks")
				.append("  from basicGoods")
				.append(" where bgnumber=?");
				
		Object arg = this.get("bgnumber");
		System.out.println(arg);
		
		return this.queryForMap(sql.toString(), arg);
		
	}

}
