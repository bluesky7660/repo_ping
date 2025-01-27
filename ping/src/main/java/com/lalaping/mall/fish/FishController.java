package com.lalaping.mall.fish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lalaping.common.util.UtilDateTime;

@Controller
public class FishController {
	@Autowired
	public FishService fishService;
	
	@RequestMapping(value = "/v1/fish/fishXdmList")
	public String fishXdmList(Model model,@ModelAttribute("vo") FishVo vo){
		vo.setShDateStart(vo.getShDateStart() == null || vo.getShDateStart() == "" ? null : UtilDateTime.add00TimeString(vo.getShDateStart()));
		vo.setShDateEnd(vo.getShDateEnd() == null || vo.getShDateEnd() == "" ? null : UtilDateTime.add59TimeString(vo.getShDateEnd()));
		vo.setParamsPaging(fishService.listCount(vo));
		model.addAttribute("list",fishService.selectList(vo));
		model.addAttribute("formLink", "fishXdmForm");
		return "xdm/v1/fish/fishXdmList";
	}
	@RequestMapping(value = "/v1/fish/fishXdmForm")
	public String fishXdmForm(Model model) {
		model.addAttribute("listLink", "fishXdmList");
		return "xdm/v1/fish/fishXdmForm";
	}
	@RequestMapping(value = "/v1/fish/fishXdmInst")
	public String fishXdmInst(FishDto fishDto) {
		fishService.insert(fishDto);
		return "redirect:/v1/fish/fishXdmList";
	}
	@RequestMapping(value = "/v1/fish/fishXdmMFom")
	public String fishXdmMFom(Model model,FishDto fishDto) {
		model.addAttribute("item",fishService.selectOne(fishDto));
//		System.out.println("seasonsSeqList:"+fishService.selectOne(fishDto).getSeasonsSeqList());
//		System.out.println("getSeasonsName:"+fishService.selectOne(fishDto).getSeasonsName());
		model.addAttribute("listLink", "fishXdmList");
		return "xdm/v1/fish/fishXdmMFom";
	}
	@RequestMapping(value="/v1/fish/fishXdmUpdt")
	public String fishXdmUpdt(FishDto fishDto) {
		fishService.update(fishDto);
		return "redirect:/v1/fish/fishXdmList";
	}
	@RequestMapping(value="/v1/fish/fishXdmUelt")
	public String fishXdmUelt(FishDto fishDto) {
		fishService.uelete(fishDto);
		return "redirect:/v1/fish/fishXdmList";
	}
	@RequestMapping(value="/v1/fish/fishXdmDelt")
	public String fishXdmDelt(FishDto fishDto) {
		fishService.delete(fishDto);
		return "redirect:/v1/fish/fishXdmList";
	}

}
