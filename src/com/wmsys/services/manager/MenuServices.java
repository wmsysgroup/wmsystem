package com.wmsys.services.manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mywq.util.LabelValueBean;

import com.wmsys.system.db.DBUtils;

public class MenuServices {
	public Map<String,List<LabelValueBean>> getMenuList()throws Exception
	{
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try
		{
			//1.∂®“ÂSQL”Ôæ‰
			StringBuilder sql=new StringBuilder()
					.append("select s.sysname,s.sysvalue,s.syscode")
					.append("  from syscode s")
					.append("  order by s.sysname");
			
			pstm=DBUtils.prepareStatement(sql.toString());
			rs=pstm.executeQuery();
			
			Map<String,List<LabelValueBean>> allList=new HashMap<>();
			List<LabelValueBean> opts= null;
			LabelValueBean bean=null;
			String ftype=null;
			if(rs.next()) 
			{
				ftype = rs.getString(1);
				opts=new ArrayList<>();
				bean=new LabelValueBean(rs.getString(2), rs.getString(3));
				opts.add(bean);
				
				String ntype = null;
				while(rs.next()) 
				{
					ntype = rs.getString(1);
					if(ntype.equals(ftype)) 
					{
						bean=new LabelValueBean(rs.getString(2), rs.getString(3));
						opts.add(bean);
					}
					else 
					{
						allList.put(ftype, opts);
						
						ftype = ntype;
						
						opts=new ArrayList<>();
						
						bean=new LabelValueBean(rs.getString(2), rs.getString(3));
						opts.add(bean);
						
					}
				}
				allList.put(ftype, opts);
			}
			return allList;
		}
		finally 
		{
			DBUtils.close(rs);
			DBUtils.close(pstm);
		}
	}
}
