package com.wmsys.services.support;

import java.util.List;
import java.util.Map;

public interface BaseServices 
{
	/**
	 * ΪServices ׼��ҳ������
	 * @param dto
	 */
	void setMapDto(Map<String,Object> dto);
	
	/**
	 * ͨ�ø��·���
	 * @param uType  ---  ���µĶ������
	 * @return
	 * @throws Exception
	 */
    default boolean update(String uType)throws Exception
    {
    	return false;
    }
    
    
    /**
     * ����������ѯ
     * @return
     * @throws Exception
     */
    default List<Map<String,String>> query()throws Exception
    {
    	return null;
    }
    
    /**
     * ��һʵ����ѯ
     * @return
     * @throws Exception
     */
    default Map<String,String> findById()throws Exception
    {
    	return null;
    }
}
