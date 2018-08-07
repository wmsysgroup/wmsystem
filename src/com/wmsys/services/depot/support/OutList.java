package com.wmsys.services.depot.support;

import java.math.BigDecimal;
import java.util.Date;

public class OutList 
{
	private Date date;
	private String id;
	private BigDecimal number;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public BigDecimal getNumber() {
		return number;
	}
	public void setNumber(BigDecimal number) {
		this.number = number;
	}
	
}
