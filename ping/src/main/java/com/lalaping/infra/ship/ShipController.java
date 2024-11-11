package com.lalaping.infra.ship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lalaping.infra.port.PortDto;
import com.lalaping.infra.port.PortService;
import com.lalaping.infra.port.PortVo;
@Controller
public class ShipController {
	@Autowired
	public ShipService shipService;
	
	@RequestMapping(value = "/v1/ship/shipXdmList")
	public String shipXdmList(Model model,@ModelAttribute("vo") ShipVo vo){
		model.addAttribute("list",shipService.selectList(vo));
		return "/xdm/v1/ship/shipXdmList";
	}
	@RequestMapping(value = "/v1/ship/shipXdmForm")
	public String shipXdmForm() {
		return "/xdm/v1/ship/shipXdmForm";
	}
	@RequestMapping(value = "/v1/ship/shipXdmInst")
	public String shipXdmInst(ShipDto shipDto) {
		shipService.insert(shipDto);
		return "redirect:/v1/ship/shipXdmList";
	}
	@RequestMapping(value = "/v1/ship/shipXdmMFom")
	public String shipXdmMFom(Model model,ShipDto shipDto) {
		model.addAttribute("item",shipService.selectOne(shipDto));
		return "/xdm/v1/ship/shipXdmMFom";
	}
	@RequestMapping(value="/v1/ship/shipXdmUpdt")
	public String shipXdmUpdt(ShipDto shipDto) {
		shipService.update(shipDto);
		return "redirect:/v1/ship/shipXdmList";
	}
	@RequestMapping(value="/v1/ship/shipXdmUelt")
	public String shipXdmUelt(ShipDto shipDto) {
		shipService.uelete(shipDto);
		return "redirect:/v1/ship/shipXdmList";
	}
	@RequestMapping(value="/v1/ship/shipXdmDelt")
	public String shipXdmDelt(ShipDto shipDto) {
		shipService.delete(shipDto);
		return "redirect:/v1/ship/shipXdmList";
	}
	
	/*usr*/
	@RequestMapping(value = "/v1/ship/shipList")
	public String shipList(Model model,@ModelAttribute("vo") ShipVo vo){
		model.addAttribute("list",shipService.selectList(vo));
		return "/usr/v1/ship/ping_shipList";
	}
	@RequestMapping(value = "/v1/ship/shipDetail")
	public String shipDetail(Model model,@ModelAttribute("vo") ShipVo vo){
//		model.addAttribute("list",shipService.selectList(vo));
		return "/usr/v1/ship/ping_shipDetail";
	}

}
