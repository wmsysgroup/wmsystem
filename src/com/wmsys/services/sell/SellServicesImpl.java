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
import com.wmsys.services.support.SellServicesSupport;

public final class SellServicesImpl extends SellServicesSupport 
{
	 Date date = new Date();//���ϵͳʱ��.
	//��ʱ���ʽת���ɷ���Ҫ��ĸ�ʽ
	 String nowTime = new SimpleDateFormat("yyyy-MM-dd").format(date);

	public SellServicesImpl(Map<String, Object> dto) 
	{
		super(dto);
	}
	
	/**
	 * ���񷽷������޸ĳ�����״̬��������۱�
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
	 * �޸ĳ�����״̬
	 * @return
	 * @throws Exception
	 */
	protected void appendModifySql()throws Exception
	{
		//this.showDto();
		//1.����SQL���
		StringBuilder sql=new StringBuilder()
		.append("update outboundList")
		.append("   set outstate=0")
		.append(" where outid=?")
		;
		//2.��֯����
		Object args[]={this.get("outid")};
		//���
		this.appendSql(sql.toString(), args);
	}
	
	
	/**
	 * ������۱�
	 * @return
	 * @throws Exception
	 */
	public void appendSalesListSql()throws Exception
	{
		//1.����SQL���
		StringBuilder sql=new StringBuilder()
		.append("insert into salesList(sid,outid,snumber,sprice,squantity,spurchaser,sdate,sprincipal,remarks)")
		.append(" values(?,?,?,?,?,?,?,?,?)")		
		;
		//2.��֯����
		Object args[]={
				Tools.listno("C"),this.get("outid"),this.get("snumber"),this.get("sprice"),this.get("squantity"),
				this.get("spurchaser"),nowTime.toString(),this.get("sprincipal"),this.get("remarks")
		};
		//3.���
		this.appendSql(sql.toString(), args);
	}
	
	
	/**
	 * ����˻���
	 * @return
	 * @throws Exception
	 */
	public void addReturnList()throws Exception
	{
		//1.����SQL���
		StringBuilder sql=new StringBuilder()
		.append("insert into returnList(rid,sid,rnumber,rquantity,rdate,rprincipal,remarks) ")
		.append(" values(?,?,?,?,?,?,?)")		
		;
		//2.��֯����
		Object args[]={
				Tools.listno("D"),this.get("sid"),this.get("rnumber"),this.get("rquantity"),
				nowTime.toString(),"����",this.get("remarks")
		};
		//3.ִ��
		this.executeUpdate(sql.toString(), args);
	}
	
	
	
	/**
	 * ���������ݲ�ѯ
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> queryForOutList()throws Exception
	{
		//1.��DTO��ԭ��ѯ����
		Object bgname=this.get("pbgname");
		Object bglevel=this.get("pbglevel");
		Object bgprice=this.get("pbgprice");
		Object bgtype=this.get("pbgtype");
		Object outquantity=this.get("poutquantity");
		int sum=5;
		
		//2.ƴ��SQL���
		StringBuilder sql=new StringBuilder()
				.append("select o.outid,o.outnumber,b.bgname,sc2.sysvalue as type,sc4.sysvalue as level,b.bgchandi,b.bgprice,o.outquantity")
				.append("  from basicGoods b,outboundList o,")
				.append("(select sc1.sysvalue,sc1.syscode")
				.append(" from syscode sc1 where sc1.sysname='bgtype') sc2,")
				.append("(select sc3.sysvalue,sc3.syscode")
				.append(" from syscode sc3 where sc3.sysname='bglevel') sc4")
				.append(" where b.bgnumber= o.outnumber")
				.append(" and b.bgtype=sc2.syscode and b.bglevel=sc4.syscode")
				.append(" and o.outstate=1")
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
		
		//ִ��
		return this.queryForList(sql.toString(), paramList.toArray());
	}
	
	/**
	 * ���۱����ݲ�ѯ
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> queryForSaleList()throws Exception
	{
		//1.��DTO��ԭ��ѯ����
		Object sid=this.get("saleid");
//		Object bgnumber=this.get("bgnumber");
		Object bgname=this.get("bgname");
		Object bsdate=this.get("bsdate");
		Object esdate=this.get("esdate");
		Object spurchaser=this.get("spurchaser");
		int sum=5;
		
		//2.ƴ��SQL���
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
			sql.append(" and b.bgname like ?");
			paramList.add("%"+bgname+"%");
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
		
		//ִ��
		return this.queryForList(sql.toString(), paramList.toArray());
	}

	
	/**
	 * ���������ݵ�һ��ѯ
	 * @return
	 * @throws Exception
	 */
	public final Map<String,String> queryForOut()throws Exception{
		//1.����SQL���
				StringBuilder sql=new StringBuilder()
						.append("select b.bgname,b.bgnumber,sc2.sysvalue as type,sc4.sysvalue as level,b.bgchandi,b.bgexp,o.outquantity,b.bgprice")
						.append("  from outboundList o,basicGoods b,")
						.append("(select sc1.sysvalue,sc1.syscode")
						.append(" from syscode sc1 where sc1.sysname='bgtype') sc2,")
						.append("(select sc3.sysvalue,sc3.syscode")
						.append(" from syscode sc3 where sc3.sysname='bglevel') sc4")
						.append(" where b.bgnumber= o.outnumber")
						.append(" and b.bgtype=sc2.syscode and b.bglevel=sc4.syscode")
						.append(" and o.outid=?")
				;
				//2.��֯����
				Object args[]={this.get("outid")};
				//ִ��
				return this.queryForMap(sql.toString(), args);
	}

	/**
	 * ���۱���һ���ݲ�ѯ
	 * @return
	 * @throws Exception
	 */
	public final Map<String,String> queryForSale()throws Exception{
		//1.����SQL���
				StringBuilder sql=new StringBuilder()
						.append("select s.sid,s.snumber,b.bgname,s.sprice,sc2.sysvalue as type,sc4.sysvalue as level,s.spurchaser")
						.append(" from salesList s,basicGoods b,")
						.append("(select sc1.sysvalue,sc1.syscode")
						.append(" from syscode sc1 where sc1.sysname='bgtype') sc2,")
						.append("(select sc3.sysvalue,sc3.syscode")
						.append(" from syscode sc3 where sc3.sysname='bglevel') sc4")
						.append(" where s.snumber=b.bgnumber")
						.append(" and b.bgtype=sc2.syscode and b.bglevel=sc4.syscode")
						.append(" and s.sid=?")
				;
				//2.��֯����
				Object args[]={this.get("sid")};
				//ִ��
				return this.queryForMap(sql.toString(), args);
	}
	
//	/**
//	 * ���ݲ�ͬ��ˮ�Ų�ѯ����
//	 * @return
//	 * @throws Exception
//	 */
//	protected final List<Map<String,String>> queryByID(final String sql,final Object...args)throws Exception
//	{
//		//1.����JDBC�ӿ�
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
