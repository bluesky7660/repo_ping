package com.lalaping.mall.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lalaping.mall.mapPoint.MapPointDto;
import com.lalaping.mall.mapPoint.MapPointService;

@Controller
public class WeatherController {
	@Value("${khoa.api.key}")
    private String API_KEY;
	
	@Autowired
	MapPointService mapPointService;
	
	@RequestMapping(value = "/v1/weather/weatherPoint")
	public String weatherPoint(Model model, MapPointDto mapPointDto){
		model.addAttribute("item",mapPointService.selectOne(mapPointDto));
		return "/usr/v1/weather/ping_weatherPoint";
	}
	
	/*물떄*/
	@RequestMapping(value = "/v1/weather/khoa")
	public String khoa(){
		String OBS_CODE = "";
		String DATE = "";
		String apiUrl = "http://www.khoa.go.kr/api/oceangrid/DataType/search.do?ServiceKey=" + API_KEY +
                "&ObsCode=" + OBS_CODE + "&Date=" + DATE + "&ResultType=json";
		return "/usr/v1/weather/ping_weatherPoint";
	}
}
