
package com.hongye.engineering.update.common;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.cloudbuild.common.web.Constants;
import org.cloudbuild.common.web.support.HttpCode;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hongye.engineering.update.constants.EngineeringConstants;
import com.hongye.engineering.update.exception.EngineeringException;

/**
 * 控制器基类
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:47:58
 */
public abstract class EngineeringBaseController {

    protected final Logger logger = LogManager.getLogger(this.getClass());

    /** 设置成功响应代码 */
    protected ResponseEntity<ModelMap> setSuccessModelMap(ModelMap modelMap) {
        return setSuccessModelMap(modelMap, new BizModel(BizCodeEnum.B2B2000.getValue(), BizCodeEnum.B2B2000.getMsg()));
    }

    /** 设置成功响应代码 */
    protected ResponseEntity<ModelMap> setSuccessModelMap(ModelMap modelMap, BizModel data) {
        return setModelMap(modelMap, HttpCode.OK, data);
    }

    /** 设置响应代码 */
    protected ResponseEntity<ModelMap> setModelMap(ModelMap modelMap, HttpCode code) {
        return setModelMap(modelMap, code, null);
    }

    /** 设置响应代码 */
    protected ResponseEntity<ModelMap> setModelMap(ModelMap modelMap, HttpCode code, Object data) {
        modelMap.clear();
        Map<String, Object> msgBody = new HashMap<>();
        SysModel sysModel = new SysModel(code.value(), code.msg(), System.currentTimeMillis());
        msgBody.put(EngineeringConstants.SYS_INFO, sysModel);
        if (data != null) {
            msgBody.put(EngineeringConstants.BIZ_INFO, data);
        }
        modelMap.put(EngineeringConstants.MSG_BODY, msgBody);
        return ResponseEntity.ok(modelMap);
    }

    /** 设置错误响应代码 */
    protected ResponseEntity<ModelMap> setModelMap(ModelMap modelMap, BizCodeEnum code, String exception) {
        SysModel sysModel = new SysModel(HttpCode.OK.value(), HttpCode.OK.msg(), System.currentTimeMillis());
        BizModel bizModel = new BizModel();
        bizModel.setCode(code.getValue());
        if (StringUtils.isNotBlank(exception)) {
            bizModel.setMsg(exception);
        } else {
            bizModel.setMsg(code.getMsg());
        }
        Map<String, Object> msgBody = new HashMap<>();
        msgBody.put(EngineeringConstants.SYS_INFO, sysModel);
        msgBody.put(EngineeringConstants.BIZ_INFO, bizModel);
        modelMap.put(EngineeringConstants.MSG_BODY, msgBody);
        return ResponseEntity.ok(modelMap);
    }

    /** 异常处理 */
    @ExceptionHandler(RuntimeException.class)
    public void exceptionHandler(HttpServletResponse response, Exception ex) throws Exception {
        ModelMap modelMap = new ModelMap();
        if (ex instanceof EngineeringException) {
            ((EngineeringException) ex).handler(modelMap);
            logger.error(Constants.Exception_Head + " {}", ((EngineeringException) ex).getBizCode().getValue(), ex);
        } else {
            setModelMap(modelMap, BizCodeEnum.B2B5000, BizCodeEnum.B2B5000.getMsg());
            logger.error(Constants.Exception_Head + "{}", BizCodeEnum.B2B5000.getValue(), ex);
        }
        response.setContentType("application/json;charset=UTF-8");
        byte[] bytes = JSON.toJSONBytes(modelMap, SerializerFeature.DisableCircularReferenceDetect);
        response.getOutputStream().write(bytes);
    }

    protected void setHeaderForCrosDomain(HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
        response.addHeader("Access-Control-Allow-Headers:", "Content-Type, Content-Range, Content-Disposition, Content-Description");
        response.setContentType("text/html;charset=utf-8");
    }

    protected String getToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (token == null || "".equals(token)){
            token = request.getParameter("token");
        }    
        return token;
    }

}