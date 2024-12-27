package com.lalaping.mall.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {
	@Autowired
	WeatherDao weatherDao;
	
	public WeatherDto observationStationNear(WeatherVo weatherVo) {
		return weatherDao.observationStationNear(weatherVo);
	}
}
