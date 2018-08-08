package com.wmsys.services.depot.impl;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wmsys.services.depot.support.JdbcServiceSupport;
import com.wmsys.system.db.DBUtils;

public class InBoundServImpl extends JdbcServiceSupport
{
	
	
	
	/*************************************************************
	 *              查询
	 ************************************************************/
	/**
	 * 单一查询编号
	 */
	@Override
	public Map<String, Object> findByno() throws Exception 
	{
		StringBuilder sql=new StringBuilder()
				.append("SELECT p.plid,b.bgname,p.plquantity,p.plsupplier,p.plnumber")
				.append("  FROM purchaseList p,basicGoods b")
				.append(" WHERE p.plid=?");
		return this.query(sql.toString(), this.get("plid"));
	}
	
	/**
	 * 查询（状态） 1，尚未入库
	 */
	@Override
	public List<Map<String, Object>> findAll() throws Exception 
	{
		StringBuilder sql=new StringBuilder()
				.append("SELECT p.plid,b.bgname,p.plquantity,p.plsupplier")
				.append("  FROM purchaseList p,basicGoods b")
				.append(" WHERE p.plstate=? and b.bgnumber=p.plnumber");
		return this.queryList(sql.toString(), 1);
	}
	
	
	/**
	 * 货物存量查询，出库
	 * <p>Title: beforeout</p>  
	 * Description: </p>  
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> beforeout()throws Exception
	{
		StringBuilder sql=new StringBuilder()
				.append("SELECT i.inid,b.bgname,m.number")
				.append("  FROM margin m,InboundList i,basicGoods b,purchaseList p")
				.append(" WHERE m.indid=i.inid")
				.append("   AND i.plid=p.plid")
				.append("   AND p.plnumber=b.bgnumber")
		        .append("   AND b.bgnumber=?");
		Object args[]={this.get("bgnumber")};
		return this.queryList(sql.toString(),args);
	}
	
	/**
	 * 库存余量查询 （indid）
	 * <p>Title: findnum</p>  
	 * Description: </p>  
	 * @return
	 * @throws Exception
	 */
	public BigDecimal findnum()throws Exception
	{
		String sql="select number from margin where indid=?";
		Object arg=this.get("indid");
		
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try
		{
			pstm=DBUtils.prepareStatement(sql);
			pstm.setObject(1, arg);
			rs=pstm.executeQuery();
			
			Map<String,Object> ins=null;
			if(rs.next())
			{
				ResultSetMetaData rsmd=rs.getMetaData();
				
				//初始化ins装载数据
				int count=rsmd.getColumnCount();
				int initSize=((int)(count/0.75))+1;
				ins=new HashMap<>(initSize);
				
				for(int i=1;i<=count;i++)
				{
					ins.put(rsmd.getColumnLabel(i).toLowerCase(), rs.getString(i));
				}
			 }
			return rs.getBigDecimal("number");
		}
		
		finally
		{
			DBUtils.close(rs);
			DBUtils.close(pstm);
		}
	}
	
	public Date finddate()throws Exception
	{
		String sql="select indate from InboundList where inid=?";
		Object arg=this.get("inid");
		
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try
		{
			pstm=DBUtils.prepareStatement(sql);
			pstm.setObject(1, arg);
			rs=pstm.executeQuery();
			
			Map<String,Object> ins=null;
			if(rs.next())
			{
				ResultSetMetaData rsmd=rs.getMetaData();
				
				//初始化ins装载数据
				int count=rsmd.getColumnCount();
				int initSize=((int)(count/0.75))+1;
				ins=new HashMap<>(initSize);
				
				for(int i=1;i<=count;i++)
				{
					ins.put(rsmd.getColumnLabel(i).toLowerCase(), rs.getString(i));
				}
			 }
			return rs.getDate("indate");
		}
		
		finally
		{
			DBUtils.close(rs);
			DBUtils.close(pstm);
		}
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
				.append("insert into InboundList(inid,plid,innumber,inquantity,indate,inprincipal,wnumber,remarks)")
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
		this.addMsg(sql.toString(), args);
	}
	
	
	/**
	 * 库中损毁清单添加
	 */
	@Override
	public void insertdam() throws Exception 
	{
		StringBuilder sql=new StringBuilder()
				.append("insert into goodsDam(gdid,indid,gdnumber,gdquantity,gdprincipal,gdremarks)")
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
				.append("insert into outboundList(outid,indid,outnumber,outquantity,outdate,outprincipal,outstate,remarks)")
				.append("              values(?,?,?,?,?,?,?,?)");
		Object args[]={
				this.get("outid"),
				this.get("indid"),
				this.get("outnumber"),
				this.get("outquantity"),
				this.get("outdate"),
				this.get("outprincipal"),
				"1",
				"",
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
				this.get("inquantity")
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
	 * 修改已入库进货清单状态   0,已入库
	 * <p>Title: updateSt</p>  
	 * Description: </p>  
	 * @throws Exception
	 */
	public void updateSt()throws Exception
	{
		String sql="update purchaseList set plstate=0 where plid=?";
		Object args[]={
				
				this.get("plid")
		};
		this.addMsg(sql, args);
	}
	
	/**
	 * 库中存量耗尽，删除
	 * <p>Title: deleteMl</p>  
	 * Description: </p>  
	 * @throws Exception
	 */
	public void deleteMl()throws Exception
	{
		String sql="delete from margin where indid=?";
		Object args[]={				
				this.get("indid")
		};
		this.addMsg(sql, args);
	}
}
