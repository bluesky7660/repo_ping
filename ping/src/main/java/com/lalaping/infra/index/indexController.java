package com.lalaping.infra.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lalaping.infra.ship.ShipVo;

@Controller
public class indexController {
//	@Autowired
	@RequestMapping(value = "/v1/index")
	public String index(Model model,@ModelAttribute("vo") ShipVo vo){
//		model.addAttribute("list",shipService.selectList(vo));
		return "/usr/v1/etc/ping_index";
	}
}
