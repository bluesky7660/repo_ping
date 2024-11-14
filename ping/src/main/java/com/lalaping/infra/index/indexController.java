package com.lalaping.infra.index;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lalaping.mall.fish.FishService;
import com.lalaping.mall.fish.FishVo;
import com.lalaping.mall.mapPoint.MapPointDto;
import com.lalaping.mall.mapPoint.MapPointService;
import com.lalaping.mall.mapPoint.MapPointVo;
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
	public String index(Model model, FishVo vo, MapPointVo mapPointVo, ShipVo shipVo, 
			@RequestParam(name = "shSeason", defaultValue = "0") String shSeason){
		
		model.addAttribute("fishList",fishService.selectList(vo));
		model.addAttribute("mapPoint",mapPointService.selectUsrList(mapPointVo));
		mapPointVo.setRowNumToShow(8);
		if(shSeason=="0") {
			model.addAttribute("sessPoint",mapPointService.selectUsrList(mapPointVo));
		}else {
			model.addAttribute("sessPoint",mapPointService.sessSelectList(mapPointVo));
		}
		List<MapPointDto> usrList = mapPointService.sessSelectList(mapPointVo);

		if (usrList != null && !usrList.isEmpty()) {
		    // 리스트의 각 항목을 순회하며 season_ssSeq 값을 출력
		    for (MapPointDto mapPoint : usrList) {
		        System.out.println("시즌:"+mapPoint.getSeason_ssSeq());
		    }
		} else {
		    System.out.println("리스트가 비어있거나 null입니다.");
		}
		
		
		
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
