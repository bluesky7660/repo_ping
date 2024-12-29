package com.lalaping.mall.weather;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lalaping.mall.mapPoint.MapPointDto;
import com.lalaping.mall.mapPoint.MapPointService;

@Controller
public class WeatherController {
	@Value("${khoa.api.key}")
    private String API_KEY;
	
	@Autowired
	MapPointService mapPointService;
	
	@Autowired
	WeatherService weatherService;
	
	@RequestMapping(value = "/v1/weather/weatherApi")
	public String weatherApi(){
		return"";
	}
	
	@RequestMapping(value = "/v1/weather/weatherPoint")
	public String weatherPoint(Model model, MapPointDto mapPointDto ,WeatherVo weatherVo) {
		MapPointDto mapPointItem =  mapPointService.selectOne(mapPointDto);
	    model.addAttribute("item", mapPointItem);
	    
	    RestTemplate restTemplate = new RestTemplate();
//		String OBS_CODE = "";
//		String DATE = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
//		
//		weatherVo.setBaseMpLatitude(mapPointItem.getMpLatitude());
//		weatherVo.setBaseMpLongitude(mapPointItem.getMpLongitude());
//		WeatherDto closestStation = weatherService.observationStationNear(weatherVo); 
//		if (closestStation != null) {
//	        OBS_CODE = closestStation.getOsStationId();
//	    } else {
//	        return "가까운 관측소를 찾을 수 없습니다.";
//	    }
//		String obApiUrl = "http://www.khoa.go.kr/api/oceangrid/tideObsPreTab/search.do?ServiceKey=" + API_KEY +
//                "&ObsCode=" + OBS_CODE + "&Date=" + DATE + "&ResultType=json";
//		System.out.println("obApiUrl:"+obApiUrl);
//		ResponseEntity<String> obResponse = restTemplate.getForEntity(obApiUrl, String.class);
//		
//		System.out.println("response.getBody():"+obResponse.getBody());
//		String jsonResponse = obResponse.getBody();
//
//        model.addAttribute("OBSData", obResponse.getBody());
//        JSONObject jsonObject = new JSONObject(jsonResponse);
//        List<JSONObject> highTideList = new ArrayList<>();
//        List<JSONObject> lowTideList = new ArrayList<>();
//
//        // 필터링
//        JSONArray data = jsonObject.getJSONObject("result").getJSONArray("data");
//        for (int i = 0; i < data.length(); i++) {
//            JSONObject item = data.getJSONObject(i);
//            if ("고조".equals(item.getString("hl_code"))) {
//                highTideList.add(item);
//            } else if ("저조".equals(item.getString("hl_code"))) {
//                lowTideList.add(item);
//            }
//        }
//        
//        // 고조와 저조 데이터를 모델에 추가
//        model.addAttribute("highTideData", highTideList.toString());
//        model.addAttribute("lowTideData", lowTideList.toString());
		
	    String longitude = String.valueOf(mapPointService.selectOne(mapPointDto).getMpLongitude());
	    String latitude = String.valueOf(mapPointService.selectOne(mapPointDto).getMpLatitude());

	    String apiUrl = "https://marine-api.open-meteo.com/v1/marine?latitude=" + latitude + 
	            "&longitude=" + longitude + 
	            "&hourly=wave_height,wave_direction,wave_period&forecast_days=1&temporal_resolution=native&timezone=Asia/Seoul";

	    System.out.println("api:" + apiUrl);
	    
	    String apiUrl2 = "https://api.open-meteo.com/v1/forecast?latitude="+latitude+"&longitude="+longitude+
	    		"&hourly=temperature_2m,relative_humidity_2m,precipitation_probability,precipitation,weather_code,wind_speed_10m,wind_direction_10m&forecast_days=1&temporal_resolution=native&timezone=Asia/Seoul";
	    System.out.println("api2:" + apiUrl2);
	    
	    ResponseEntity<WeatherResponse> response2 = restTemplate.getForEntity(apiUrl2,WeatherResponse.class);
	    ResponseEntity<WeatherResponse> response = restTemplate.getForEntity(apiUrl, WeatherResponse.class);
	    WeatherResponse weatherData2 = response2.getBody();
	    WeatherResponse weatherData = response.getBody();
	    if(mapPointItem.getMpType()==5) {
	    	if (weatherData != null && weatherData.getHourly() != null && weatherData2 != null && weatherData2.getHourly() != null) {
		        //기존날씨
		    	List<WeatherDataDto> weatherDataEntries2 = new ArrayList<>();
		        
		        //해양
		        List<WeatherDataDto> weatherDataEntries = new ArrayList<>();
		        List<String> times = weatherData.getHourly().getTime();
		        List<Double> waveHeights = weatherData.getHourly().getWave_height();
		        List<Double> waveDirections = weatherData.getHourly().getWave_direction();
		        List<Double> wavePeriods = weatherData.getHourly().getWave_period();
		        
		        List<Double> temperatures = weatherData2.getHourly().getTemperature_2m();
		        List<Double> relativeHumidities = weatherData2.getHourly().getRelative_humidity_2m();
		        List<Double> windSpeeds = weatherData2.getHourly().getWind_speed_10m();
		        List<Double> windDirections = weatherData2.getHourly().getWind_direction_10m();
		        List<Double> precipitations = weatherData2.getHourly().getPrecipitation();
		        List<Double> precipitationProbabilitys = weatherData2.getHourly().getPrecipitation_probability();
		        List<Integer> weatherCodes = weatherData2.getHourly().getWeather_code();

		        for (int i = 0; i < times.size(); i++) {
		        	System.out.println("waveDirections:"+waveDirections.get(i));
		            String waveDirectionKorean = convertDirectionToKorean(waveDirections.get(i));
		            String windDirectionKorean = convertDirectionToKorean(precipitationProbabilitys.get(i));
		            String weaherName = convertWeatherCodeToKorean(weatherCodes.get(i));
		            String waveDirectionIcon = convertDirectionToIcon(waveDirections.get(i));
		            String windDirectionIcon = convertDirectionToIcon(precipitationProbabilitys.get(i));
		            String weatherIcon = convertWeatherCodeToIcon(weatherCodes.get(i));
		            weatherDataEntries.add(new WeatherDataDto(
		            		times.get(i),
		                    waveHeights.get(i),
		                    waveDirectionKorean,
		                    wavePeriods.get(i),
		                    temperatures.get(i),
		                    relativeHumidities.get(i),
		                    windSpeeds.get(i),
		                    windDirectionKorean,
		                    precipitations.get(i),
		                    precipitationProbabilitys.get(i),
		                    weaherName,
		                    waveDirectionIcon,
		                    windDirectionIcon,
		                    weatherIcon
		            ));
		        }

		        // 모델에 데이터 추가
		        model.addAttribute("weatherData", weatherDataEntries);
		    } else {
		        System.out.println("Weather data is null or does not contain hourly data.");
		    }
	    }else {
	    	if (weatherData != null && weatherData.getHourly() != null && weatherData2 != null && weatherData2.getHourly() != null) {
		        //기존날씨
		    	List<WeatherDataDto> weatherDataEntries2 = new ArrayList<>();
		        
		        //해양
		        List<WeatherDataDto> weatherDataEntries = new ArrayList<>();
		        List<String> times = weatherData.getHourly().getTime();
		        
		        List<Double> temperatures = weatherData2.getHourly().getTemperature_2m();
		        List<Double> relativeHumidities = weatherData2.getHourly().getRelative_humidity_2m();
		        List<Double> windSpeeds = weatherData2.getHourly().getWind_speed_10m();
		        List<Double> windDirections = weatherData2.getHourly().getWind_direction_10m();
		        List<Double> precipitations = weatherData2.getHourly().getPrecipitation();
		        List<Double> precipitationProbabilitys = weatherData2.getHourly().getPrecipitation_probability();
		        List<Integer> weatherCodes = weatherData2.getHourly().getWeather_code();

		        for (int i = 0; i < times.size(); i++) {
		            String windDirectionKorean = convertDirectionToKorean(precipitationProbabilitys.get(i));
		            String weaherName = convertWeatherCodeToKorean(weatherCodes.get(i));
		            String windDirectionIcon = convertDirectionToIcon(precipitationProbabilitys.get(i));
		            String weatherIcon = convertWeatherCodeToIcon(weatherCodes.get(i));
		            weatherDataEntries.add(new WeatherDataDto(
		            		times.get(i),
		                    temperatures.get(i),
		                    relativeHumidities.get(i),
		                    windSpeeds.get(i),
		                    windDirectionKorean,
		                    precipitations.get(i),
		                    precipitationProbabilitys.get(i),
		                    weaherName,
		                    windDirectionIcon,
		                    weatherIcon
		            ));
		        }

		        // 모델에 데이터 추가
		        model.addAttribute("weatherData", weatherDataEntries);
		    } else {
		        System.out.println("Weather data is null or does not contain hourly data.");
		    }
	    }
	    return "usr/v1/weather/ping_weatherPoint";
	}

