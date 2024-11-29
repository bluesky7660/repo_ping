package com.lalaping.mall.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lalaping.infra.member.MemberDto;
import com.lalaping.infra.member.MemberService;

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
	@RequestMapping(value = "/v1/checkout/checkoutInst")
	public String checkoutInst(OrderDto orderDto) {
		orderService.insertOrder(orderDto);
		return "redirect:/v1/member/orderList";
	}
	
	@RequestMapping(value = "/v1/member/orderList")
	public String orderList(Model model, MemberDto memberDto, HttpSession session,OrderDto orderDto) {
		String sessSeqUsr = String.valueOf(session.getAttribute("sessSeqUsr"));
		memberDto.setMmSeq(sessSeqUsr);
		orderDto.setMember_mmSeq(sessSeqUsr);
		model.addAttribute("item", memberService.selectOne(memberDto));
		model.addAttribute("list", orderService.selectListOrder(orderDto));
		return "usr/v1/member/ping_orderList";
	}
	@RequestMapping(value = "/v1/member/orderUelete")
	public String orderUelete(OrderDto orderDto) {
		orderService.ueleteOrder(orderDto);
		return "redirect:/v1/member/orderReturn";
	}
	@RequestMapping(value = "/v1/member/orderReturn")
	public String orderReturn(Model model, MemberDto memberDto, HttpSession session,OrderDto orderDto) {
		String sessSeqUsr = String.valueOf(session.getAttribute("sessSeqUsr"));
		orderDto.setMember_mmSeq(sessSeqUsr);
		memberDto.setMmSeq(sessSeqUsr);
		model.addAttribute("list", orderService.selectListReturn(orderDto));
		model.addAttribute("item", memberService.selectOne(memberDto));
		return "usr/v1/member/ping_orderReturn";
	}

}
