package com.lalaping.mall.weather;

public class WeatherDataDto {
	private String time;
    private Double waveHeight;
    private String waveDirection;
    private Double wavePeriod;
    private Double temperature;
    private Double relativeHumidity;
    private Double windSpeed;
    private String windDirection;
    private Double precipitation;
    private Double precipitationProbability;
    private String weatherName;
    private String waveDirectionIcon;
    private String windDirectionIcon;
    private String weatherIcon;
//---------------------------------
    public WeatherDataDto(String time, Double waveHeight, String waveDirection, Double wavePeriod,
            Double temperature, Double relativeHumidity, Double windSpeed, String windDirection ,
            Double precipitation, Double precipitationProbability, String weatherName,
            String waveDirectionIcon,String windDirectionIcon,String weatherIcon) {
		this.time = time;
		this.waveHeight = waveHeight;
		this.waveDirection = waveDirection;
		this.wavePeriod = wavePeriod;
		
		this.temperature = temperature;
		this.relativeHumidity = relativeHumidity;
		this.windSpeed = windSpeed;
		this.precipitation = precipitation;
		this.weatherName = weatherName;
		this.precipitationProbability = precipitationProbability;
        this.windDirection = windDirection;
        
        this.waveDirectionIcon = waveDirectionIcon;
		this.windDirectionIcon = windDirectionIcon;
        this.weatherIcon = weatherIcon;
	}
    public WeatherDataDto(String time, Double temperature, Double relativeHumidity,
    		Double windSpeed, String windDirection , Double precipitation, 
            Double precipitationProbability, String weatherName, String windDirectionIcon,
            String weatherIcon) {
		this.time = time;
		
		this.temperature = temperature;
		this.relativeHumidity = relativeHumidity;
		this.windSpeed = windSpeed;
		this.precipitation = precipitation;
		this.weatherName = weatherName;
		this.precipitationProbability = precipitationProbability;
        this.windDirection = windDirection;
        
		this.windDirectionIcon = windDirectionIcon;
        this.weatherIcon = weatherIcon;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Double getWaveHeight() {
		return waveHeight;
	}
	public void setWaveHeight(Double waveHeight) {
		this.waveHeight = waveHeight;
	}
	public String getWaveDirection() {
		return waveDirection;
	}
	public void setWaveDirection(String waveDirection) {
		this.waveDirection = waveDirection;
	}
	public Double getWavePeriod() {
		return wavePeriod;
	}
	public void setWavePeriod(Double wavePeriod) {
		this.wavePeriod = wavePeriod;
	}
	public Double getTemperature() {
		return temperature;
	}
	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}
	public Double getRelativeHumidity() {
		return relativeHumidity;
	}
	public void setRelativeHumidity(Double relativeHumidity) {
		this.relativeHumidity = relativeHumidity;
	}
	public Double getWindSpeed() {
		return windSpeed;
	}
	public void setWindSpeed(Double windSpeed) {
		this.windSpeed = windSpeed;
	}
	public Double getPrecipitation() {
		return precipitation;
	}
	public void setPrecipitation(Double precipitation) {
		this.precipitation = precipitation;
	}
	public String getWeatherName() {
		return weatherName;
	}
	public void setWeatherName(String weatherName) {
		this.weatherName = weatherName;
	}
	public String getWindDirection() {
		return windDirection;
	}
	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}
	public Double getPrecipitationProbability() {
		return precipitationProbability;
	}
	public void setPrecipitationProbability(Double precipitationProbability) {
		this.precipitationProbability = precipitationProbability;
	}
	public String getWaveDirectionIcon() {
		return waveDirectionIcon;
	}
	public void setWaveDirectionIcon(String waveDirectionIcon) {
		this.waveDirectionIcon = waveDirectionIcon;
	}
	public String getWindDirectionIcon() {
		return windDirectionIcon;
	}
	public void setWindDirectionIcon(String windDirectionIcon) {
		this.windDirectionIcon = windDirectionIcon;
	}
	public String getWeatherIcon() {
		return weatherIcon;
	}
	public void setWeatherIcon(String weatherIcon) {
		this.weatherIcon = weatherIcon;
	}
    
}
