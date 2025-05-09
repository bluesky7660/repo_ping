package com.lalaping.mall.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lalaping.common.util.UtilDateTime;
import com.lalaping.mall.mapPoint.MapPointController;
import com.lalaping.mall.mapPoint.MapPointService;

@Controller
public class ReviewController {
	@Autowired
	ReviewService reviewService;
	MapPointService mapPointService;
	
	@RequestMapping(value = "v1/review/reviewXdmList")
	public String reviewXdmList(Model model,@ModelAttribute("vo") ReviewVo reviewVo) {
		model.addAttribute("formLink", "reviewXdmForm");
		reviewVo.setShDateStart(reviewVo.getShDateStart() == null || reviewVo.getShDateStart() == "" ? null : UtilDateTime.add00TimeString(reviewVo.getShDateStart()));
		reviewVo.setShDateEnd(reviewVo.getShDateEnd() == null || reviewVo.getShDateEnd() == "" ? null : UtilDateTime.add59TimeString(reviewVo.getShDateEnd()));
		reviewVo.setParamsPaging(reviewService.rvSelectListCount(reviewVo));
		model.addAttribute("list", reviewService.rvSelectList(reviewVo));
		return "xdm/v1/review/reviewXdmList";
	}
	@RequestMapping(value = "v1/review/reviewXdmInst")
	public String reviewXdmInst(ReviewDto reviewDto) {
		reviewService.rvinsert(reviewDto);
		return "redirect:/v1/review/reviewXdmList";
	}
	@RequestMapping(value = "v1/review/reviewXdmForm")
	public String reviewXdmForm(Model model,ReviewVo reviewVo) {
		model.addAttribute("list", reviewService.rvSelectList(reviewVo));
		model.addAttribute("listLink", "reviewXdmList");
		return "xdm/v1/review/reviewXdmForm";
	}
	@RequestMapping(value = "v1/review/reviewXdmMFom2")
	public String reviewXdmMFom(ReviewDto reviewDto, Model model) {
		model.addAttribute("item", reviewService.rvSelectOne(reviewDto));
		model.addAttribute("listLink", "reviewXdmList");
		return "xdm/v1/review/reviewXdmMFom2";
	}
	@RequestMapping(value = "v1/review/reviewXdmUpdt")
	public String reviewXdmUpdt(ReviewDto reviewDto) {
		reviewService.rvUpdate(reviewDto);
		return "redirect:/v1/review/reviewXdmList";
	}
	@RequestMapping(value = "v1/review/reviewXdmUelt")
	public String reviewXdmUelt(ReviewDto reviewDto) {
		reviewService.rvUelete(reviewDto);
		return "redirect:/v1/review/reviewXdmList";
	}
	@RequestMapping(value = "v1/review/reviewXdmDelt")
	public String reviewXdmDelt(ReviewDto reviewDto) {
		reviewService.rvDelete(reviewDto);
		return "redirect:/v1/review/reviewXdmList";
	}

}
