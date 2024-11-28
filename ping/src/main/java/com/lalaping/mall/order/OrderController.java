package com.lalaping.mall.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lalaping.infra.member.MemberDto;
import com.lalaping.infra.member.MemberService;
import com.lalaping.mall.ship.ShipDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class OrderController {
	@Autowired
	public OrderService orderService;
	@Autowired
	public MemberService memberService;
	
	@RequestMapping(value = "/v1/checkout/ping_checkout")
	public String ping_checkout(Model model,OrderDto orderDto, HttpSession session, MemberDto memberDto) {
		String sessSeqUsr = (String) session.getAttribute("sessSeqUsr");
		memberDto.setMmSeq(sessSeqUsr);
		model.addAttribute("item", orderService.CheckSelectOne(orderDto));
		model.addAttribute("memberitem", memberService.selectOne(memberDto));
		return "usr/v1/checkout/ping_checkout";
	}

}
