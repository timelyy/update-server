/** 
 * Project Name : engineering_project 
 * File Name : MsgBody.java 
 * Package Name : com.cloudbuild.engineering.vo.resp 
 * Date : 2018年10月18日下午4:59:38 
 * Copyright (c) 2018, 鸿业云建. 
 * 
*/  
  
package com.hongye.engineering.update.vo.resp;  

/** 
 * ClassName : MsgBody <br/>; 
 * Date :     2018年10月18日 下午4:59:38 <br/>; 
 * @author   w_ji_ang 
 * @version   
 * @since    JDK 1.8
 * @see       
 */
public class MsgBody {

    private SysInfo sysInfo;

    private BizInfo bizInfo;

    public SysInfo getSysInfo() {
        return sysInfo;
    }

    public void setSysInfo(SysInfo sysInfo) {
        this.sysInfo = sysInfo;
    }

    public BizInfo getBizInfo() {
        return bizInfo;
    }

    public void setBizInfo(BizInfo bizInfo) {
        this.bizInfo = bizInfo;
    }

}
