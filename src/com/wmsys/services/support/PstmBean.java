
/**
 * ����������Bean
 */
package com.wmsys.services.support;

import java.io.Serializable;
import java.sql.PreparedStatement;

import com.wmsys.system.db.DBUtils;


final class PstmBean implements Serializable
{
	//��������Ҫִ�е�SQL���
	private PreparedStatement pstm=null;
	//��SQL����ִ�з�ʽ,isBatch--true -->executeBatch(); false--->executUpdate();
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
