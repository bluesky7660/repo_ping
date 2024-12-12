package com.lalaping.mall.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReviewController {
	@Autowired
	ReviewService reviewService;
	
	@RequestMapping(value = "v1/review/reviewXdmList")
	public String reviewXdmList(Model model,@ModelAttribute("vo") ReviewVo reviewVo) {
		model.addAttribute("list", reviewService.rvSelectList(reviewVo));
		return "xdm/v1/review/reviewXdmList";
	}
	
	@RequestMapping(value = "v1/review/reviewXdmForm")
	public String reviewXdmForm() {
		return "xdm/v1/review/reviewXdmForm";
	}
	
	@RequestMapping(value = "v1/review/reviewXdmInst")
	public String reviewXdmInst(ReviewDto reviewDto) {
		reviewService.rvinsert(reviewDto);
		return "redirect:/v1/review/reviewXdmList";
	}
	@RequestMapping(value = "v1/review/reviewXdmMFom2")
	public String reviewXdmMFom(ReviewDto reviewDto, Model model) {
		model.addAttribute("item", reviewService.rvSelectOne(reviewDto));
		return "xdm/v1/review/reviewXdmMFom2";
	}

}
