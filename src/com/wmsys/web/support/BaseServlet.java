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
 		//1.��ȡ���еļ�ֵ�Լ���(EntrySet)
 		Set<Entry<String,String[]>> entrySet=tem.entrySet();
 		//�����ַ�����ֵ��ʾEntry��value
 		String value[]=null;
 		//ʵ����DTO
 		Map<String,Object> dto=new HashMap<>();
 		//ѭ����ȡÿ����ֵ��
 		for(Entry<String,String[]>  entry:entrySet)
 		{
 			//��ȡ��ֵ�Ե�value����
 			value=entry.getValue();
 			//�ж�value����ĳ���,�����1,��ʾ�ÿؼ��Ƿ�checkbox,������ȴ���1,��ʾcheckbox
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
