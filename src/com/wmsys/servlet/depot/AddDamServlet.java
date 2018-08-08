package com.wmsys.servlet.depot;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.tools.Tool;

import com.wmsys.services.depot.impl.InBoundServImpl;
import com.wmsys.system.db.DBUtils;
import com.wmsys.system.tools.Tools;


/**
 * Servlet implementation class AddDamServlet
 */
@WebServlet("/AddDamServlet")
public class AddDamServlet extends HttpServlet 
{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//��ȡ���е�����ֵ���������ݴ洢����dto��
		Map<String, String[]> tem = request.getParameterMap();
		Set<Entry<String,String[]>> entrySet = tem.entrySet();
		String val[] = null;
		Map<String, Object> dto = new HashMap<>();
				
		for(Entry<String,String[]> entry:entrySet) 
		{
			val = entry.getValue();
			dto.put(entry.getKey(),val[0]);
		}		
		dto.put("gdid", Tools.listno("C"));
		dto.put("gdprincipal", "120");
		
		InBoundServImpl ibs=new InBoundServImpl();
		ibs.setMapDto(dto);
		
		try {
			//�������嵥
			ibs.insertdam();
			String s=(String)dto.get("gdquantity");

			//��ȡ��ǰ�嵥�Ļ�������
			BigDecimal count1=new BigDecimal((String)dto.get("gdquantity"));
			//��ȡ��������
			BigDecimal count=ibs.findnum();
	
			//��������
			BigDecimal rs=count.subtract(count1);
			
			dto.put("number", rs);
			//�޸Ŀ�������
			ibs.updatema();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		request.getRequestDispatcher("/destr.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
