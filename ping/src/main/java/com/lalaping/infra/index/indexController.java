package com.lalaping.infra.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lalaping.mall.fish.FishService;
import com.lalaping.mall.fish.FishVo;



@Controller
public class indexController {
	@Autowired
	FishService fishService;
	
	@RequestMapping(value = "/v1/index")
	public String index(Model model, FishVo vo){
		model.addAttribute("fishList",fishService.selectList(vo));
		return "/usr/v1/etc/ping_index";
	}
}
