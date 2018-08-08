package com.wmsys.services.support;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wmsys.services.support.PstmBean;
import com.wmsys.system.db.DBUtils;

public abstract class SellServicesSupport 
{
	/**********************************************************
	 *         数据传输对象相关方法
	 **********************************************************/
	private Map<String,Object>  dto=null;
	public SellServicesSupport(Map<String,Object> dto) 
	{
		this.dto=dto;
	}
	
	/**
	 * 获取DTO中的数据
	 * @param key
	 * @return
	 */
	protected final Object get(String key)
	{
		return this.dto.get(key);
	}
	
	protected final void showDto()
	{
		System.out.println(this.dto);
	}
	
	/**
	 * 将指定的数据还原成数组
	 * @param key
	 * @return
	 */
	protected final String[] getArray(String key)
	{
		Object val=this.dto.get(key);
		if(val instanceof java.lang.String[])
		{
			return (String[])val;
		}
		else
		{
			String tem[]={val.toString()};
			return tem;
			//return new String[]{val.toString()};
		}	
	}

	
	/***************************************************
	*    辅助方法
	***************************************************/
	/**
	 * 空值校验
	 * @param args
	 * @return
	 */
    protected final boolean isNotNull(final Object args)
    {
    	return args!=null && !args.equals("");
    }
	
	
   /***************************************************
	*    以下为查询封装方法
	***************************************************/
	
	protected final List<Map<String,String>> queryForList(final String sql,final Object...args)throws 	Exception
	{
		//1.定义JDBC接口
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try
		{
			pstm=DBUtils.prepareStatement(sql.toString());
			if(args!=null)
			{
				int index=1;
				for(Object param:args)
				{
					pstm.setObject(index++, param);
				}	
			}
			
			
			rs=pstm.executeQuery();
			//获取结果集描述对象
			ResultSetMetaData rsmd=rs.getMetaData();
			//计算列数
			int count=rsmd.getColumnCount();
			//计算初始容量
			int initSize=((int)(count/0.75))+1;
			
			
			//定义List 装载查询结果
			List<Map<String,String>> rows=new ArrayList<>();
			//定义容器变量,装载每行数据
			Map<String,String> ins=null;
			
			//循环解析rs
			while(rs.next())
			{
				//实例化装载当前行数据的HashMap
				ins=new HashMap<>(initSize);
				//循环读取每列数据
				for(int i=1;i<=count;i++)
				{
					ins.put(rsmd.getColumnLabel(i).toLowerCase(), rs.getString(i));
				}
				//将当前行放入List
				rows.add(ins);
			}
			return rows;
		}
		finally
		{
			DBUtils.close(rs);
			DBUtils.close(pstm);
		}
	}
	

	
	
	/**
	 * 单一实例查询
	 * @param sql
	 * @param args
	 * @return
	 * @throws Exception
	 */
	protected final Map<String,String> queryForMap(final String sql,final Object...args)throws Exception
	{
		//1.定义JDBC接口
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try
		{
			pstm=DBUtils.prepareStatement(sql);
			int index=1;
			for(Object param:args)
			{
				pstm.setObject(index++, param);
			}
			rs=pstm.executeQuery();
			
			Map<String,String> ins=null;
			if(rs.next())
			{
				ResultSetMetaData rsmd=rs.getMetaData();
				int count=rsmd.getColumnCount();
				int initSize=((int)(count/0.75))+1;
				ins=new HashMap<>(initSize);
				for(int i=1;i<=count;i++)
				{
					ins.put(rsmd.getColumnLabel(i).toLowerCase(), rs.getString(i));
				}
			}
			return ins;
		}
		finally
		{
			DBUtils.close(rs);
			DBUtils.close(pstm);
		}
	}
	
	
	
