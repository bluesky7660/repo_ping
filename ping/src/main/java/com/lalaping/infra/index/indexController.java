package com.lalaping.infra.index;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lalaping.infra.code.CodeService;
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
	@Autowired
	CodeService codeService;
	
	@RequestMapping(value = "/v1/index")
	public String index(Model model, FishVo vo, MapPointVo mapPointVo, ShipVo shipVo){
		
		model.addAttribute("fishList",fishService.allList(vo));
		model.addAttribute("mapPoint",mapPointService.allList(mapPointVo));
		model.addAttribute("ships",shipService.selectUsrList(shipVo));
		
		List<MapPointDto> usrList = mapPointService.sessSelectList(mapPointVo);
		mapPointVo.setRowNumToShow(8);
		model.addAttribute("sessPoint",mapPointService.selectUsrList(mapPointVo));
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
	@RequestMapping(value = "/v1/mapPoint/getSeasonalData")
	@ResponseBody
	public Map<String, Object> getSeasonalData(MapPointDto mapPointDto,MapPointVo mapPointVo,
			@RequestParam(name = "shSeason", defaultValue = "0") String shSeason){
		Map<String, Object> returnMap = new HashMap<String, Object>();
		List<MapPointDto> rtPoint = new ArrayList<>();
		mapPointVo.setRowNumToShow(8);
		if(shSeason.equals("0")) {
			System.out.println("연중");
			rtPoint = mapPointService.selectUsrList(mapPointVo);
		}else {
			System.out.println("연중아님");
			mapPointVo.setSeason_ssSeq(shSeason);
			rtPoint = mapPointService.sessSelectList(mapPointVo);
		}
		
		List<Map<String, Object>> points = new ArrayList<>();
	    for (MapPointDto point : rtPoint) {
	        Map<String, Object> pointData = new HashMap<>();
	        pointData.put("mpSeq", point.getMpSeq());
	        pointData.put("mpTitle", point.getMpTitle());
	        pointData.put("mpAddress", point.getMpAddress());
	        String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(point.getMpRegDate());
	        pointData.put("mpRegDate", formattedDate);
	        pointData.put("fsNameList", point.getFsNameList());
	        if(!shSeason.equals("0")) {
	        	pointData.put("season_ssSeq", point.getSeason_ssSeq());
		        int a = Integer.parseInt(point.getSeason_ssSeq());
		        try {
					pointData.put("speciesSeason", codeService.selectOneCachedCode(a));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  // 계산된 값 추가
			}
	        points.add(pointData);
	    }
	    System.out.println("성공");
	    returnMap.put("data", points);
		return returnMap;
	}
	
}
