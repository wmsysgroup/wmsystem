package com.wmsys.services.sell;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.HashMap;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;

import com.wmsys.system.db.DBUtils;
import com.wmsys.system.tools.Tools;
import com.wmsys.services.support.JdbcServicesSupport;

public final class SellServicesImpl extends JdbcServicesSupport 
{
	 Date date = new Date();//获得系统时间.
	//将时间格式转换成符合要求的格式
	 String nowTime = new SimpleDateFormat("yyyy-MM-dd").format(date);

	public SellServicesImpl(Map<String, Object> dto) 
	{
		super(dto);
	}
	
	
//	/**
//	 * 批处理获取待销售产品数额总量
//	 * @return
//	 * @throws Exception
//	 */
//	public List<Map<String, String>> getQuantity()throws Exception
//	{
//		//1.定义SQL语句
//		StringBuilder sql=new StringBuilder()
//		.append("select o.outid,o.outquantity")
//		.append("   from outboundlist o")
//		.append(" where outid=?")
//		;
//		//2.组织数据
//		Object args[]=this.getArray("idlist");
//		return this.queryByID(sql.toString(), args);
//		//执行
//	}
	
	/**
	 * 事务方法处理修改出货表单状态和添加销售表单
	 * @return
	 * @throws Exception
	 */
	public boolean executeTrans()throws Exception
	{
		boolean tag=false;
		try
		{
			this.appendModifySql();
			this.appendSalesListSql();
			this.executeTransaction();
			tag=true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return tag;
	}
	
	
	/**
	 * 修改出货表单状态
	 * @return
	 * @throws Exception
	 */
	protected void appendModifySql()throws Exception
	{
		//this.showDto();
		//1.定义SQL语句
		StringBuilder sql=new StringBuilder()
		.append("update outboundList")
		.append("   set outstate=0")
		.append(" where outid=?")
		;
		//2.组织数据
		Object args[]={this.get("outid")};
		//添加
		this.appendSql(sql.toString(), args);
	}
	
	
	/**
	 * 添加销售表单
	 * @return
	 * @throws Exception
	 */
	public void appendSalesListSql()throws Exception
	{
		//1.定义SQL语句
		StringBuilder sql=new StringBuilder()
		.append("insert into salesList(sid,outid,snumber,sprice,squantity,spurchaser,sdate,sprincipal,remarks)")
		.append(" values(?,?,?,?,?,?,?,?,?)")		
		;
		//2.组织数据
		Object args[]={
				Tools.listno("C"),this.get("outid"),this.get("snumber"),this.get("sprice"),this.get("squantity"),
				this.get("spurchaser"),nowTime.toString(),"待添",this.get("remarks")
		};
		//3.添加
		this.appendSql(sql.toString(), args);
	}
	
	
	/**
	 * 添加退货表单
	 * @return
	 * @throws Exception
	 */
	public void addReturnList()throws Exception
	{
		//1.定义SQL语句
		StringBuilder sql=new StringBuilder()
		.append("insert into returnList(rid,sid,rnumber,rquantity,rdate,rprincipal,remarks) ")
		.append(" values(?,?,?,?,?,?,?)")		
		;
		//2.组织数据
		Object args[]={
				Tools.listno("D"),this.get("sid"),this.get("rnumber"),this.get("rquantity"),
				nowTime.toString(),"待添",this.get("remarks")
		};
		//3.执行
		this.executeUpdate(sql.toString(), args);
	}
	
	
	
	/**
	 * 出货表单数据查询
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> queryForOutList()throws Exception
	{
		//1.从DTO还原查询条件
		Object bgname=this.get("pbgname");
		Object bglevel=this.get("pbglevel");
		Object bgprice=this.get("pbgprice");
		Object bgtype=this.get("pbgtype");
		Object outquantity=this.get("poutquantity");
		int sum=5;
		
		//2.拼接SQL语句
		StringBuilder sql=new StringBuilder()
				.append("select o.outid,o.outnumber,b.bgname,b.bglevel,b.bgtype,b.bgchandi,b.bgprice,o.outquantity")
				.append("  from basicGoods b,outboundList o")
				.append(" where b.bgnumber= o.outnumber ")
				.append("and o.outstate=1")
		;
		
		List<Object> paramList=new ArrayList<>(20);
		
		if(this.isNotNull(bgname))
		{
			sql.append(" and b.bgname like ?");
			paramList.add("%"+bgname+"%");
		}
		if(this.isNotNull(bglevel))
		{
			sql.append(" and b.bglevel=?");
			paramList.add(bglevel);
		}
		if(this.isNotNull(bgprice))
		{
			sql.append(" and bgprice=?");
			paramList.add(bgprice);
		}
		if(this.isNotNull(bgtype))
		{
			sql.append(" and b.bgtype=?");
			paramList.add(bgtype);
		}
		if(this.isNotNull(outquantity))
		{
			sql.append(" and o.outquantity=?");
			paramList.add(outquantity);
		}
		//sql.append(" limit  10");
		
		//执行
		return this.queryForList(sql.toString(), paramList.toArray());
	}
	
	/**
	 * 销售表单数据查询
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> queryForSaleList()throws Exception
	{
		//1.从DTO还原查询条件
		Object sid=this.get("saleid");
//		Object bgnumber=this.get("bgnumber");
		Object bgname=this.get("bgname");
		Object bsdate=this.get("bsdate");
		Object esdate=this.get("esdate");
		Object spurchaser=this.get("spurchaser");
		int sum=5;
		
		//2.拼接SQL语句
		StringBuilder sql=new StringBuilder()
				.append("select s.sid,b.bgnumber,b.bgname,s.sdate,s.spurchaser")
				.append("  from salesList s,basicGoods b")
				.append(" where s.snumber=b.bgnumber ")
		;
		
		List<Object> paramList=new ArrayList<>(20);
		
		if(this.isNotNull(sid))
		{
			sql.append(" and s.sid=?");
			paramList.add(sid);
		}
//		if(this.isNotNull(bgnumber))
//		{
//			sql.append(" and b.bgnumber=?");
//			paramList.add(bgnumber);
//		}
		if(this.isNotNull(bgname))
		{
			sql.append(" and b.bgname=?");
			paramList.add(bgname);
		}
		if(this.isNotNull(bsdate))
		{
			sql.append(" and s.sdate>=?");
			paramList.add(bsdate);
		}
		if(this.isNotNull(esdate))
		{
			sql.append(" and s.sdate<=?");
			paramList.add(esdate);
		}
		if(this.isNotNull(spurchaser))
		{
			sql.append(" and s.spurchaser=?");
			paramList.add(spurchaser);
		}
		//sql.append(" limit  10");
		
		//执行
		return this.queryForList(sql.toString(), paramList.toArray());
	}

	
	/**
	 * 出货表单数据单一查询
	 * @return
	 * @throws Exception
	 */
	public final Map<String,String> queryForOut()throws Exception{
		//1.定义SQL语句
				StringBuilder sql=new StringBuilder()
						.append("select b.bgname,b.bgnumber,b.bglevel,b.bgchandi,b.bgtype,b.bgexp,o.outquantity")
						.append("  from outboundList o,basicGoods b")
						.append(" where b.bgnumber= o.outnumber")
						.append(" and o.outid=?")
				;
				//2.组织数据
				Object args[]={this.get("outid")};
				//执行
				return this.queryForMap(sql.toString(), args);
	}

	/**
	 * 销售表单单一数据查询
	 * @return
	 * @throws Exception
	 */
	public final Map<String,String> queryForSale()throws Exception{
		//1.定义SQL语句
				StringBuilder sql=new StringBuilder()
						.append("select s.sid,b.bgname,s.sprice,b.bglevel,b.bgtype,s.spurchaser,s.snumber")
						.append("  from salesList s,basicGoods b")
						.append(" where s.snumber=b.bgnumber")
						.append(" and s.sid=?")
				;
				//2.组织数据
				Object args[]={this.get("sid")};
				//执行
				return this.queryForMap(sql.toString(), args);
	}
	
//	/**
//	 * 根据不同流水号查询数据
//	 * @return
//	 * @throws Exception
//	 */
//	protected final List<Map<String,String>> queryByID(final String sql,final Object...args)throws Exception
//	{
//		//1.定义JDBC接口
//		PreparedStatement pstm=null;
//		ResultSet rs=null;
//		try
//		{
//			pstm=DBUtils.prepareStatement(sql);
//			Map<String,String> ins=null;
//			List<Map<String,String>> rows=new ArrayList<>();
//			for(Object param:args)
//			{
//				pstm.setObject(1, param);
//				rs=pstm.executeQuery();
//				if(rs.next())
//				{
//					ResultSetMetaData rsmd=rs.getMetaData();
//					int count=rsmd.getColumnCount();
//					int initSize=((int)(count/0.75))+1;
//					ins=new HashMap<>(initSize);
//					for(int i=1;i<=count;i++)
//					{
//						ins.put(rsmd.getColumnLabel(i).toLowerCase(), rs.getString(i));
//					}
//					rows.add(ins);
//				}
//			}
//			return rows;
//		}
//		finally
//		{
//			DBUtils.close(rs);
//			DBUtils.close(pstm);
//		}
//	}
}