	public String convertDirectionToKorean(double direction) {
	    if (direction >= 337.5 || direction < 22.5) {
	        return "북 (N)";
	    } else if (direction >= 22.5 && direction < 45) {
	        return "북북동 (NNE)";
	    } else if (direction >= 45 && direction < 67.5) {
	        return "북동 (NE)";
	    } else if (direction >= 67.5 && direction < 90) {
	        return "동북동 (ENE)";
	    } else if (direction >= 90 && direction < 112.5) {
	        return "동 (E))";
	    } else if (direction >= 112.5 && direction < 135) {
	        return "동남동 (ESE)";
	    } else if (direction >= 135 && direction < 157.5) {
	        return "남동 (SE)";
	    } else if (direction >= 157.5 && direction < 180) {
	        return "남남동 (SSE)";
	    } else if (direction >= 180 && direction < 202.5) {
	        return "남 (S)";
	    } else if (direction >= 202.5 && direction < 225) {
	        return "남서 (SW)";
	    } else if (direction >= 225 && direction < 247.5) {
	        return "서남서 (WSW)";
	    } else if (direction >= 247.5 && direction < 270) {
	        return "서 (W)";
	    } else if (direction >= 270 && direction < 292.5) {
	        return "서북서 (WNW)";
	    } else if (direction >= 292.5 && direction < 315) {
	        return "북서 (NW)";
	    } else if (direction >= 315 && direction < 337.5) {
	        return "북북서 (NNW)";
	    } else {
	        return "알 수 없음";
	    }
	}
	public String convertWeatherCodeToKorean(int weatherCode) {
	    switch (weatherCode) {
	        case 0: return "맑음";
	        case 1: return "구름 조금";
	        case 2: return "구름 많음";
	        case 3: return "흐림";
	        case 61: return "비";
	        case 63: return "소나기";
	        case 80: return "소나기";
	        case 100: return "눈";
	        case 200: return "눈 또는 비";
	        case 201: return "눈 또는 비 (소량)";
	        case 202: return "눈 또는 비 (강함)";
	        case 210: return "비 또는 소나기 (약한)";
	        case 211: return "비 또는 소나기 (강한)";
	        default: return "알 수 없음";
	    }
	}
	public String convertDirectionToIcon(double direction) {
	    if (direction >= 337.5 || direction < 22.5) {
	        return "wi wi-wind wi-towards-n"; // 북
	    } else if (direction >= 22.5 && direction < 45) {
	        return "wi wi-wind wi-towards-nne"; // 북북동
	    } else if (direction >= 45 && direction < 67.5) {
	        return "wi wi-wind wi-towards-ne"; // 북동
	    } else if (direction >= 67.5 && direction < 90) {
	        return "wi wi-wind wi-towards-ene"; // 동북동
	    } else if (direction >= 90 && direction < 112.5) {
	        return "wi wi-wind wi-towards-e"; // 동
	    } else if (direction >= 112.5 && direction < 135) {
	        return "wi wi-wind wi-towards-ese"; // 동남동
	    } else if (direction >= 135 && direction < 157.5) {
	        return "wi wi-wind wi-towards-se"; // 남동
	    } else if (direction >= 157.5 && direction < 180) {
	        return "wi wi-wind wi-towards-sse"; // 남남동
	    } else if (direction >= 180 && direction < 202.5) {
	        return "wi wi-wind wi-towards-s"; // 남
	    } else if (direction >= 202.5 && direction < 225) {
	        return "wi wi-wind wi-towards-sw"; // 남서
	    } else if (direction >= 225 && direction < 247.5) {
	        return "wi wi-wind wi-towards-wsw"; // 서남서
	    } else if (direction >= 247.5 && direction < 270) {
	        return "wi wi-wind wi-towards-w"; // 서
	    } else if (direction >= 270 && direction < 292.5) {
	        return "wi wi-wind wi-towards-wnw"; // 서북서
	    } else if (direction >= 292.5 && direction < 315) {
	        return "wi wi-wind wi-towards-nw"; // 북서
	    } else if (direction >= 315 && direction < 337.5) {
	        return "wi wi-wind wi-towards-nnw"; // 북북서
	    } else {
	        return "wi wi-wind wi-towards-s"; // 기본값
	    }
	}

