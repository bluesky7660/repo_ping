package com.lalaping.mall.mapPoint;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lalaping.mall.fish.FishService;
import com.lalaping.mall.fish.FishVo;


@Controller
public class MapPointController {
	@Autowired
	public MapPointService mapPointService;
	@Autowired
	FishService fishService;
	
	@RequestMapping(value = "/v1/mapPoint/mapPointXdmList")
	public String mapPointXdmList(Model model,@ModelAttribute("vo") MapPointVo vo){
		model.addAttribute("list",mapPointService.selectList(vo));
		return "xdm/v1/mapPoint/mapPointXdmList";
	}
	@RequestMapping(value = "/v1/mapPoint/mapPointXdmForm")
	public String mapPointXdmForm() {
		return "xdm/v1/mapPoint/mapPointXdmForm";
	}
	@RequestMapping(value = "/v1/mapPoint/mapPointXdmInst")
	public String mapPointXdmInst(MapPointDto mapPointDto) {
		mapPointService.insert(mapPointDto);
		return "redirect:/v1/mapPoint/mapPointXdmList";
	}
	@RequestMapping(value = "/v1/mapPoint/mapPointXdmMFom")
	public String mapPointXdmMFom(Model model,MapPointDto mapPointDto) {
		model.addAttribute("item",mapPointService.selectOne(mapPointDto));
		return "xdm/v1/mapPoint/mapPointXdmMFom";
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
	public String mapPointDetail(Model model, MapPointDto mapPointDto){
		model.addAttribute("item",mapPointService.selectUsrOne(mapPointDto));
		return "usr/v1/mapPoint/ping_mapPointDetail";
	}
	@RequestMapping(value = "/v1/mapPoint/mapPointAdd")
	public String mapPointAdd(Model model,FishVo fishVo){
//		model.addAttribute("list",mapPointService.selectList(vo));
		model.addAttribute("fishList",fishService.allOneList(fishVo));
		return "usr/v1/mapPoint/ping_mapPoint_add";
	}
	
	@RequestMapping(value = "/v1/mapPoint/mapPointSearchList")
	@ResponseBody
	public Map<String, Object> mapPointSearchList(MapPointDto mapPointDto,MapPointVo mapPointVo){
		if (mapPointVo.getFsSeqList() != null) {
		    System.out.println("fsSeqList2: " + mapPointVo.getFsSeqList().toString());
		} else {
		    System.out.println("fsSeqList2 is null");
		}
		Map<String, Object> returnMap = new HashMap<String, Object>();
		List<MapPointDto> rtPoint = mapPointService.selectSearchList(mapPointVo);
		System.out.println("성공");
	    returnMap.put("data", rtPoint);
		return returnMap;
	}

}
