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

public class DepotInquireServices {

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
						.append("select b.bgname,i.innumber,s4.sysvalue wnumber,s1.sysvalue bglevel,s2.sysvalue bgtype,s3.sysvalue bgspec,m.number,i.indate,i.inprincipal,i.remarks")
						.append(" from basicGoods b,InboundList i,margin m,syscode s1,syscode s2,syscode s3,syscode s4")
						.append(" where i.innumber = b.bgnumber and i.inid=m.indid")
						.append(" and b.bglevel=s1.syscode and b.bgtype =s2.syscode and b.bgspec = s3.syscode and i.wnumber = s4.syscode")
						.append(" and s1.sysname = 'bglevel' and s2.sysname = 'bgtype' and s3.sysname = 'bgspec' and s4.sysname = 'wnumber'");
				if(Tools.isNotNull(this.get("bgname"))) 
				{
					sql.append(" and b.bgname like ?");
					paramList.add("%"+this.get("bgname")+"%");
				}
				if(Tools.isNotNull(this.get("innumber"))) 
				{
					sql.append(" and i.innumber=?");
					paramList.add(this.get("innumber"));
				}
				if(Tools.isNotNull(this.get("wnumber"))) 
				{
					sql.append(" and i.wnumber=?");
					paramList.add(this.get("wnumber"));
				}
				if(Tools.isNotNull(this.get("bglevel"))) 
				{
					sql.append(" and b.bglevel=?");
					paramList.add(this.get("bglevel"));
				}
				
				if(Tools.isNotNull(this.get("bgtype"))) 
				{
					sql.append(" and b.bgtype=?");
					paramList.add(this.get("bgtype"));
				}
				
				if(Tools.isNotNull(this.get("bgspec"))) 
				{
					sql.append(" and b.bgspec=?");
					paramList.add(this.get("bgspec"));
				}
				if(Tools.isNotNull(this.get("bdate"))) 
				{
					sql.append(" and i.indate>?");
					paramList.add(this.get("bdate"));
				}
				if(Tools.isNotNull(this.get("edate"))) 
				{
					sql.append(" and i.indate<?");
					paramList.add(this.get("edate"));
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
