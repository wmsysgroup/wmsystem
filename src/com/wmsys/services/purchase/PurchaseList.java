package com.wmsys.services.purchase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wmsys.system.db.DBUtils;
import com.wmsys.system.tools.Tools;

public class PurchaseList {
    private Map<String,Object> dto = null;

    public void setDto(Map<String, Object> dto) {
        this.dto = dto;
    }
    public void showDto()
    {
        System.out.println(this.dto);
    }
    private final Object get(String key)
    {
        return this.dto.get(key);
    }
    public final List<Map<String,String>> plist()throws Exception
    {
        List<Object> paramList = new ArrayList<>();
        PreparedStatement pstm = null;
        ResultSet rs=null;
        //定义sql
        StringBuilder sql = new StringBuilder()
                .append("select bgname,bglevel,bgtype,bgchandi,bgexp,bgnumber,bgprice")
                .append(" from basicGoods")
                .append(" where 1=1");
        if(Tools.isNotNull(this.get("bgname")))
        {
            sql.append(" and bgname like ?");
            paramList.add("%"+this.get("bgname")+"%");
        }

        if(Tools.isNotNull(this.get("bglevel")))
        {
            sql.append(" and bglevel=?");
            paramList.add(this.get("bglevel"));
        }

        if(Tools.isNotNull(this.get("bgtype")))
        {
            sql.append(" and bgtype=?");
            paramList.add(this.get("bgtype"));
        }

        if(Tools.isNotNull(this.get("bgchandi")))
        {
            sql.append(" and bgchandi like ?");
            paramList.add("%"+this.get("bgchandi")+"%");
        }



        try
        {
            pstm = DBUtils.prepareStatement(sql.toString());

            int index = 1;
            for(Object param:paramList)
            {
                pstm.setObject(index++, param);
            }
            rs=pstm.executeQuery();

            ResultSetMetaData rsmd=rs.getMetaData();

            int count=rsmd.getColumnCount();

            int initSize=((int)(count/0.75))+1;

            List<Map<String,String>> rows=new ArrayList<>();

            Map<String,String> ins=null;

            while(rs.next())
            {
                //实例化装载当前行数据的HashMap
                ins=new HashMap<>(initSize);
                //循环读取每列数据
                for(int i=1;i<=count;i++)
                {
                    ins.put(rsmd.getColumnLabel(i).toLowerCase(), rs.getString(i));
                }
                rows.add(ins);
            }
            return rows;
        }
        finally
        {
            DBUtils.close(rs);
            DBUtils.close(pstm);
        }
    }
}
