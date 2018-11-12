package com.hongye.engineering.update.common;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * ClassName:BizModel <br/>
 * Function: 业务返回状态. <br/>
 * Date: 2018年2月1日 上午10:23:28 <br/>
 * 
 * @author dell
 * @version
 * @since JDK 1.8
 * @see
 */
public class BizModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String code;
	
	private String msg;

	private LinkedHashMap<String, Object> data = new LinkedHashMap<>();

	public void addAttribute(String attributeName, Object attributeValue) {
		data.put(attributeName, attributeValue);
	}

	public BizModel addAttributeT(String attributeName, Object attributeValue) {
		data.put(attributeName, attributeValue);
		return this;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public LinkedHashMap<String, Object> getData() {
		return data;
	}

	public void setData(LinkedHashMap<String, Object> data) {
		this.data = data;
	}

	public BizModel() {
		super();
	}

	public BizModel(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	
	

}