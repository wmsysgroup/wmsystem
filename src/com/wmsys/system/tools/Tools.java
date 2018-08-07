package com.wmsys.system.tools;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.mywq.util.LabelValueBean;

import com.wmsys.system.db.DBUtils;



public class Tools 
{
//	
//	public static void main(String[] args) 
//	  {
//		     
//			System.out.println(listno("C"));
//			System.out.println(listno("C"));
//			System.out.println(listno("C"));
//			System.out.println(listno("C"));
//			System.out.println(listno("C"));
//			System.out.println(listno("C"));
//			
//			
//			System.out.println(listno("D"));
//			System.out.println(listno("D"));
//			System.out.println(listno("D"));
//			System.out.println(listno("D"));
//			System.out.println(listno("D"));
//			System.out.println(listno("D"));
//			
//			
//	  }
	

    //私有化构造函数
	private Tools(){}
	
	
	/**
	 * 生成清单编号
	 * <p>Title: nowi</p>  
	 * Description: </p>  
	 * @param type 清单类型 用{A,B,C,D}
	 * @return
	 */
	public static String listno(String type)
	{
		int me=UUID.randomUUID().toString().replace("-", "").hashCode();
        if(me>0)
        {
        	return type+"1"+me;
        }
        else
        {
        	
        	return type+"2"+Math.abs(me);
        }
	}
	
