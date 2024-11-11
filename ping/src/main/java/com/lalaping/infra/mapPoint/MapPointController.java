package com.lalaping.infra.mapPoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lalaping.infra.port.PortDto;
import com.lalaping.infra.port.PortService;
import com.lalaping.infra.port.PortVo;
@Controller
public class MapPointController {
	@Autowired
	public MapPointService mapPointService;
	
	@RequestMapping(value = "/v1/mapPoint/mapPointXdmList")
	public String mapPointXdmList(Model model,@ModelAttribute("vo") MapPointVo vo){
		model.addAttribute("list",mapPointService.selectList(vo));
		return "/xdm/v1/mapPoint/mapPointXdmList";
	}
	@RequestMapping(value = "/v1/mapPoint/mapPointXdmForm")
	public String mapPointXdmForm() {
		return "/xdm/v1/mapPoint/mapPointXdmForm";
	}
	@RequestMapping(value = "/v1/mapPoint/mapPointXdmInst")
	public String mapPointXdmInst(MapPointDto mapPointDto) {
		mapPointService.insert(mapPointDto);
		return "redirect:/v1/mapPoint/mapPointXdmList";
	}
	@RequestMapping(value = "/v1/mapPoint/mapPointXdmMFom")
	public String mapPointXdmMFom(Model model,MapPointDto mapPointDto) {
		model.addAttribute("item",mapPointService.selectOne(mapPointDto));
		return "/xdm/v1/mapPoint/mapPointXdmMFom";
	}
	@RequestMapping(value="/v1/mapPoint/mapPointXdmUpdt")
	public String mapPointXdmUpdt(MapPointDto mapPointDto) {
		mapPointService.update(mapPointDto);
		return "redirect:/v1/mapPoint/mapPointXdmList";
	}
	@RequestMapping(value="/v1/mapPoint/mapPointXdmUelt")
	public String mapPointXdmUelt(MapPointDto mapPointDto) {
		mapPointService.uelete(mapPointDto);
		return "redirect:/v1/mapPoint/mapPointXdmList";
	}
	@RequestMapping(value="/v1/mapPoint/mapPointXdmDelt")
	public String mapPointXdmDelt(MapPointDto mapPointDto) {
		mapPointService.delete(mapPointDto);
		return "redirect:/v1/mapPoint/mapPointXdmList";
	}
	
	/*usr*/
	
	@RequestMapping(value = "/v1/mapPoint/mapPointDetail")
	public String mapPointDetail(Model model,@ModelAttribute("vo") MapPointVo vo){
//		model.addAttribute("list",mapPointService.selectList(vo));
		return "/usr/v1/mapPoint/ping_mapPointDetail";
	}

}
