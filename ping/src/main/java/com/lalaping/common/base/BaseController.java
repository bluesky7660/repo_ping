package com.lalaping.common.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lalaping.mall.mapPoint.MapPointDto;
import com.lalaping.mall.mapPoint.MapPointService;
import com.lalaping.mall.mapPoint.MapPointVo;

@Controller
public class BaseController {
	@Autowired
	MapPointService mapPointService;
	
	@RequestMapping(value = "/v1/dashBoardXdm")
	public String dashBoardXdm(Model model,MapPointVo mapPointVo, MapPointDto mapPointDto) {
		model.addAttribute("mapPointCount", mapPointService.listCount(mapPointVo));
		mapPointDto.setMpSeq(Integer.toString(mapPointService.listCount(mapPointVo)));
		model.addAttribute("lastMapPoint", mapPointService.selectOne(mapPointDto));
		//선상낚시 예약 관련 카운트 3개 model로 반환?
		return "xdm/v1/infra/base/index";
	}

}
