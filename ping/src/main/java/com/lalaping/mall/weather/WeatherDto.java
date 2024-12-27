package com.lalaping.mall.weather;

public class WeatherDto {
	private String osStationId;
	private String osStationName;
	private Double latitude;
	private Double longitude;
	private Double distance;
//-----------------------------
	public String getOsStationId() {
		return osStationId;
	}
	public void setOsStationId(String osStationId) {
		this.osStationId = osStationId;
	}
	public String getOsStationName() {
		return osStationName;
	}
	public void setOsStationName(String osStationName) {
		this.osStationName = osStationName;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	
}
