package com.wmsys.system.db;
//数据库连接对象
import java.sql.Connection;
//驱动管理器
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//资源文件解析器
import java.util.ResourceBundle;
//线程局部变量--线程副本
import java.lang.ThreadLocal;
import com.wmsys.system.tools.*;

public class DBUtils 
{
	//1.定义驱动串----描述驱动jar包中,连接数据库所用的核心类的完整路径
	private static String driver=null;
	//2.定义连接串--描述数据库的网络标识,以及数据库名称,打开数据库时候所用的编码格式等
	private static String url=null;
	private static String username=null;
	private static String password=null;
	
	//实例化线程副本容器,装载Connection
	/**
	 * 对于java中的常量:
	 * 是对象不可变
	 * 还是引用不可变
	 */
	private static final ThreadLocal<Connection>  threadLocal=new ThreadLocal<>();
	

  private DBUtils() {}

  
  //静态块:在类被第一次加载入内存时候,执行,以后不再执行,而且静态块创建的对象,常驻内存不释放,直到程序结束
  static
  {
  	//3.加载驱动
  	try 
  	{
  		//获取资源文件解析器对象
  		ResourceBundle bundle=ResourceBundle.getBundle("jdbc");
  		//从资源文件读取数据:按名取值
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
   * 获取数据库连接
   * @return
   * @throws Exception
   */
  private static Connection getConnection()throws Exception
  {
  	//1.到threadLocal获取为当前线程绑定的连接对象
  	Connection conn=threadLocal.get();
  	//2.判断连接是否存在
  	if(conn==null)
  	{
  		//创建Connection
  		conn=DriverManager.getConnection(url, username,password);
  		//将连接对象与当前线程进行绑定
  		threadLocal.set(conn);
  	}
      //5.返回连接
      return conn;
  }
  
  /*******************************************************************
   *          以下为事务相关方法
   ******************************************************************/
  
  /**
   * 开启事务
   * @throws Exception
   */
  public static void beginTransaction()throws Exception 
  {
  	DBUtils.getConnection().setAutoCommit(false);
  }
  /**
   * 事务提交
   * @throws Exception
   */
  public static void commit()throws Exception
  {
  	//链式调用
  	DBUtils.getConnection().commit();
  }
  
  /**
   * 事务回滚
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
   * 结束事务
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
   *          以下为SQL编译模块
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
   *          以下为资源销毁模块 
   ******************************************************************/
  
  public static void close(ResultSet rs)
  {
  	try
  	{
  		if(rs!=null)  //规避了空指针
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
  		if(pstm!=null)  //规避了空指针
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
  		//获取当前线程绑定的连接对象
  		Connection conn=threadLocal.get();
  		if(conn!=null)  //规避了空指针
  		{
  			System.out.println("------ Close Connection ---------------------------");
  			//解除绑定
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
