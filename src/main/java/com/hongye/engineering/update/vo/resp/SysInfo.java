/** 
 * Project Name : engineering_project 
 * File Name : SysInfo.java 
 * Package Name : com.cloudbuild.engineering.vo.resp 
 * Date : 2018年10月18日下午5:00:32 
 * Copyright (c) 2018, 鸿业云建. 
 * 
*/  
  
package com.hongye.engineering.update.vo.resp;  

/** 
 * ClassName : SysInfo <br/>; 
 * Date :     2018年10月18日 下午5:00:32 <br/>; 
 * @author   w_ji_ang 
 * @version   
 * @since    JDK 1.8
 * @see       
 */
public class SysInfo {

    private int httpCode;

    private String msg;

    private Long timestamp;

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
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

}
