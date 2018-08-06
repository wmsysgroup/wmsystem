package com.wmsys.services.manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wmsys.system.db.DBUtils;
import com.wmsys.system.tools.Tools;

public class SalesInquireServices {
		//定义数据传输对象
		private Map<String,Object> dto = null;
		
		//接受dto
		public void setDto(Map<String, Object> dto) {
			this.dto = dto;
		} 
		public void showDto() 
		{
			System.out.println(this.dto);
		}
		//获取dto中的值
		private final Object get(String key) 
		{
			return this.dto.get(key);
		}
		
		public final List<Map<String,String>> salesInquire()throws Exception
		{
			List<Object> paramList = new ArrayList<>();
			PreparedStatement pstm = null;
			ResultSet rs=null;
			//定义sql
			StringBuilder sql = new StringBuilder()
					.append("select sid,outid,snumber,sprice,squantity,spurchaser,sdate,sprincipal,remarks")
					.append(" from salesList")
					.append(" where 1=1");
			if(Tools.isNotNull(this.get("sid"))) 
			{
				sql.append(" and sid=?");
				paramList.add(this.get("sid"));
			}
			if(Tools.isNotNull(this.get("outid"))) 
			{
				sql.append(" and outid=?");
				paramList.add(this.get("outid"));
			}
			if(Tools.isNotNull(this.get("snumber"))) 
			{
				sql.append(" and snumber=?");
				paramList.add(this.get("snumber"));
			}
			if(Tools.isNotNull(this.get("sprice"))) 
			{
				sql.append(" and sprice=?");
				paramList.add(this.get("sprice"));
			}
			
			if(Tools.isNotNull(this.get("spurchaser"))) 
			{
				sql.append(" and spurchaser=?");
				paramList.add(this.get("spurchaser"));
			}
			if(Tools.isNotNull(this.get("bdate"))) 
			{
				sql.append(" and sdate>?");
				paramList.add(this.get("bdate"));
			}
			if(Tools.isNotNull(this.get("edate"))) 
			{
				sql.append(" and sdate<?");
				paramList.add(this.get("edate"));
			}
			if(Tools.isNotNull(this.get("sprincipal"))) 
			{
				sql.append(" and sprincipal=?");
				paramList.add(this.get("sprincipal"));
			}
		
			
			
			try 
			{
				pstm = DBUtils.prepareStatement(sql.toString());
			
				int index = 1;
				for(Object param:paramList) 
				{
					pstm.setObject(index++, param);
				}
				rs=pstm.executeQuery();
				
				ResultSetMetaData rsmd=rs.getMetaData();
				
				int count=rsmd.getColumnCount();
				
				int initSize=((int)(count/0.75))+1;
				
				List<Map<String,String>> rows=new ArrayList<>();
				
				Map<String,String> ins=null;
				
				while(rs.next())
				{
					//实例化装载当前行数据的HashMap
					ins=new HashMap<>(initSize);
					//循环读取每列数据
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
}
