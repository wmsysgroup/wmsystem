package com.wmsys.servlet.purchase;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mywq.util.LabelValueBean;

import com.wmsys.services.purchase.Order;
import com.wmsys.system.tools.Tools;


@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final Order services = new Order();
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
            String msg = services.order()?"添加成功":"网络故障";
            request.setAttribute("msg", msg);
        }
        catch(Exception e)
        {
            String msg = "添加失败，您输入的数据有误！";
            request.setAttribute("msg", msg);
            e.printStackTrace();
        }
        request.getRequestDispatcher("/Order.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
