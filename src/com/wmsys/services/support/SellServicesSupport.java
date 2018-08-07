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
	 *         ���ݴ��������ط���
	 **********************************************************/
	private Map<String,Object>  dto=null;
	public SellServicesSupport(Map<String,Object> dto) 
	{
		this.dto=dto;
	}
	
	/**
	 * ��ȡDTO�е�����
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
	 * ��ָ�������ݻ�ԭ������
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
	*    ��������
	***************************************************/
	/**
	 * ��ֵУ��
	 * @param args
	 * @return
	 */
    protected final boolean isNotNull(final Object args)
    {
    	return args!=null && !args.equals("");
    }
	
	
   /***************************************************
	*    ����Ϊ��ѯ��װ����
	***************************************************/
	
	protected final List<Map<String,String>> queryForList(final String sql,final Object...args)throws 	Exception
	{
		//1.����JDBC�ӿ�
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
			//��ȡ�������������
			ResultSetMetaData rsmd=rs.getMetaData();
			//��������
			int count=rsmd.getColumnCount();
			//�����ʼ����
			int initSize=((int)(count/0.75))+1;
			
			
			//����List װ�ز�ѯ���
			List<Map<String,String>> rows=new ArrayList<>();
			//������������,װ��ÿ������
			Map<String,String> ins=null;
			
			//ѭ������rs
			while(rs.next())
			{
				//ʵ����װ�ص�ǰ�����ݵ�HashMap
				ins=new HashMap<>(initSize);
				//ѭ����ȡÿ������
				for(int i=1;i<=count;i++)
				{
					ins.put(rsmd.getColumnLabel(i).toLowerCase(), rs.getString(i));
				}
				//����ǰ�з���List
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
	 * ��һʵ����ѯ
	 * @param sql
	 * @param args
	 * @return
	 * @throws Exception
	 */
	protected final Map<String,String> queryForMap(final String sql,final Object...args)throws Exception
	{
		//1.����JDBC�ӿ�
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
    *    ��һ�������������װ
    ***************************************************/
	/**
	 * 
	 * @param sql
	 *    update person
	 *       set pname=?,pnumber=?,psex=?,psr=?
	 *     where pid=?
	 * @param newStateTable[][]={
	 *              {"���˸�","110","1","1979-12-26"},
	 *              {"����","110","1","1979-12-26"},
	 *              {"������","110","1","1979-12-26"} 
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
			//1.��״̬�б����ѭ��,��ȡ��һ�е�����
			for(Object[] newStateList:newStateTable)
			{
				//����index,��ʾsql��?������
				int index=1;
				//���set�б�ĸ�ֵ
				for(Object newState:newStateList)
				{
					pstm.setObject(index++, newState);
				}
				//���������ĸ�ֵ
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
	 *              {"���˸�","110","1","1979-12-26","17"},
	 *              {"����","110","1","1979-12-26","18"},
	 *              {"������","110","1","1979-12-26","19"} 
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
			//1.��״̬�б����ѭ��,��ȡ��һ�е�����
			for(Object[] newStateList:newStateTable)
			{
				//����index,��ʾsql��?������
				int index=1;
				//���ÿ�����ݵĸ�ֵ
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
	 * ��״̬��������
	 * <
	 *   update table 
	 *      set col1=?,col2=?,col3=?..........colN=?
	 *    where id=? 
	 *    
	 *   �ٸ�����:
	 *   update person set pstate=?,psal=psal+? where pid=?
	 *   newStateList[]={"2","500"}
	 *   idlist[]={"24","25","26"}; 
	 * >
	 * @param sql
	 * @param newStateList   ----  ״̬�б�
	 * @param idlist         ----  �����б� 
	 * @return
	 * @throws Exception
	 */
	protected final boolean batchUpdate(final String sql,Object[] newStateList,final Object...idlist)throws Exception
	{
		PreparedStatement pstm=null;
		try
		{
			pstm=DBUtils.prepareStatement(sql);
			//��������
			int index=1;
			//���set�б�ĸ�ֵ
			for(Object newState:newStateList)  //newState=500
			{
				pstm.setObject(index++, newState);  //index=3
			}
			//���������ֵ
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
	 * ��һ״̬����������
	 * <
	 *   �ʺϵ����
	 *   update table 
	 *      set col=?
	 *    where pid=?
	 *   �ٸ�����:Ա����������  
	 * >
	 * @param sql
	 * @param newState   --- ѡ����Ա,ָ�����ݵ���״̬
	 * @param idlist     --- �����б�
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
	 * ��һ��������
	 * <
	 *   �ʺϵ��﷨��ʽ:
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
    *    ����������������
    ***************************************************/
	
	//�������,װ������ִ���ڼ������������
	private final List<PstmBean> pstmList=new ArrayList<>();
	
	protected final void appendBatchSql(final String sql,Object[][] newStateTable,final Object...idlist)throws Exception
	{
		PreparedStatement pstm=DBUtils.prepareStatement(sql);
		int id_index=0;
		//1.��״̬�б����ѭ��,��ȡ��һ�е�����
		for(Object[] newStateList:newStateTable)
		{
			//����index,��ʾsql��?������
			int index=1;
			//���set�б�ĸ�ֵ
			for(Object newState:newStateList)
			{
				pstm.setObject(index++, newState);
			}
			//���������ĸ�ֵ
			pstm.setObject(index, idlist[id_index++]);
			pstm.addBatch();
		}
		PstmBean bean=new PstmBean(pstm, true);
		this.pstmList.add(bean);
	}
	
	protected final void appendBatchSql(final String sql,Object[][] newStateTable)throws Exception
	{
		PreparedStatement pstm=DBUtils.prepareStatement(sql);
		//1.��״̬�б����ѭ��,��ȡ��һ�е�����
		for(Object[] newStateList:newStateTable)
		{
			//����index,��ʾsql��?������
			int index=1;
			//���ÿ�����ݵĸ�ֵ
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

		//��������
		int index=1;
		//���set�б�ĸ�ֵ
		for(Object newState:newStateList)  //newState=500
		{
			pstm.setObject(index++, newState);  //index=3
		}
		//���������ֵ
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
	 * Ϊ����ע���������SQL���
	 * @param sql
	 * @param args
	 * @throws Exception
	 */
	protected final void appendSql(final String sql,final Object...args)throws Exception
	{
		//1.����SQL���
		PreparedStatement pstm=DBUtils.prepareStatement(sql);
		//2.��ɲ�����ֵ
		int index=1;
		for(Object param:args)
		{
			pstm.setObject(index++, param);
		}
		//���ڸ������󹹽���������
		PstmBean bean=new PstmBean(pstm, false);
		//�������б�
		this.pstmList.add(bean);
	}
	
	/**
	 * ��������������ִ�з���
	 * @return
	 * @throws Exception
	 */
	protected final boolean executeTransaction()throws Exception
	{
		//1.�������񷵻�ֵ
		boolean tag=false;
		//2.��������
		DBUtils.beginTransaction();
		try
		{
			//ִ�������е�����SQL���
			for(PstmBean bean:this.pstmList)
			{
				//ִ�����
				bean.execute();
			}
			//�ύ����
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
			//1.���������е�����������
			for(PstmBean bean:this.pstmList)
			{
				bean.close();
			}
			//����������б�
			this.pstmList.clear();
		}
		return tag;
	}
	
   /***************************************************
    *   ��һSQL��������·���
    ***************************************************/
	/**
	 * ��һ������
	 * @param sql   --- ��Ҫִ�е�SQL���
	 * @param args  --- ����ֵ�б�
	 * @throws Exception
	 */
	protected final int executeUpdate(final String sql,final Object...args)throws Exception
	{
		//1.����JDBC����
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








