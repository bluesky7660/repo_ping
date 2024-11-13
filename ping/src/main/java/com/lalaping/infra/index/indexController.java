package com.lalaping.infra.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lalaping.mall.fish.FishService;
import com.lalaping.mall.fish.FishVo;
import com.lalaping.mall.mapPoint.MapPointDto;
import com.lalaping.mall.mapPoint.MapPointService;
import com.lalaping.mall.mapPoint.MapPointVo;
import com.lalaping.mall.ship.ShipDto;
import com.lalaping.mall.ship.ShipService;
import com.lalaping.mall.ship.ShipVo;



@Controller
public class indexController {
	@Autowired
	FishService fishService;
	@Autowired
	MapPointService mapPointService;
	@Autowired
	ShipService shipService;
	
	@RequestMapping(value = "/v1/index")
	public String index(Model model, FishVo vo, MapPointVo mapPointVo, ShipVo shipVo){
		model.addAttribute("fishList",fishService.selectList(vo));
		model.addAttribute("mapPoint",mapPointService.selectList(mapPointVo));
		shipVo.setRowNumToShow(3);
		model.addAttribute("ships",shipService.selectUsrList(shipVo));
		return "/usr/v1/etc/ping_index";
	}
	@RequestMapping(value = "/v1/weather/weatherPoint")
	public String weatherPoint(Model model, MapPointDto mapPointDto){
		model.addAttribute("item",mapPointService.selectOne(mapPointDto));
		return "/usr/v1/weather/ping_weatherPoint";
	}
}
