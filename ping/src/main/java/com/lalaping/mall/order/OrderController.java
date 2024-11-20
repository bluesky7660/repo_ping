package com.lalaping.mall.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lalaping.mall.ship.ShipDto;

@Controller
public class OrderController {
	@Autowired
	public OrderService orderService;
	
//	@RequestMapping(value = "/v1/checkout/ping_checkout")
//	public String ping_checkout(Model model,ShipDto shipDto) {
//		model.addAttribute("item", shipService.selectOne(shipDto));
//		return "/usr/v1/checkout/ping_checkout";
//	}

}
