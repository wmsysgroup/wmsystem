package com.wmsys.services.depot.impl;

import java.util.List;
import java.util.Map;

import com.wmsys.services.depot.support.JdbcServiceSupport;

public class InBoundServImpl extends JdbcServiceSupport
{
	
	
	
	/*************************************************************
	 *              查询
	 ************************************************************/
	/**
	 * 单一查询编号
	 */
	@Override
	public Map<String, String> findByno(String arg) throws Exception 
	{
		StringBuilder sql=new StringBuilder()
				.append("SELECT p.plid,b.bgname,p.plquantity,p.plsupplier,p.plnumber")
				.append("  FROM purchaselist p,basicgoods b")
				.append(" WHERE p.plid=?");
		return this.query(sql.toString(), arg);
	}
	
	/**
	 * 查询（状态）
	 */
	@Override
	public List<Map<String, String>> findAll() throws Exception 
	{
		StringBuilder sql=new StringBuilder()
				.append("SELECT p.plid,b.bgname,p.plquantity,p.plsupplier")
				.append("  FROM purchaselist p,basicgoods b")
				.append(" WHERE p.plstate=?");
		return this.queryList(sql.toString(), 1);
	}
	
	
	public List<Map<String, String>> beforeout()throws Exception
	{
		StringBuilder sql=new StringBuilder()
				.append("SELECT i.inid,b.bgname,m.number")
				.append("  FROM margin m,inboundlist i,basicgoods b,purchaselist p")
				.append(" WHERE m.indid=i.inid")
				.append("   AND i.plid=p.plid")
				.append("   AND p.plnumber=b.bgnumber")
		        .append("   AND b.bgnumber=?");
		Object args[]={this.get("bgnumber")};
		return this.queryList(sql.toString(),args);
	}
	
	
	
	/*************************************************************
	 *              增加
	 ************************************************************/
	/**
	 * 入库清单添加操作
	 */
	@Override
	public void insertin() throws Exception 
	{
		StringBuilder sql=new StringBuilder()
				.append("insert into inboundlist(inid,plid,innumber,inquantity,indate,inprincipal,wnumber,remarks)")
				.append("              values(?,?,?,?,?,?,?,?)");
		Object args[]={
				this.get("inid"),
				this.get("plid"),
				this.get("innumber"),
				this.get("inquantity"),
				this.get("indate"),
				this.get("inprincipal"),
				this.get("wnumber"),
				this.get("remarks"),
				};
		System.out.println(args);
		this.addMsg(sql.toString(), args);
	}
	
	
	/**
	 * 库中损毁清单添加
	 */
	@Override
	public void insertdam() throws Exception 
	{
		StringBuilder sql=new StringBuilder()
				.append("insert into goodsdam(gdid,indid,gdnumber,gdquantity,gdprincipal,gdremarks)")
				.append("              values(?,?,?,?,?,?)");
		Object args[]={
				this.get("gdid"),
				this.get("indid"),
				this.get("gdnumber"),
				this.get("gdquantity"),
				this.get("gdprincipal"),
				this.get("gdremarks"),
		};
		
		this.addMsg(sql.toString(), args);
	}
	
	
	
	/**
	 * 出库清单添加
	 */
	@Override
	public void insertout() throws Exception 
	{
		StringBuilder sql=new StringBuilder()
				.append("insert into outboundlist(outid,indid,outnumber,outquantity,outdate,outprincipal,outstate,remarks)")
				.append("              values(?,?,?,?,?,?,?,?)");
		Object args[]={
				this.get("outid"),
				this.get("indid"),
				this.get("outnumber"),
				this.get("outquantity"),
				this.get("outdate"),
				this.get("outprincipal"),
				this.get("outstate"),
				this.get("remarks"),
		};
		
		this.addMsg(sql.toString(), args);
		
	}
	
	/**
	 * 货物入库
	 * <p>Title: indetp</p>  
	 * Description: </p>  
	 * @throws Exception
	 */
	public void indetp()throws Exception
	{
		StringBuilder sql=new StringBuilder()
				.append("insert into margin(indid,number)")
				.append("			 values(?,?)");
		Object args[]={
				this.get("indid"),
				this.get("number")
		};
		this.addMsg(sql.toString(), args);
	}
	
	
	
	/*************************************************************
	 *              修改
	 ************************************************************/
	/**
	 * 修改货物存量
	 * <p>Title: updatema</p>  
	 * Description: </p>  
	 * @throws Exception
	 */
	public void updatema()throws Exception
	{
		String sql="update margin set number=? where indid=?";
		Object args[]={
				this.get("number"),
				this.get("indid")
		};
		this.addMsg(sql, args);
				
	}
	
	
	/**
	 * 修改已入库进货清单状态
	 * <p>Title: updateSt</p>  
	 * Description: </p>  
	 * @throws Exception
	 */
	public void updateSt()throws Exception
	{
		String sql="update purchaselist set plstate=0 where plid=?";
		Object args[]={
				
				this.get("plid")
		};
		this.addMsg(sql, args);
	}
	
}
