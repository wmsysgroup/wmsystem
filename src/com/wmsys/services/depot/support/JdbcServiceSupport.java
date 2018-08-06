package com.wmsys.services.depot.support;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wmsys.system.db.DBUtils;

public abstract class JdbcServiceSupport implements BaseServices 
{
	
	//定义数据处理对象DTO
	Map<String,Object> dto=null;
	
	/**
	 * 赋值DTO
	 * <p>Title: setMapDto</p>  
	 * Description: </p>  
	 * @param dto
	 */
	public void setMapDto(Map<String,Object> dto)
	{
		this.dto=dto;
	}
	
	/**
	 * 获取dto键值对
	 * <p>Title: get</p>  
	 * Description: </p>  
	 * @param key
	 * @return
	 */
	public Object get(String key)
	{
		return this.dto.get(key);
	}

	/**
	 * 展示dto方法
	 * <p>Title: show</p>  
	 * Description: </p>
	 */
	public void show()
	{
		System.out.println(this.dto);
	}
	
	/*
	 * 空值检验
	 */
	public boolean idNotNull(Object value)
	{
		return value!=null&& !value.equals("");
	}
	
	
	/**
	 * 按主键单一查询方法
	 * <p>Title: queryList</p>  
	 * Description: </p>  
	 * @param sql
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> query(String sql,String args)throws Exception
	{
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try
		{
			pstm=DBUtils.prepareStatement(sql);
			pstm.setObject(1, args);
			rs=pstm.executeQuery();
			
			Map<String,String> ins=null;
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
			return ins;
		}
		
		finally
		{
			DBUtils.close(rs);
			DBUtils.close(pstm);
		}
	}
	
	
	/**
	 * 批量查询结果方法
	 * <p>Title: queryList</p>  
	 * Description: </p>  
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> queryList(String sql,Object arg)throws Exception
	{
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try
		{
			pstm=DBUtils.prepareStatement(sql);
			pstm.setObject(1, arg);
			
			rs=pstm.executeQuery();
			ResultSetMetaData rsmd=rs.getMetaData();
			
			//计算列数
			int count=rsmd.getColumnCount();
			//计算初始容量
			int initSize=((int)(count/0.75))+1;
			
			//定义List 装载查询结果
			List<Map<String,String>> rows=new ArrayList<>();
			//定义容器变量,装载每行数据
			Map<String,String> ins=null;
			
			while(rs.next())
			{
				
				ins=new HashMap<>(initSize);
				for(int i=1;i<=count;i++)
				{
					ins.put(rsmd.getColumnLabel(i).toLowerCase(), rs.getString(i));
				}
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
	
	public List<Map<String,String>> queryList(String sql,Object...args)throws Exception
	{
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try
		{
			pstm=DBUtils.prepareStatement(sql);
			
			int index =1;
			for(Object parm:args)
			{
				pstm.setObject(index++, parm);
			}
			
			rs=pstm.executeQuery();
			ResultSetMetaData rsmd=rs.getMetaData();
			
			//计算列数
			int count=rsmd.getColumnCount();
			//计算初始容量
			int initSize=((int)(count/0.75))+1;
			
			//定义List 装载查询结果
			List<Map<String,String>> rows=new ArrayList<>();
			//定义容器变量,装载每行数据
			Map<String,String> ins=null;
			
			while(rs.next())
			{
				
				ins=new HashMap<>(initSize);
				for(int i=1;i<=count;i++)
				{
					ins.put(rsmd.getColumnLabel(i).toLowerCase(), rs.getString(i));
				}
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
	 * 修改多列数据
	 * <p>Title: addMsg</p>  
	 * Description: </p>  
	 * @param sql
	 * @param args
	 * @throws Exception
	 */
	public void addMsg(String sql,Object...args)throws Exception
	{
		PreparedStatement pstm=null;
		try
		{
			pstm=DBUtils.prepareStatement(sql);
			int index=1;
			for(Object parm:args)
			{
				pstm.setObject(index++, parm);
			}
			System.out.println(pstm.executeUpdate());
		}
		finally
		{
			DBUtils.close(pstm);
		}
	}
}
