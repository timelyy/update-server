package com.hongye.engineering.update.feign;

import com.hongye.engineering.update.vo.resp.ResultVO;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

@Headers("Content-Type:application/json")
public interface EngineeringManageFeignClient {

	/**
     * 
     * getAuthCodeInfoByAuthCode: (根据授权码查询授权码信息). <br/> 
     * Date : 2018年10月18日下午5:23:48
     * @author w_ji_ang 
     * @param authCode
     * @return 
     * @since JDK 1.8
     */
    @RequestLine("GET /engineering/authCode/search/code/id?authCode={authCode}")
    ResultVO getAuthCodeInfoByAuthCode(@Param("authCode") String authCode);

}
