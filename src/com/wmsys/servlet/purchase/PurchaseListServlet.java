package com.wmsys.servlet.purchase;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mywq.util.LabelValueBean;

import com.wmsys.services.purchase.PurchaseList;
import com.wmsys.system.tools.Tools;

@WebServlet("/PurchaseListServlet")
public class PurchaseListServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        final PurchaseList services = new PurchaseList();
        Map<String, String[]> tem = request.getParameterMap();
        Set<Entry<String,String[]>> entrySet = tem.entrySet();
        String val[] = null;
        Map<String, Object> dto = new HashMap<>();

        for(Entry<String,String[]> entry:entrySet)
        {
            val = entry.getValue();
            dto.put(entry.getKey(),val[0]);
        }
        services.setDto(dto);

        try
        {

            List<Map<String,String>> rows = services.plist();
            System.out.println(rows);
            if(rows.size()==0)
            {
                String msg = "没有符合条件的数据";
                request.setAttribute("msg", msg);
            }
            request.setAttribute("rows", rows);
        }
        catch (Exception e)
        {
            String msg = "网络故障";
            request.setAttribute("，msg", msg);
            e.printStackTrace();
        }

        request.getRequestDispatcher("/PurchaseList.jsp").forward(request, response);

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

}
