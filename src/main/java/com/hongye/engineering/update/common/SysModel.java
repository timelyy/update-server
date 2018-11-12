package com.hongye.engineering.update.common;

import java.io.Serializable;

/** 
 * ClassName:SysModel <br/> 
 * Function: 系统返回状态. <br/> 
 * Date:     2018年2月1日 上午10:40:53 <br/> 
 * @author   dell 
 * @version   
 * @since    JDK 1.8
 * @see       
 */
public class SysModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer httpCode;
	
	private String msg;
	
	private Long timestamp;

	public Integer getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(Integer httpCode) {
		this.httpCode = httpCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public SysModel(Integer httpCode, String msg, Long timestamp) {
		super();
		this.httpCode = httpCode;
		this.msg = msg;
		this.timestamp = timestamp;
	}

	public SysModel() {
		super();
	}
	
	
	

}