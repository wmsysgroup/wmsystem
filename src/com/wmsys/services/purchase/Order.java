package com.wmsys.services.purchase;

import java.sql.PreparedStatement;
import java.util.Map;


import com.wmsys.system.db.DBUtils;
import com.wmsys.system.tools.Tools;

public class Order
{
    //定义数据传输对象
    private Map<String,Object> dto = null;

    //接受dto
    public void setDto(Map<String, Object> dto) {
        this.dto = dto;
    }
    public void showDto()
    {
        System.out.println(this.dto);
    }
    //获取dto中的值
    private final Object get(String key)
    {
        return this.dto.get(key);
    }

    public final boolean order()throws Exception
    {
        PreparedStatement pstm = null;
        //定义sql
        StringBuilder sql = new StringBuilder()
                .append("insert into purchaseList(plid,plnumber,plquantity,pldate,plsupplier,plprincipal,plstate,remarks)")
                .append(" values (?,?,?,?,?,?,?,?)");
        //数据整合
        Object args[]={
                Tools.listno("E"),this.get("plnumber"),this.get("plquantity"),this.get("pldate"),
                this.get("plsupplier"),this.get("plprincipal"),this.get("plstate"),this.get("remarks")};
        try
        {
            pstm = DBUtils.prepareStatement(sql.toString());

            int index = 1;
            for(Object param:args)
            {
                pstm.setObject(index++, param);
            }
            return pstm.executeUpdate()>0;
        }
        finally
        {
            DBUtils.close(pstm);
        }
    }

}