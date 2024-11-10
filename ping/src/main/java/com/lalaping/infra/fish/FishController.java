package com.lalaping.infra.fish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lalaping.infra.port.PortDto;
import com.lalaping.infra.port.PortService;
import com.lalaping.infra.port.PortVo;
@Controller
public class FishController {
	@Autowired
	public FishService fishService;
	
	@RequestMapping(value = "/v1/infra/fish/fishXdmList")
	public String fishXdmList(Model model,@ModelAttribute("vo") FishVo vo){
		model.addAttribute("list",fishService.selectList(vo));
		return "/xdm/v1/infra/fish/fishXdmList";
	}
	@RequestMapping(value = "/v1/infra/fish/fishXdmForm")
	public String fishXdmForm() {
		return "/xdm/v1/infra/fish/fishXdmForm";
	}
	@RequestMapping(value = "/v1/infra/fish/fishXdmInst")
	public String fishXdmInst(FishDto fishDto) {
		fishService.insert(fishDto);
		return "redirect:/v1/infra/fish/fishXdmList";
	}
	@RequestMapping(value = "/v1/infra/fish/fishXdmMFom")
	public String fishXdmMFom(Model model,FishDto fishDto) {
		model.addAttribute("item",fishService.selectOne(fishDto));
		return "/xdm/v1/infra/fish/fishXdmMFom";
	}
	@RequestMapping(value="/v1/infra/fish/fishXdmUpdt")
	public String fishXdmUpdt(FishDto fishDto) {
		fishService.update(fishDto);
		return "redirect:/v1/infra/fish/fishXdmList";
	}
	@RequestMapping(value="/v1/infra/fish/fishXdmUelt")
	public String fishXdmUelt(FishDto fishDto) {
		fishService.uelete(fishDto);
		return "redirect:/v1/infra/fish/fishXdmList";
	}
	@RequestMapping(value="/v1/infra/fish/fishXdmDelt")
	public String fishXdmDelt(FishDto fishDto) {
		fishService.delete(fishDto);
		return "redirect:/v1/infra/fish/fishXdmList";
	}

}
