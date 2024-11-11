package com.lalaping.infra.port;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PortController {
	@Autowired
	public PortService portService;
	
	@RequestMapping(value = "/v1/port/portXdmList")
	public String portXdmList(Model model,@ModelAttribute("vo") PortVo vo){
		model.addAttribute("list",portService.selectList(vo));
		model.addAttribute("formLink", "portXdmForm");
		return "/xdm/v1/port/portXdmList";
	}
	@RequestMapping(value = "/v1/port/portXdmForm")
	public String portXdmForm(Model model) {
		model.addAttribute("listLink", "portXdmList");
		return "/xdm/v1/port/portXdmForm";
	}
	@RequestMapping(value = "/v1/port/portXdmInst")
	public String portXdmInst(PortDto portDto) {
		portService.insert(portDto);
		return "redirect:/v1/port/portXdmList";
	}
	@RequestMapping(value = "/v1/port/portXdmMFom")
	public String portXdmMFom(Model model,PortDto portDto) {
		model.addAttribute("item",portService.selectOne(portDto));
		return "/xdm/v1/port/portXdmMFom";
	}
	@RequestMapping(value="/v1/port/portXdmUpdt")
	public String portXdmUpdt(PortDto portDto) {
		portService.update(portDto);
		return "redirect:/v1/port/portXdmList";
	}
	@RequestMapping(value="/v1/port/portXdmUelt")
	public String portXdmUelt(PortDto portDto) {
		portService.uelete(portDto);
		return "redirect:/v1/port/portXdmList";
	}
	@RequestMapping(value="/v1/port/portXdmDelt")
	public String portXdmDelt(PortDto portDto) {
		portService.delete(portDto);
		return "redirect:/v1/port/portXdmList";
	}

}
