package com.neusoft.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.services.RiceImpl;


@WebServlet("/RiceEditServlet")
public class RiceEditServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		final RiceImpl services =new RiceImpl();
		try 
		{
			//ֲ��dto
			services.setMapDto(this.createDto(request));
			//
			
				this.execute(services, request);
				
			
		
				
						
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/Riceedit.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doGet(request, response);
	}
	//��һʵ����ѯ
	private final void execute(RiceImpl services,HttpServletRequest request)throws Exception
	{
		Map<String,String> ins=services.findById();
		if(ins!=null)
		{
			System.out.println(ins);
			request.setAttribute("ins", ins);
		}
		else
		{
			request.setAttribute("msg", "��������ɾ�����ֹ����!");
		}	
	}




//��ȡdto
	private final Map<String,Object> createDto(HttpServletRequest request)
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
	
	private final boolean isNotNull(final Object args)
    {
    	return args!=null && !args.equals("");
    }
}