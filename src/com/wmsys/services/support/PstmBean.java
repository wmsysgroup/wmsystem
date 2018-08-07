
/**
 * 语句对象描述Bean
 */
package com.wmsys.services.support;

import java.io.Serializable;
import java.sql.PreparedStatement;

import com.wmsys.system.db.DBUtils;


final class PstmBean implements Serializable
{
	//程序中需要执行的SQL语句
	private PreparedStatement pstm=null;
	//该SQL语句的执行方式,isBatch--true -->executeBatch(); false--->executUpdate();
	private boolean isBatch=false;
	
	public PstmBean(PreparedStatement pstm,boolean isBatch) 
	{
		this.pstm=pstm;
		this.isBatch=isBatch;
	}
	
	public void execute()throws Exception
	{
		if(this.isBatch)
		{
			this.pstm.executeBatch();
		}
		else
		{
			this.pstm.executeUpdate();
		}	
	}
	
	public void close()
	{
		DBUtils.close(pstm);
	}

}
