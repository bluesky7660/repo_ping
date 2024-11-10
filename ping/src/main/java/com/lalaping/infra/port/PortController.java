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
	
	@RequestMapping(value = "/v1/infra/port/portXdmList")
	public String portXdmList(Model model,@ModelAttribute("vo") PortVo vo){
		model.addAttribute("list",portService.selectList(vo));
		return "/xdm/v1/infra/port/portXdmList";
	}
	@RequestMapping(value = "/v1/infra/port/portXdmForm")
	public String portXdmForm() {
		return "/xdm/v1/infra/port/portXdmForm";
	}
	@RequestMapping(value = "/v1/infra/port/portXdmInst")
	public String portXdmInst(PortDto portDto) {
		portService.insert(portDto);
		return "redirect:/v1/infra/port/portXdmList";
	}
	@RequestMapping(value = "/v1/infra/port/portXdmMFom")
	public String portXdmMFom(Model model,PortDto portDto) {
		model.addAttribute("item",portService.selectOne(portDto));
		return "/xdm/v1/infra/port/portXdmMFom";
	}
	@RequestMapping(value="/v1/infra/port/portXdmUpdt")
	public String portXdmUpdt(PortDto portDto) {
		portService.update(portDto);
		return "redirect:/v1/infra/port/portXdmList";
	}
	@RequestMapping(value="/v1/infra/port/portXdmUelt")
	public String portXdmUelt(PortDto portDto) {
		portService.uelete(portDto);
		return "redirect:/v1/infra/port/portXdmList";
	}
	@RequestMapping(value="/v1/infra/port/portXdmDelt")
	public String portXdmDelt(PortDto portDto) {
		portService.delete(portDto);
		return "redirect:/v1/infra/port/portXdmList";
	}

}