   /***************************************************
    *    单一表批处理事务封装
    ***************************************************/
	/**
	 * 
	 * @param sql
	 *    update person
	 *       set pname=?,pnumber=?,psex=?,psr=?
	 *     where pid=?
	 * @param newStateTable[][]={
	 *              {"王兴刚","110","1","1979-12-26"},
	 *              {"老王","110","1","1979-12-26"},
	 *              {"还是我","110","1","1979-12-26"} 
	 *        };
	 * @param idlist[]={"17","18","19"}
	 * @return
	 * @throws Exception
	 */
	protected final boolean batchUpdate(final String sql,Object[][] newStateTable,final Object...idlist)throws Exception
	{
		PreparedStatement pstm=null;
		try
		{
			pstm=DBUtils.prepareStatement(sql);
			int id_index=0;
			//1.对状态列表进行循环,读取出一行的数据
			for(Object[] newStateList:newStateTable)
			{
				//定义index,表示sql中?的索引
				int index=1;
				//完成set列表的赋值
				for(Object newState:newStateList)
				{
					pstm.setObject(index++, newState);
				}
				//补充主键的赋值
				pstm.setObject(index, idlist[id_index++]);
				pstm.addBatch();
			}
			return this.executeBatchTransaction(pstm);
		}
		finally
		{
			DBUtils.close(pstm);
		}
	}

	
	/**
	 * 
	 * @param sql
	 *    update person
	 *       set pname=?,pnumber=?,psex=?,psr=?
	 *     where pid=?
	 * @param newStateTable[][]={
	 *              {"王兴刚","110","1","1979-12-26","17"},
	 *              {"老王","110","1","1979-12-26","18"},
	 *              {"还是我","110","1","1979-12-26","19"} 
	 *        };
	 * @return
	 * @throws Exception
	 */
	protected final boolean batchUpdate(final String sql,Object[][] newStateTable)throws Exception
	{
		PreparedStatement pstm=null;
		try
		{
			pstm=DBUtils.prepareStatement(sql);
			//1.对状态列表进行循环,读取出一行的数据
			for(Object[] newStateList:newStateTable)
			{
				//定义index,表示sql中?的索引
				int index=1;
				//完成每行数据的赋值
				for(Object newState:newStateList)
				{
					pstm.setObject(index++, newState);
				}
				pstm.addBatch();
			}
			return this.executeBatchTransaction(pstm);
		}
		finally
		{
			DBUtils.close(pstm);
		}
	}
	
	/**
	 * 多状态批量更新
	 * <
	 *   update table 
	 *      set col1=?,col2=?,col3=?..........colN=?
	 *    where id=? 
	 *    
	 *   举个例子:
	 *   update person set pstate=?,psal=psal+? where pid=?
	 *   newStateList[]={"2","500"}
	 *   idlist[]={"24","25","26"}; 
	 * >
	 * @param sql
	 * @param newStateList   ----  状态列表
	 * @param idlist         ----  主键列表 
	 * @return
	 * @throws Exception
	 */
	protected final boolean batchUpdate(final String sql,Object[] newStateList,final Object...idlist)throws Exception
	{
		PreparedStatement pstm=null;
		try
		{
			pstm=DBUtils.prepareStatement(sql);
			//参数索引
			int index=1;
			//完成set列表的赋值
			for(Object newState:newStateList)  //newState=500
			{
				pstm.setObject(index++, newState);  //index=3
			}
			//完成主键赋值
			for(Object id:idlist)
			{
				pstm.setObject(index, id);
				pstm.addBatch();
			}
			return this.executeBatchTransaction(pstm);
		}
		finally
		{
			DBUtils.close(pstm);
		}
	}
	
	/**
	 * 单一状态的批量更新
	 * <
	 *   适合的语句
	 *   update table 
	 *      set col=?
	 *    where pid=?
	 *   举个列子:员工批量退休  
	 * >
	 * @param sql
	 * @param newState   --- 选中人员,指定数据的新状态
	 * @param idlist     --- 主键列表
	 * @return
	 * @throws Exception
	 */
	protected final boolean batchUpdate(final String sql,Object newState,final Object...idlist)throws Exception
	{
		PreparedStatement pstm=null;
		try
		{
			pstm=DBUtils.prepareStatement(sql);
			pstm.setObject(1, newState);
			for(Object id:idlist)
			{
				pstm.setObject(2, id);
				pstm.addBatch();
			}
			
			return this.executeBatchTransaction(pstm);
		}
		finally
		{
			DBUtils.close(pstm);
		}
	}
	
	
	/**
	 * 单一表批处理
	 * <
	 *   适合的语法格式:
	 *   delete from table where id=? 
	 * >
	 * @param idlist
	 * @return
	 * @throws Exception
	 */
	protected final boolean batchUpdate(final String sql,final Object...idlist)throws Exception
	{
		PreparedStatement pstm=null;
		try
		{
			pstm=DBUtils.prepareStatement(sql);
			for(Object id:idlist)
			{
				pstm.setObject(1, id);
				pstm.addBatch();
			}
			return this.executeBatchTransaction(pstm);
		}
		finally
		{
			DBUtils.close(pstm);
		}
	}
	
	private boolean executeBatchTransaction(final PreparedStatement pstm)throws Exception
	{
		boolean tag=false;
		DBUtils.beginTransaction();
		try
		{
			pstm.executeBatch();
			DBUtils.commit();
			
			tag=true;
		}
		catch(Exception ex)
		{
			DBUtils.rollback();
			ex.printStackTrace();
		}
		finally
		{
			DBUtils.endTransaction();
		}
		return tag;
	}
	
	
   /***************************************************
    *    多表关联更新事务处理
    ***************************************************/
	
	//定义变量,装载事务执行期间的所有语句对象
	private final List<PstmBean> pstmList=new ArrayList<>();
	
