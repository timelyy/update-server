/** 
 * Project Name : engineering_project 
 * File Name : BizInfo.java 
 * Package Name : com.cloudbuild.engineering.vo.resp 
 * Date : 2018年10月18日下午5:00:38 
 * Copyright (c) 2018, 鸿业云建. 
 * 
*/  
  
package com.hongye.engineering.update.vo.resp;

import java.util.Map;

/** 
 * ClassName : BizInfo <br/>; 
 * Date :     2018年10月18日 下午5:00:38 <br/>; 
 * @author   w_ji_ang 
 * @version   
 * @since    JDK 1.8
 * @see       
 */
public class BizInfo {

    private String code;

    private String msg;

    private Map<String, Object> data;

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

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

}
