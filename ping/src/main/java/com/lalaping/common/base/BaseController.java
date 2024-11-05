package com.lalaping.common.base;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseController {
	@RequestMapping(value = "/v1/dashBoardXdm")
	public String dashBoardXdm() {
		return "/xdm/v1/infra/base/index";
	}
}
