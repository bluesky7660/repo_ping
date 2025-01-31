package com.lalaping.common.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lalaping.infra.member.MemberService;
import com.lalaping.mall.mapPoint.MapPointDto;
import com.lalaping.mall.mapPoint.MapPointService;
import com.lalaping.mall.mapPoint.MapPointVo;
import com.lalaping.mall.order.OrderDto;
import com.lalaping.mall.order.OrderService;
import com.lalaping.mall.review.ReviewService;
import com.lalaping.mall.review.ReviewVo;
import com.lalaping.mall.ship.ShipService;
import com.lalaping.mall.ship.ShipVo;

@Controller
public class BaseController {
	@Autowired
	MapPointService mapPointService;
	
	@Autowired
	OrderService orderService;
	@Autowired
	MemberService memberService;
	@Autowired
	ShipService shipService;
	@Autowired
	ReviewService reviewService;
	
	@RequestMapping(value = "/v1/dashBoardXdm")
	public String dashBoardXdm(Model model,MapPointVo mapPointVo, MapPointDto mapPointDto
			,ShipVo shipVo,OrderDto orderDto,ReviewVo reviewVo) {
		
		mapPointDto.setMpSeq(Integer.toString(mapPointService.listCount(mapPointVo)));
		model.addAttribute("lastMapPoint", mapPointService.selectOne(mapPointDto));
		model.addAttribute("orderList", orderService.previewList());
		model.addAttribute("commentList", reviewService.rvSelectList(reviewVo));
		model.addAttribute("lastPoint", mapPointService.lastPoint());
		
		orderDto.setOdReturnNy(26);
		model.addAttribute("orderCompletion", orderService.orderListCount(orderDto));
		orderDto.setOdReturnNy(27);
		model.addAttribute("orderUse", orderService.orderListCount(orderDto));
		orderDto.setOdReturnNy(28);
		model.addAttribute("orderRefund", orderService.orderListCount(orderDto));
		model.addAttribute("mapPointCount", mapPointService.listCount(mapPointVo));
		model.addAttribute("orderListCount", orderService.allListOrderCount());
		model.addAttribute("memberCount", memberService.listAllCount());
		model.addAttribute("orderListCount", orderService.allListOrderCount());
		model.addAttribute("shipListCount", shipService.listCount(shipVo));
		model.addAttribute("revenue", orderService.revenue());
//		model.addAttribute("commentListCount", reviewService.rvSelectListCount(reviewVo));
		//선상낚시 예약 관련 카운트 3개 model로 반환?
		return "xdm/v1/infra/base/index";
	}

}
