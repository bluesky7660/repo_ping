package com.lalaping.mall.weather;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lalaping.mall.mapPoint.MapPointDto;
import com.lalaping.mall.mapPoint.MapPointService;

@Controller
public class WeatherController {
	@Value("${khoa.api.key}")
    private String API_KEY;
	
	@Value("${data.api.key}")
    private String Weather_API_KEY;
	
	@Autowired
	MapPointService mapPointService;
	
	@Autowired
	WeatherService weatherService;
	
	@RequestMapping(value = "/v1/weather/weatherPoint")
	public String weatherPoint(Model model, MapPointDto mapPointDto ,WeatherVo weatherVo) {
		MapPointDto mapPointItem =  mapPointService.selectOne(mapPointDto);
	    model.addAttribute("item", mapPointItem);
	    
	    
	    RestTemplate restTemplate = new RestTemplate();
	    model.addAttribute("khoaData", khoaWeather(mapPointItem.getMpLatitude(),mapPointItem.getMpLongitude(), weatherVo));

	    String longitude = String.valueOf(mapPointService.selectOne(mapPointDto).getMpLongitude());
	    String latitude = String.valueOf(mapPointService.selectOne(mapPointDto).getMpLatitude());

	    String apiUrl = "https://marine-api.open-meteo.com/v1/marine?latitude=" + latitude + 
	            "&longitude=" + longitude + 
	            "&hourly=wave_height,wave_direction,wave_period&forecast_days=1&temporal_resolution=native&timezone=Asia/Seoul";

	    String apiUrl2 = "https://api.open-meteo.com/v1/forecast?latitude="+latitude+"&longitude="+longitude+
	    		"&hourly=temperature_2m,relative_humidity_2m,precipitation_probability,precipitation,weather_code,wind_speed_10m,wind_direction_10m&forecast_days=1&temporal_resolution=native&timezone=Asia/Seoul";
	    
	    ResponseEntity<WeatherResponse> response2 = restTemplate.getForEntity(apiUrl2,WeatherResponse.class);
	    ResponseEntity<WeatherResponse> response = restTemplate.getForEntity(apiUrl, WeatherResponse.class);
	    WeatherResponse weatherData2 = response2.getBody();
	    WeatherResponse weatherData = response.getBody();
	    if(mapPointItem.getMpType()==5) {
	    	Coordinate coordinate = dfs_xy_conv("toXY", mapPointItem.getMpLatitude(), mapPointItem.getMpLongitude());
		    List<Map<String, Object>> shortTerm = weatherForecast(coordinate.x, coordinate.y);
		    model.addAttribute("shortTerm", shortTerm);
		    
		    Map<String, Object> longTerm = weatherMidSeaFcst(mapPointItem.getMpLatitude(), mapPointItem.getMpLongitude());
		    model.addAttribute("longTerm", longTerm);
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
	public String convertWeatherShortTermCodeToKorean(int skycode, int PTYcode) {
	    if (PTYcode == 0) {
	        switch (skycode) {
	            case 1: return "맑음";  		    // 맑음
	            case 3: return "구름 많음";        // 구름많이 
	            case 4: return "흐림";     		// 흐림
	            default: return "알 수 없음";		// 알 수 없음
	        }
	    } else {
	        switch (PTYcode) {
	            case 1: return "비";       // 비
	            case 2: return "비 / 눈";   // 비 또는 눈
	            case 3: return "눈";       // 눈
	            case 4: return "소나기";    // 소나기
	            default: return "알 수 없음";        // 알 수 없음
	        }
	    }
	}
	public String convertWeatherShortTermCodeToIcon(int skycode, int PTYcode) {
		if (PTYcode == 0) {
	        switch (skycode) {
	            case 1: return "wi wi-day-sunny";  // 맑음
	            case 3: return "wi wi-cloud";      // 구름많이
	            case 4: return "wi wi-cloudy";     // 흐림
	            default: return "wi wi-na";		   // 알 수 없음
	        }
	    } else {
	        switch (PTYcode) {
	            case 1: return "wi wi-rain";       // 비
	            case 2: return "wi wi-rain-mix";   // 비 또는 눈
	            case 3: return "wi wi-snow";       // 눈
	            case 4: return "wi wi-showers";    // 소나기
	            default: return "wi wi-na";        // 알 수 없음
	        }
	    }
	}
	public static class Coordinate {
	    public int x;
	    public int y;

	    public Coordinate() {}
	    
	    public Coordinate(int x, int y) {
	        this.x = x;
	        this.y = y;
	    }
	}
	public static Coordinate dfs_xy_conv(String code, double v1, double v2) {
	    // 기상청의 LCC DFS 좌표 변환 공식
	    double RE = 6371.00877; // 지구 반경(km)
	    double GRID = 5.0; // 격자 간격(km)
	    double SLAT1 = 30.0; // 투영 위도1(degree)
	    double SLAT2 = 60.0; // 투영 위도2(degree)
	    double OLON = 126.0; // 기준점 경도(degree)
	    double OLAT = 38.0; // 기준점 위도(degree)
	    int XO = 43; // 기준점 X좌표(GRID)
	    int YO = 136; // 기준점 Y좌표(GRID)
	    
	    double DEGRAD = Math.PI / 180.0;
	    double RADDEG = 180.0 / Math.PI;

	    double re = RE / GRID;
	    double slat1 = SLAT1 * DEGRAD;
	    double slat2 = SLAT2 * DEGRAD;
	    double olon = OLON * DEGRAD;
	    double olat = OLAT * DEGRAD;

	    double sn = Math.tan(Math.PI * 0.25 + slat2 * 0.5) / Math.tan(Math.PI * 0.25 + slat1 * 0.5);
	    sn = Math.log(Math.cos(slat1) / Math.cos(slat2)) / Math.log(sn);
	    double sf = Math.tan(Math.PI * 0.25 + slat1 * 0.5);
	    sf = Math.pow(sf, sn) * Math.cos(slat1) / sn;
	    double ro = Math.tan(Math.PI * 0.25 + olat * 0.5);
	    ro = re * sf / Math.pow(ro, sn);

	    Coordinate rs = new Coordinate();
	    
	    if (code.equals("toXY")) {
	        double ra = Math.tan(Math.PI * 0.25 + (v1) * DEGRAD * 0.5);
	        ra = re * sf / Math.pow(ra, sn);
	        double theta = v2 * DEGRAD - olon;
	        if (theta > Math.PI) theta -= 2.0 * Math.PI;
	        if (theta < -Math.PI) theta += 2.0 * Math.PI;
	        theta *= sn;

	        rs.x = (int) Math.floor(ra * Math.sin(theta) + XO + 0.5);
	        rs.y = (int) Math.floor(ro - ra * Math.cos(theta) + YO + 0.5);
	    }
	    
	    return rs;
	}
	public String getSeaAreaText(double latitude, double longitude) {
        if (latitude >= 35 && latitude <= 37 && longitude >= 125 && longitude <= 127) {
            return "서해중부";
        } else if (latitude >= 34 && latitude <= 35 && longitude >= 126 && longitude <= 127) {
            return "서해남부";
        } else if (latitude >= 33 && latitude <= 34 && longitude >= 126 && longitude <= 128) {
            return "남해서부";
        } else if (latitude >= 34 && latitude <= 36 && longitude >= 128 && longitude <= 130) {
            return "남해동부";
        } else if (latitude >= 35 && latitude <= 37 && longitude >= 128 && longitude <= 130) {
            return "동해남부";
        } else if (latitude >= 37 && latitude <= 38 && longitude >= 129 && longitude <= 130) {
            return "동해중부";
        } else if (latitude >= 38 && latitude <= 40 && longitude >= 130 && longitude <= 132) {
            return "동해북부";
        } else if (latitude >= 37 && latitude <= 38 && longitude >= 124 && longitude <= 125) {
            return "서해북부";
        } else if (latitude >= 33 && latitude <= 34 && longitude >= 126 && longitude <= 127) {
            return "제주도";
        } else if (latitude >= 34 && latitude <= 35 && longitude >= 130 && longitude <= 132) {
            return "대화퇴";
        } else if (latitude >= 27 && latitude <= 32 && longitude >= 122 && longitude <= 127) {
            return "동중국해";
        } else if (latitude >= 30 && latitude <= 32 && longitude >= 129 && longitude <= 131) {
            return "규슈";
        } else if (latitude >= 42 && latitude <= 45 && longitude >= 133 && longitude <= 135) {
            return "연해주";
        } else {
            return "알 수 없는 해역";
        }
    }
	public String getSeaAreaCode(double latitude, double longitude) {
		System.out.println("latitude:"+latitude+",longitude:"+longitude);
		if (latitude >= 35 && latitude <= 37 && longitude >= 125 && longitude <= 127) {
            return "12A20000";  // 서해중부
        } else if (latitude >= 34 && latitude <= 35 && longitude >= 126 && longitude <= 127) {
            return "12A30000";  // 서해남부
        } else if (latitude >= 33 && latitude <= 34 && longitude >= 126 && longitude <= 128) {
            return "12B10000";  // 남해서부
        } else if (latitude >= 34 && latitude <= 36 && longitude >= 128 && longitude <= 130) {
            return "12B20000";  // 남해동부
        } else if (latitude >= 35 && latitude <= 37 && longitude >= 128 && longitude <= 130) {
            return "12C10000";  // 동해남부
        } else if (latitude >= 37 && latitude <= 38 && longitude >= 129 && longitude <= 130) {
            return "12C20000";  // 동해중부
        } else if (latitude >= 38 && latitude <= 40 && longitude >= 130 && longitude <= 132) {
            return "12C30000";  // 동해북부
        } else if (latitude >= 37 && latitude <= 38 && longitude >= 124 && longitude <= 125) {
            return "12A10000";  // 서해북부
        } else if (latitude >= 33 && latitude <= 34 && longitude >= 126 && longitude <= 127) {
            return "12B10500";  // 제주도
        } else if (latitude >= 34 && latitude <= 35 && longitude >= 130 && longitude <= 132) {
            return "12D00000";  // 대화퇴
        } else if (latitude >= 27 && latitude <= 32 && longitude >= 122 && longitude <= 127) {
            return "12E00000";  // 동중국해
        } else if (latitude >= 30 && latitude <= 32 && longitude >= 129 && longitude <= 131) {
            return "12F00000";  // 규슈
        } else if (latitude >= 42 && latitude <= 45 && longitude >= 133 && longitude <= 135) {
            return "12G00000";  // 연해주
        } else {
            return "알 수 없는 코드";  // 예외처리
        }
    }
	public List<Map<String, Object>> weatherForecast(Integer Latitude,Integer Longitude){
		String baseUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst";
        
		String serviceKey = Weather_API_KEY; // 원래 인증키
	    String encodedServiceKey = "";
	    try {
	        encodedServiceKey = URLEncoder.encode(serviceKey, StandardCharsets.UTF_8.toString()); // URL 인코딩된 인증키
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
        String pageNo = "1";
        String numOfRows = "1000";
        String dataType = "JSON";
        String baseDate = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        String baseTime = "0200";
        String nx = Latitude.toString();
        String ny = Longitude.toString();

        String urlString = baseUrl + "?serviceKey=" + encodedServiceKey
                + "&pageNo=" + pageNo
                + "&numOfRows=" + numOfRows
                + "&dataType=" + dataType
                + "&base_date=" + baseDate
                + "&base_time=" +baseTime
                + "&nx=" + nx
                + "&ny=" + ny;
        RestTemplate restTemplate2 = new RestTemplate();
        final URI uri = URI.create(urlString);
        ResponseEntity<String> response = restTemplate2.getForEntity(uri, String.class);

        JSONObject jsonResponses = new JSONObject(response.getBody());
        JSONObject jsonResponse = jsonResponses.getJSONObject("response");
        JSONObject body = jsonResponse.getJSONObject("body");
        JSONArray items = body.getJSONObject("items").getJSONArray("item");
        
        Map<String, List<Map<String, String>>> groupedByDate = new HashMap<>();
        Integer skycode = null;
        Integer PTYcode = null;
        for (int i = 0; i < items.length(); i++) {
            JSONObject forecast = items.getJSONObject(i);
            String fcstTime = forecast.getString("fcstTime");
            String fcstDate = forecast.getString("fcstDate") ;
            String category = forecast.getString("category");
            if (("0600".equals(fcstTime) || "1500".equals(fcstTime)) && ("SKY".equals(category) || "PTY".equals(category) || "WAV".equals(category) || "VEC".equals(category) || "WSD".equals(category))) {
                Map<String, String> entry = new HashMap<>();
                entry.put("fcstDate", fcstDate);
                entry.put("fcstTime", fcstTime);
                entry.put("category", category);
                if("SKY".equals(category)||"PTY".equals(category)) {
                	if(skycode==null || PTYcode==null) {
                		if("SKY".equals(category)) {
                			if("0".equals(forecast.getString("fcstValue"))) {
                				skycode = 0 ;
                			}else {
                				skycode = Integer.parseInt(forecast.getString("fcstValue"));
                			}
                		}else if("PTY".equals(category)){
                			if("0".equals(forecast.getString("fcstValue"))) {
                				PTYcode = 0 ;
                			}else {
                				PTYcode = Integer.parseInt(forecast.getString("fcstValue"));
                			}
                		}
                	}
                	if(skycode!=null && PTYcode!=null) {
                		String weatherIcon = convertWeatherShortTermCodeToIcon(skycode,PTYcode);
                		String weatherCode = convertWeatherShortTermCodeToKorean(skycode,PTYcode);
                		entry.put("weatherCode", weatherCode);
                		entry.put("weatherIcon", weatherIcon);
                		skycode = null;
                        PTYcode = null;
                	}
                	
                	entry.put("fcstValue", forecast.getString("fcstValue"));
                	
                }else if("VEC".equals(category)) {
                	String vecValue = convertDirectionToKorean(Double.parseDouble(forecast.getString("fcstValue")));
                	String vecIcon = convertDirectionToIcon(Double.parseDouble(forecast.getString("fcstValue")));
                	entry.put("vecIcon", vecIcon);
                	entry.put("fcstValue", vecValue);
                }else {
                	entry.put("fcstValue", forecast.getString("fcstValue"));
                }
                
                groupedByDate
                        .computeIfAbsent(fcstDate, k -> new ArrayList<>())
                        .add(entry);
            }
        }

        List<Map<String, Object>> result = new ArrayList<>();
        for (String date : groupedByDate.keySet()) {
            Map<String, Object> dateForecast = new HashMap<>();
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
            try {
                Date fcstDate = inputFormat.parse(date);
                dateForecast.put("fcstDate", fcstDate);
            } catch (ParseException e) {
                System.out.println("날짜 파싱 오류: " + e.getMessage());
                dateForecast.put("fcstDate", date);
            }
            dateForecast.put("forecastData", groupedByDate.get(date));
            result.add(dateForecast);
        }
        result.sort((o1, o2) -> {
            Object date1 = o1.get("fcstDate");
            Object date2 = o2.get("fcstDate");
            if (date1 instanceof Date && date2 instanceof Date) {
                long time1 = ((Date) date1).getTime();
                long time2 = ((Date) date2).getTime();
                return Long.compare(time1, time2);
            } else if (date1 instanceof String && date2 instanceof String) {
                return date1.toString().compareTo(date2.toString());
            }
            return 0; 
        });

        return result;
	}
	public Map<String, Object> weatherMidSeaFcst(Double Latitude,Double Longitude){
		String baseUrl = "http://apis.data.go.kr/1360000/MidFcstInfoService/getMidSeaFcst";
        
		String serviceKey = Weather_API_KEY; // 원래 인증키
	    String encodedServiceKey = "";
	    try {
	        encodedServiceKey = URLEncoder.encode(serviceKey, StandardCharsets.UTF_8.toString()); // URL 인코딩된 인증키
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
        String pageNo = "1";
        String numOfRows = "10";
        String dataType = "JSON";
        String regId = getSeaAreaCode(Latitude,Longitude);
        String baseDate = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        String baseTime = "0600";
        String tmFc = baseDate+baseTime;
        
        String urlString2 = baseUrl + "?serviceKey=" + encodedServiceKey
                + "&pageNo=" + pageNo
                + "&numOfRows=" + numOfRows
                + "&dataType=" + dataType
                + "&regId=" + regId
                + "&tmFc=" + tmFc;
        RestTemplate restTemplate3 = new RestTemplate();
        final URI uri2 = URI.create(urlString2);
        ResponseEntity<String> response = restTemplate3.getForEntity(uri2, String.class);

        JSONObject jsonResponses = new JSONObject(response.getBody());
        JSONObject jsonResponse = jsonResponses.getJSONObject("response");
        JSONObject body = jsonResponse.getJSONObject("body");
        JSONArray items = body.getJSONObject("items").getJSONArray("item");
        Map<String,String> datas = new HashMap<>();
        JSONObject item = items.getJSONObject(0);
        for (String key : item.keySet()) {
            datas.put(key, item.get(key).toString());
        }
        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("seaName", getSeaAreaText(Latitude,Longitude));
        String nowdate =LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
        try {
            Date longDate = inputFormat.parse(nowdate);
            resultMap.put("nowdate", longDate);
            resultMap.put("date4", inputFormat.parse(LocalDate.now().plusDays(4).format(DateTimeFormatter.BASIC_ISO_DATE)));
            resultMap.put("date5", inputFormat.parse(LocalDate.now().plusDays(5).format(DateTimeFormatter.BASIC_ISO_DATE)));
            resultMap.put("date6", inputFormat.parse(LocalDate.now().plusDays(6).format(DateTimeFormatter.BASIC_ISO_DATE)));
            resultMap.put("date7", inputFormat.parse(LocalDate.now().plusDays(7).format(DateTimeFormatter.BASIC_ISO_DATE)));
            resultMap.put("date8", inputFormat.parse(LocalDate.now().plusDays(8).format(DateTimeFormatter.BASIC_ISO_DATE)));
        } catch (ParseException e) {
            System.out.println("날짜 파싱 오류: " + e.getMessage());
            resultMap.put("nowdate", nowdate); 
        }
        
        resultMap.put("data", datas);
        System.out.println("resultMap: " + resultMap);
        return resultMap;
	}
	/*물떄*/
	public Map<String, Object> khoaWeather( Double Latitude ,Double Longitude ,WeatherVo weatherVo){
		RestTemplate khoaRestTemplate = new RestTemplate();
		Map<String, Object> resultMap = new LinkedHashMap<>();
		
		String OBS_CODE = "";
		LocalDate today = LocalDate.now();
	    List<String> dates = new ArrayList<>();

	    for (int i = -1; i <= 6; i++) {
	        dates.add(today.plusDays(i).format(DateTimeFormatter.BASIC_ISO_DATE));
	    }
		weatherVo.setBaseMpLatitude(Latitude);
		weatherVo.setBaseMpLongitude(Longitude);
		WeatherDto closestStation = weatherService.observationStationNear(weatherVo); 
		if (closestStation != null) {
	        OBS_CODE = closestStation.getOsStationId();
	    } else {
	    	resultMap.put("error", "가까운 관측소를 찾을 수 없습니다.");
	    	return resultMap;
	    }
	    
	    ObjectMapper objectMapper = new ObjectMapper();

	    for (String date : dates) {
	        String apiUrl = "http://www.khoa.go.kr/api/oceangrid/tideObsPreTab/search.do?ServiceKey=" + API_KEY +
	                "&ObsCode=" + OBS_CODE + "&Date=" + date + "&ResultType=json";

	        try {
	            ResponseEntity<String> response = khoaRestTemplate.getForEntity(apiUrl, String.class);
	            resultMap.put(date, response.getBody()); 
	        } catch (Exception e) {
	            System.err.println("Error fetching data for date " + date + ": " + e.getMessage());
	            resultMap.put(date, "Error fetching data for date");
	        }
	    }

	    try {
	        return resultMap; 
	    } catch (Exception e) {
	        e.printStackTrace();
	        resultMap.put("error", "Error converting result to JSON");
	        return resultMap;
	    }
	}
}
