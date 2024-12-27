package com.lalaping.mall.weather;

import org.springframework.stereotype.Repository;

@Repository
public interface WeatherDao {
	public WeatherDto observationStationNear(WeatherVo weatherVo);
}