	public String convertWeatherCodeToIcon(int weatherCode) {
	    switch (weatherCode) {
	        case 0: return "wi wi-day-sunny"; // 맑음
	        case 1: return "wi wi-day-cloudy"; // 구름 조금
	        case 2: return "wi wi-cloud"; // 구름 많음
	        case 3: return "wi wi-cloudy"; // 흐림
	        case 61: return "wi wi-rain"; // 비
	        case 63: return "wi wi-showers"; // 소나기
	        case 80: return "wi wi-showers"; // 소나기
	        case 100: return "wi wi-snow"; // 눈
	        case 200: return "wi wi-snow"; // 눈 또는 비
	        case 201: return "wi wi-snow"; // 눈 또는 비 (소량)
	        case 202: return "wi wi-snow"; // 눈 또는 비 (강함)
	        case 210: return "wi wi-rain-mix"; // 비 또는 소나기 (약한)
	        case 211: return "wi wi-rain-mix"; // 비 또는 소나기 (강한)
	        default: return "wi wi-na"; // 알 수 없음
	    }
	}
	/*물떄*/
	@RequestMapping(value = "/v1/weather/khoa")
	@ResponseBody
	public String khoa(@RequestBody MapPointDto mapPointDto, WeatherVo weatherVo){
		RestTemplate restTemplate = new RestTemplate();
		String OBS_CODE = "";
//		String DATE = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
		LocalDate today = LocalDate.now();
	    List<String> dates = new ArrayList<>();

	    for (int i = -1; i <= 5; i++) {
	        dates.add(today.plusDays(i).format(DateTimeFormatter.BASIC_ISO_DATE));
	    }
		System.out.println("mapPointDto.getMpLatitude():"+mapPointDto.getMpLatitude());
		System.out.println("mapPointDto.getMpLongitude():"+mapPointDto.getMpLongitude());
		weatherVo.setBaseMpLatitude(mapPointDto.getMpLatitude());
		weatherVo.setBaseMpLongitude(mapPointDto.getMpLongitude());
		WeatherDto closestStation = weatherService.observationStationNear(weatherVo); 
		if (closestStation != null) {
	        OBS_CODE = closestStation.getOsStationId();
	    } else {
	        return "가까운 관측소를 찾을 수 없습니다.";
	    }
//		String result = "";
//	    for (String date : dates) {
//	        String apiUrl = "http://www.khoa.go.kr/api/oceangrid/tideObsPreTab/search.do?ServiceKey=" + API_KEY +
//	                "&ObsCode=" + OBS_CODE + "&Date=" + date + "&ResultType=json";
//	        System.out.println("obApiUrl:" + apiUrl);
//	        try {
//	            ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
//	            System.out.println("response.getBody() for date " + date + ":" + response.getBody());
//	            result += "Date: " + date + "\n" + response.getBody() + "\n\n";
//	        } catch (Exception e) {
//	            System.err.println("Error fetching data for date " + date + ": " + e.getMessage());
//	            result += "Date: " + date + " - Error fetching data\n";
//	        }
//	    }
//	    return result;
		// 날짜별 데이터를 저장할 Map 객체
	    Map<String, JsonNode> resultMap = new LinkedHashMap<>();
	    ObjectMapper objectMapper = new ObjectMapper();

	    // 여러 날짜의 데이터를 가져오기
	    for (String date : dates) {
	        String apiUrl = "http://www.khoa.go.kr/api/oceangrid/tideObsPreTab/search.do?ServiceKey=" + API_KEY +
	                "&ObsCode=" + OBS_CODE + "&Date=" + date + "&ResultType=json";
	        System.out.println("obApiUrl:" + apiUrl);

	        try {
	            ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
	            System.out.println("response.getBody() for date " + date + ":" + response.getBody());

	            // 응답을 JSON 형식으로 파싱하여 resultMap에 저장
	            JsonNode responseJson = objectMapper.readTree(response.getBody());
	            resultMap.put(date, responseJson);  // 날짜를 키로 사용하고, JSON 데이터를 값으로 저장
	        } catch (Exception e) {
	            System.err.println("Error fetching data for date " + date + ": " + e.getMessage());
	            // 오류 발생 시 Map에 오류 메시지 저장
	            resultMap.put(date, objectMapper.createObjectNode().put("error", "Error fetching data"));
	        }
	    }

	    // 최종 결과를 JSON 형식으로 반환
	    try {
	        return objectMapper.writeValueAsString(resultMap);  // Map을 JSON 형식으로 반환
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "Error converting result to JSON";
	    }
//		String apiUrl = "http://www.khoa.go.kr/api/oceangrid/tideObsPreTab/search.do?ServiceKey=" + API_KEY +
//                "&ObsCode=" + OBS_CODE + "&Date=" + DATE + "&ResultType=json";
//		System.out.println("obApiUrl:"+apiUrl);
//		ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
//		System.out.println("response.getBody():"+response.getBody());
//		return response.getBody();
	}
}
