package com.wmsys.system.db;
//���ݿ����Ӷ���
import java.sql.Connection;
//����������
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//��Դ�ļ�������
import java.util.ResourceBundle;
//�ֲ߳̾�����--�̸߳���
import java.lang.ThreadLocal;
import com.wmsys.system.tools.*;

public class DBUtils 
{
	//1.����������----��������jar����,�������ݿ����õĺ����������·��
	private static String driver=null;
	//2.�������Ӵ�--�������ݿ�������ʶ,�Լ����ݿ�����,�����ݿ�ʱ�����õı����ʽ��
	private static String url=null;
	private static String username=null;
	private static String password=null;
	
	//ʵ�����̸߳�������,װ��Connection
	/**
	 * ����java�еĳ���:
	 * �Ƕ��󲻿ɱ�
	 * �������ò��ɱ�
	 */
	private static final ThreadLocal<Connection>  threadLocal=new ThreadLocal<>();
	

  private DBUtils() {}

  
  //��̬��:���౻��һ�μ������ڴ�ʱ��,ִ��,�Ժ���ִ��,���Ҿ�̬�鴴���Ķ���,��פ�ڴ治�ͷ�,ֱ���������
  static
  {
  	//3.��������
  	try 
  	{
  		//��ȡ��Դ�ļ�����������
  		ResourceBundle bundle=ResourceBundle.getBundle("jdbc");
  		//����Դ�ļ���ȡ����:����ȡֵ
  		driver=bundle.getString("DRIVER");
  		url=bundle.getString("URL");
  		username=bundle.getString("USERNAME");
  		password=bundle.getString("PASSWORD");
  		
  		
			Class.forName(driver);
		}
  	catch (ClassNotFoundException e) 
  	{
			e.printStackTrace();
		}   
  }
  
  /**
   * ��ȡ���ݿ�����
   * @return
   * @throws Exception
   */
  private static Connection getConnection()throws Exception
  {
  	//1.��threadLocal��ȡΪ��ǰ�̰߳󶨵����Ӷ���
  	Connection conn=threadLocal.get();
  	//2.�ж������Ƿ����
  	if(conn==null)
  	{
  		//����Connection
  		conn=DriverManager.getConnection(url, username,password);
  		//�����Ӷ����뵱ǰ�߳̽��а�
  		threadLocal.set(conn);
  	}
      //5.��������
      return conn;
  }
  
  /*******************************************************************
   *          ����Ϊ������ط���
   ******************************************************************/
  
  /**
   * ��������
   * @throws Exception
   */
  public static void beginTransaction()throws Exception 
  {
  	DBUtils.getConnection().setAutoCommit(false);
  }
  /**
   * �����ύ
   * @throws Exception
   */
  public static void commit()throws Exception
  {
  	//��ʽ����
  	DBUtils.getConnection().commit();
  }
  
  /**
   * ����ع�
   */
  public static void rollback()
  {
  	try 
  	{
			DBUtils.getConnection().rollback();
		} 
  	catch (Exception e) 
  	{
  		try
  		{
  			DBUtils.getConnection().rollback();
  		}
  		catch(Exception ex1)
  		{
  			ex1.printStackTrace();
  		}
			e.printStackTrace();
		}
  }
  /**
   * ��������
   */
  public static void endTransaction()
  {
  	try
  	{
  		DBUtils.getConnection().setAutoCommit(true);
  	}
  	catch(Exception ex)
  	{
  		ex.printStackTrace();
  	}
  }

  
  /*******************************************************************
   *          ����ΪSQL����ģ��
   ******************************************************************/
  public static PreparedStatement prepareStatement(final String sql)throws Exception
  {
  	return DBUtils.prepareStatement(sql, false);
  }

  public static PreparedStatement prepareStatement(final String sql,boolean returnKey)throws Exception
  {
  	if(returnKey)
  	{
  		return DBUtils.getConnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
  	}
  	else
  	{
  		return DBUtils.getConnection().prepareStatement(sql);
  	}	
  }

  
  /*******************************************************************
   *          ����Ϊ��Դ����ģ�� 
   ******************************************************************/
  
  public static void close(ResultSet rs)
  {
  	try
  	{
  		if(rs!=null)  //����˿�ָ��
  		{
  			rs.close();
  		}
  	}
  	catch(Exception ex)
  	{
  		ex.printStackTrace();
  	}
  }
  
  public static void close(PreparedStatement pstm)
  {
  	try
  	{
  		if(pstm!=null)  //����˿�ָ��
  		{
  			pstm.close();
  		}
  	}
  	catch(Exception ex)
  	{
  		ex.printStackTrace();
  	}
  }
  
  public static void close()
  {
  	try
  	{
  		//��ȡ��ǰ�̰߳󶨵����Ӷ���
  		Connection conn=threadLocal.get();
  		if(conn!=null)  //����˿�ָ��
  		{
  			System.out.println("------ Close Connection ---------------------------");
  			//�����
  			threadLocal.set(null);
  			
  			conn.close();
  		}
  	}
  	catch(Exception ex)
  	{
  		ex.printStackTrace();
  	}
  }
  
  
  
  public static void main(String[] args) 
  {
	     try 
	     {
			
				System.out.println(DBUtils.getConnection());
			
		 } 
	     catch (Exception e) 
	     {
			e.printStackTrace();
		}	
	}
  
  
}
