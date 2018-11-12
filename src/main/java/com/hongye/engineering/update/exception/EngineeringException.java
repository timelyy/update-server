package com.hongye.engineering.update.exception;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.cloudbuild.common.web.support.HttpCode;
import org.springframework.ui.ModelMap;

import com.hongye.engineering.update.common.BizCodeEnum;
import com.hongye.engineering.update.common.BizModel;
import com.hongye.engineering.update.common.SysModel;
import com.hongye.engineering.update.constants.EngineeringConstants;

/**
 * ClassName:EngineeringBaseException <br/>
 * Date: 2018年2月8日 下午2:10:19 <br/>
 * 
 * @author dell
 * @version
 * @since JDK 1.8
 * @see
 */
public class EngineeringException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	protected BizCodeEnum code = BizCodeEnum.B2B5000;

	public EngineeringException() {
	}
	
	public EngineeringException(BizCodeEnum code) {
        super(code.getMsg());
		this.code = code;
	}

	public EngineeringException(Throwable ex) {
		super(ex);
	}
	
	public EngineeringException(String message) {
		super(message);
	}

	public EngineeringException(String message, BizCodeEnum code) {
		super(message);
		this.code = code;
	}
	
	public EngineeringException(String message, Throwable ex) {
		super(message, ex);
	}

	public EngineeringException(String message, Throwable ex, BizCodeEnum code) {
		super(message, ex);
		this.code = code;
	}

	public void handler(ModelMap modelMap) {
		Map<String, Object> msgBody = new HashMap<>();
		SysModel sysModel = new SysModel(HttpCode.OK.value(), HttpCode.OK.msg(), System.currentTimeMillis());
        msgBody.put(EngineeringConstants.SYS_INFO, sysModel);
		BizModel bizModel = new BizModel();
		bizModel.setCode(getBizCode().getValue());
		if (StringUtils.isNotBlank(getMessage())) {
			bizModel.setMsg(getMessage());
		} else {
			bizModel.setMsg(getBizCode().getMsg());
		}
        msgBody.put(EngineeringConstants.BIZ_INFO, bizModel);
        modelMap.put(EngineeringConstants.MSG_BODY, msgBody);
	}

	public BizCodeEnum getBizCode() {
        return code;
    }

}
