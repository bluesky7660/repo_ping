package com.lalaping.common.kakao;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MapController {
	@RequestMapping(value = "/xdm/v1/kakao/map")
	public String kakaoMap() {
		return "/xdm/v1/kakao/map";
	}
	@RequestMapping(value = "/xdm/v1/kakao/mapTest")
	public String kakaoMapTest() {
		return "/xdm/v1/kakao/mapTest";
	}
	@RequestMapping(value = "/xdm/v1/kakao/chicken.json")
	public String kakaoChicken() {
//		System.out.println("chicken.json : " + );
		return "/xdm/v1/kakao/chicken.json";
	}
}