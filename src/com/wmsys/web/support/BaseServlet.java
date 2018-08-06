package com.wmsys.web.support;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;


public class BaseServlet extends HttpServlet 
{
     public final Map<String,Object> createDto(HttpServletRequest request)
     {
 		Map<String,String[]> tem=request.getParameterMap();
 		//1.获取所有的键值对集合(EntrySet)
 		Set<Entry<String,String[]>> entrySet=tem.entrySet();
 		//定义字符串数值表示Entry的value
 		String value[]=null;
 		//实例化DTO
 		Map<String,Object> dto=new HashMap<>();
 		//循环获取每个键值对
 		for(Entry<String,String[]>  entry:entrySet)
 		{
 			//获取键值对的value部分
 			value=entry.getValue();
 			//判断value数组的长度,如果是1,表示该控件是非checkbox,如果长度大于1,表示checkbox
 			if(value.length==1)
 			{
 				dto.put(entry.getKey(), value[0]);
 			}
 			else
 			{
 				dto.put(entry.getKey(), value);
 			}	
 		 }
 		 return dto;
     }
}