	/**
	 * 大米编号生成   
	 * <p>Title: mealsno</p>  
	 * Description: </p>  
	 * @return
	 */
	public static String mealsno() 
	{
		PreparedStatement pstm=null;
		ResultSet rs=null;
		int m=0;
		String sql="select COUNT(test) from meals";
		try {
			pstm=DBUtils.prepareStatement(sql);
			rs=pstm.executeQuery();
			if(rs.next())
			{
				m=rs.getInt(1);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		String s="A"+String.format("%04d",m+1);
		
		DBUtils.close(rs);
		DBUtils.close(pstm);
		DBUtils.close();
		return s;
	}
	
	
	//**************************BEGIN MD5*************************************
	
	 public static String getMd5(Object pwd) throws Exception
	 {
		 String m1=Tools.MD5Encode(pwd.toString());
		 String pwd2=m1+"中文字符串ォットハбёюшжуネスぉっへ測試中國"+m1;
		 String m2=Tools.MD5Encode(pwd2);
		 
		 
		 return m2;
	 }
	
	 private final static String[] hexDigits = {
		     "0", "1", "2", "3", "4", "5", "6", "7",
		     "8", "9", "a", "b", "c", "d", "e", "f"};

		  /**
		   * 转换字节数组为16进制字串
		   * @param b 字节数组
		   * @return 16进制字串
		   */
		  private static String byteArrayToHexString(byte[] b)
		  {
		      StringBuffer resultSb = new StringBuffer();
		      for (int i = 0; i < b.length; i++)
		      {
		         resultSb.append(byteToHexString(b[i]));
		      }
		      return resultSb.toString();
		  }
		  /**
		   * 转换字节为16进制字符串
		   * @param b byte
		   * @return String
		   */
		  private static String byteToHexString(byte b)
		  {
		      int n = b;
		      if (n < 0)
		         n = 256 + n;
		      int d1 = n / 16;
		      int d2 = n % 16;
		      return hexDigits[d1] + hexDigits[d2];
		  }
		  /**
		   * 得到MD5的秘文密码
		   * @param origin String
		   * @throws Exception
		   * @return String
		   */
		  public static String MD5Encode(String origin) throws Exception
		  {
		       String resultString = null;
		       try
		       {
		           resultString=new String(origin);
		           MessageDigest md = MessageDigest.getInstance("MD5");
		           resultString=byteArrayToHexString(md.digest(resultString.getBytes()));
		           return resultString;
		       }
		       catch (Exception ex)
		       {
		          throw ex;
		       }
		  }
		//******************END  MD5******************

	
	
	/**
	 * 获取下拉列表
	 * @param fname  ---  字段名
	 * @return
	 * @throws Exception
	 */
	public static List<LabelValueBean> getOptions(final String fname)throws Exception
	{
		//1.定义JDBC接口
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try
		{
			//2.定义SQL语句
			StringBuilder sql=new StringBuilder()
					.append("select a.fvalue,a.fcode")
					.append("  from syscode a")
					.append(" where a.fname=?")
			;
			pstm=DBUtils.prepareStatement(sql.toString());
			pstm.setObject(1, fname);
			rs=pstm.executeQuery();
			
			List<LabelValueBean> opts=new ArrayList<>();
			LabelValueBean bean=null;
			while(rs.next())
			{
				bean=new LabelValueBean(rs.getString(1), rs.getString(2));
				opts.add(bean);
			}
			return opts;
		}
		finally
		{
			DBUtils.close(rs);
			DBUtils.close(pstm);
		}
	}
	
	
	
	/**
	 * 获取主键流水号----无重复名称的数据
	 * @param sname
	 * @return
	 * @throws Exception
	 */
	public static int getSequence(String sname)throws Exception
	{
		//1.定义JDBC接口
		PreparedStatement pstm1=null;
		PreparedStatement pstm2=null;
		ResultSet rs=null;
		try
		{
			
			//定义查询序列当前值的SQL
            String sql1="select svalue from sequence where sname=?";
            //编译SQL
            pstm1=DBUtils.prepareStatement(sql1);
            pstm1.setObject(1, sname);
            //调用查询方法
            rs=pstm1.executeQuery();
            
            //定义变量,表示序列的当前值
            int current_val=0;
            
            StringBuilder sql2=new StringBuilder();
            
            //判断数据库中是否存在名称为sname的序列
            if(rs.next())   //序列已经存在
            {
            	//读取序列当前值
            	current_val=rs.getInt(1);
            	//定义修改序列当前值的SQL语句
            	sql2.append("update sequence ")
            	    .append("   set svalue=?")
            	    .append(" where sname=?")
            	;
            }
            else    //序列尚未存在
            {
            	//定义创建序列的SQL	语句
            	sql2.append("insert into sequence(svalue,sname,sdate)")
            	    .append("             values(?,?,current_date)")
            	;
            }	
            pstm2=DBUtils.prepareStatement(sql2.toString());
            pstm2.setObject(1, ++current_val);
            pstm2.setObject(2, sname);
            pstm2.executeUpdate();
            
            return current_val;
		}
		finally
		{
			DBUtils.close(rs);
			DBUtils.close(pstm1);
			DBUtils.close(pstm2);
		}
	}
	
	
	private final static int MATCH_SCALE=2;         //四舍五入默认小数位数
	/**
	 * 以下四个方法为精度转换方法
	 * @param dol double
	 * @param scale int
	 * @return String
	 */
	public static double ObjToDouble(Object dol, int scale)
	{
		  return Tools.ObjectToBigDecimal(dol, scale).doubleValue();
	}
	public static double ObjToDouble(Object dol)
	{
	   return Tools.ObjToDouble(dol, MATCH_SCALE);	
	}
	
	public static String DoubleToStr(double dol, int scale)
	{
	    return Tools.ObjectToBigDecimal(dol, scale).toString();
	}
	public static String DoubleToStr(double dol)
	{
	    return Tools.DoubleToStr(dol, MATCH_SCALE);
	}

	public static double DoubleToDouble(double dol, int scale)
	{
	    return Tools.ObjectToBigDecimal(dol, scale).doubleValue();
	}
	public static double DoubleToDouble(double dol)
	{
	    return Tools.DoubleToDouble(dol,  MATCH_SCALE);
	}

	public static double StrToDouble(String dol, int scale)
	{
	    return Tools.ObjectToBigDecimal(dol, scale).doubleValue();
	}
	public static double StrToDouble(String dol)
	{
	    return Tools.StrToDouble(dol, MATCH_SCALE);
	}
	public static String StrToStr(String dol, int scale)
	{
	   return Tools.ObjectToBigDecimal(dol, scale).toString();
	}
	public static String StrToStr(String dol)
	{
	  return Tools.StrToStr(dol,MATCH_SCALE);
	}
	
	private static BigDecimal ObjectToBigDecimal(Object dol,int scale)
	{
		BigDecimal decimal=null;
		if(dol==null || dol.equals(""))
		{
			return new BigDecimal(0);
		}
		decimal = new BigDecimal(dol.toString());
		decimal = decimal.setScale(scale, BigDecimal.ROUND_HALF_UP);
		return decimal;
	}
}