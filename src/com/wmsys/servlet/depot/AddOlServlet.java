package com.wmsys.servlet.depot;

import java.awt.image.DataBufferUShort;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wmsys.services.depot.impl.InBoundServImpl;
import com.wmsys.services.depot.support.OutList;
import com.wmsys.system.db.DBUtils;
import com.wmsys.system.tools.Tools;

/**
 * Servlet implementation class AddOlServlet
 */
@WebServlet("/AddOlServlet")
public class AddOlServlet extends HttpServlet 
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String[] args=request.getParameterValues("idlist");
		BigDecimal numb=new BigDecimal(request.getParameter("count"));
		InBoundServImpl ibs=new InBoundServImpl();
		Map<String,Object> dto=new HashMap<String, Object>();
		//获取用户ID
		HttpSession session = request.getSession(); 
		Map<String,String> user=(Map<String, String>) session.getAttribute("USERINFO");
		
		
		//定义装载数据对象
		ArrayList<OutList> all=new ArrayList<>();
		
		try {
			//获取
			for(String s:args){
				OutList ol=new OutList();
				dto.put("indid", s);
				dto.put("inid", s);
				ibs.setMapDto(dto);
				ol.setId(s);
				ol.setNumber(ibs.findnum());
				ol.setDate(ibs.finddate());
				all.add(ol);
			}
			
			//排序 按时间由早到晚
			Comparator<OutList> comparator = new Comparator<OutList>() {
	            @Override
	            public int compare(OutList ol1,OutList ol2) {
	                /*按员工编号正序排序*/
	                return ol1.getDate().compareTo(ol2.getDate());
	            }
	            
	        };
	        Collections.sort((List)all,comparator);
			
	        BigDecimal zero=new BigDecimal("0");
	        int i=0;
	        
	        //定义完全出货数组 alm （装载id） 部分出货数组 litt(装载id--0以及剩余容量--2和出货容量--1)
	        ArrayList<Object> alm=new ArrayList<>();
	        ArrayList<Object> litt=new ArrayList<>();
	        while (numb.compareTo(zero)>0)
	        {
	        	if(numb.compareTo(all.get(i).getNumber())>=0)
	        	{
	        		
	        		alm.add(all.get(i).getId());
	 
	        	}
	        	else
	        	{
	        		litt.add(all.get(i).getId());
	        		litt.add(numb);
	        		litt.add(all.get(i).getNumber().subtract(numb));
	        	}
	        	numb=numb.subtract(all.get(i).getNumber());
	        	i++;
	        }
	        
	        //完全出库清单添加
	        for(int m=0;m<alm.size();m++)
	        {
	        	dto.put("indid",alm.get(m));
	        	ibs.setMapDto(dto);
	        	dto.put("outquantity", ibs.findnum());
	        	dto.put("outdate", Tools.getDate());
	        	dto.put("outid", Tools.listno("F"));
	        	dto.put("outnumber", request.getParameter("outnumber"));
	        	dto.put("outprincipal", user.get("uaccount"));
	        	ibs.setMapDto(dto);
	        	ibs.insertout();
	        	ibs.deleteMl();
	        }
	        
	        //部分出库清单添加
	        if(litt.size()!=0){
		        dto.put("indid",litt.get(0));
	        	ibs.setMapDto(dto);
	        	dto.put("outquantity", litt.get(1));
	        	dto.put("outdate", Tools.getDate());
	        	dto.put("outid", Tools.listno("E"));
	        	dto.put("outnumber", request.getParameter("outnumber"));
	        	dto.put("outprincipal", "zyl");
	        	dto.put("number", litt.get(2));
	        	ibs.setMapDto(dto);
	        	ibs.insertout();
	        	ibs.updatema();
	        }
	        
	        
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("msg", "出库成功");
		request.getRequestDispatcher("/out.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