	protected final void appendBatchSql(final String sql,Object[][] newStateTable,final Object...idlist)throws Exception
	{
		PreparedStatement pstm=DBUtils.prepareStatement(sql);
		int id_index=0;
		//1.对状态列表进行循环,读取出一行的数据
		for(Object[] newStateList:newStateTable)
		{
			//定义index,表示sql中?的索引
			int index=1;
			//完成set列表的赋值
			for(Object newState:newStateList)
			{
				pstm.setObject(index++, newState);
			}
			//补充主键的赋值
			pstm.setObject(index, idlist[id_index++]);
			pstm.addBatch();
		}
		PstmBean bean=new PstmBean(pstm, true);
		this.pstmList.add(bean);
	}
	
	protected final void appendBatchSql(final String sql,Object[][] newStateTable)throws Exception
	{
		PreparedStatement pstm=DBUtils.prepareStatement(sql);
		//1.对状态列表进行循环,读取出一行的数据
		for(Object[] newStateList:newStateTable)
		{
			//定义index,表示sql中?的索引
			int index=1;
			//完成每行数据的赋值
			for(Object newState:newStateList)
			{
				pstm.setObject(index++, newState);
			}
			pstm.addBatch();
		}
		
		PstmBean bean=new PstmBean(pstm, true);
		this.pstmList.add(bean);
		
	}
	
	protected final void appendBatchSql(final String sql,Object[] newStateList,final Object...idlist)throws Exception
	{
		PreparedStatement pstm=DBUtils.prepareStatement(sql);

		//参数索引
		int index=1;
		//完成set列表的赋值
		for(Object newState:newStateList)  //newState=500
		{
			pstm.setObject(index++, newState);  //index=3
		}
		//完成主键赋值
		for(Object id:idlist)
		{
			pstm.setObject(index, id);
			pstm.addBatch();
		}

		PstmBean bean=new PstmBean(pstm, true);
		this.pstmList.add(bean);
	}

	
	protected final void appendBatchSql(final String sql,Object newState,final Object...idlist)throws Exception
	{
		PreparedStatement pstm=DBUtils.prepareStatement(sql);
		pstm.setObject(1, newState);
		for(Object id:idlist)
		{
			pstm.setObject(2, id);
			pstm.addBatch();
		}
		PstmBean bean=new PstmBean(pstm, true);
		this.pstmList.add(bean);
	}
	
	protected final void appendBatchSql(final String sql,final Object...idlist)throws Exception
	{
		PreparedStatement pstm=DBUtils.prepareStatement(sql);
		for(Object id:idlist)
		{
			pstm.setObject(1, id);
			pstm.addBatch();
		}
		
		PstmBean bean=new PstmBean(pstm, true);
		this.pstmList.add(bean);
	}
	
	/**
	 * 为事务注册非批处理SQL语句
	 * @param sql
	 * @param args
	 * @throws Exception
	 */
	protected final void appendSql(final String sql,final Object...args)throws Exception
	{
		//1.编译SQL语句
		PreparedStatement pstm=DBUtils.prepareStatement(sql);
		//2.完成参数赋值
		int index=1;
		for(Object param:args)
		{
			pstm.setObject(index++, param);
		}
		//基于该语句对象构建描述对象
		PstmBean bean=new PstmBean(pstm, false);
		//添加语句列表
		this.pstmList.add(bean);
	}
	
	/**
	 * 多表关联更新事务执行方法
	 * @return
	 * @throws Exception
	 */
	protected final boolean executeTransaction()throws Exception
	{
		//1.定义事务返回值
		boolean tag=false;
		//2.开启事务
		DBUtils.beginTransaction();
		try
		{
			//执行事务中的所有SQL语句
			for(PstmBean bean:this.pstmList)
			{
				//执行语句
				bean.execute();
			}
			//提交事务
			DBUtils.commit();
			tag=true;
		}
		catch(Exception ex)
		{
			DBUtils.rollback();
			ex.printStackTrace();
		}
		finally
		{
			DBUtils.endTransaction();
			//1.销毁事务中的所有语句对象
			for(PstmBean bean:this.pstmList)
			{
				bean.close();
			}
			//清空语句对象列表
			this.pstmList.clear();
		}
		return tag;
	}
	
   /***************************************************
    *   单一SQL非事务更新方法
    ***************************************************/
	/**
	 * 单一语句更新
	 * @param sql   --- 需要执行的SQL语句
	 * @param args  --- 参数值列表
	 * @throws Exception
	 */
	protected final int executeUpdate(final String sql,final Object...args)throws Exception
	{
		//1.定义JDBC变量
		PreparedStatement pstm=null;
		try
		{
			pstm=DBUtils.prepareStatement(sql);
			int index=1;
			for(Object param:args)
			{
				pstm.setObject(index++, param);
			}
			return pstm.executeUpdate();
		}
		finally
		{
			DBUtils.close(pstm);
		}
		
	}
	
}








