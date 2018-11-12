package com.hongye.engineering.update.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hongye.engineering.update.common.EngineeringBaseController;
import com.hongye.engineering.update.service.UpdateService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = { "UpdateController" })
@RestController
@RequestMapping("/update")
public class UpdateController extends EngineeringBaseController {

	@Autowired
	private UpdateService updateService;

	@ApiOperation("更新程序接口")
	@RequestMapping(value = "/doUpdate",method = RequestMethod.GET)
	public Object doUpdate(HttpServletRequest request, HttpServletResponse response, @ApiIgnore ModelMap modelMap
			,String url) {
		updateService.doUpdate(url);
		return setSuccessModelMap(modelMap);
	}

}
