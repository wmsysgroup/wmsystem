package com.wmsys.services.support;

import java.util.List;
import java.util.Map;

public interface BaseServices 
{
	/**
	 * 为Services 准备页面数据
	 * @param dto
	 */
	void setMapDto(Map<String,Object> dto);
	
	/**
	 * 通用更新方法
	 * @param uType  ---  更新的动作类别
	 * @return
	 * @throws Exception
	 */
    default boolean update(String uType)throws Exception
    {
    	return false;
    }
    
    
    /**
     * 数据批量查询
     * @return
     * @throws Exception
     */
    default List<Map<String,String>> query()throws Exception
    {
    	return null;
    }
    
    /**
     * 单一实例查询
     * @return
     * @throws Exception
     */
    default Map<String,String> findById()throws Exception
    {
    	return null;
    }
}
