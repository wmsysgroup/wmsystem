package com.wmsys.services.depot.support;

import java.util.List;
import java.util.Map;

public interface BaseServices 
{
	default Map<String,Object> findByno()throws Exception
	{
		return null;
	}
	
	default List<Map<String,Object>> findAll()throws Exception
	{
		return null;
	}
	
	void insertin()throws Exception;
	void insertdam()throws Exception;
	void insertout()throws Exception;
}
