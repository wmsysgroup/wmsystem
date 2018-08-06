package com.wmsys.services.manager;

import java.sql.PreparedStatement;
import java.util.Map;


import com.wmsys.system.db.DBUtils;
import com.wmsys.system.tools.Tools;

public class AddRiceServices 
{
	//�������ݴ������
	private Map<String,Object> dto = null;
	
	//����dto
	public void setDto(Map<String, Object> dto) {
		this.dto = dto;
	} 
	public void showDto() 
	{
		System.out.println(this.dto);
	}
	//��ȡdto�е�ֵ
	private final Object get(String key) 
	{
		return this.dto.get(key);
	}
	
	public final boolean addRice()throws Exception
	{
		PreparedStatement pstm = null;
		//����sql
		StringBuilder sql = new StringBuilder()
				.append("insert into basicGoods (bgnumber,bgname,bglevel,bgspec,bgprice,bgtype,bgexp,bgchandi,remarks)")
				.append(" values (?,?,?,?,?,?,?,?,?)");
		//��������
		Object args[]={
				this.get("bgname"),this.get("bglevel"),this.get("bgspec"),this.get("bgprice"),
				this.get("bgtype"),this.get("bgexp"),this.get("bgchandi"),this.get("bgremarks")};
		try 
		{
			pstm = DBUtils.prepareStatement(sql.toString());
			pstm.setObject(1, Tools.mealsno());
			int index = 2;
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
