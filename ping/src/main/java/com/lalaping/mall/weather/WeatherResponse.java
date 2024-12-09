package com.lalaping.mall.weather;

import java.util.List;

public class WeatherResponse {
    private Hourly hourly;

    // Getter 및 Setter
    public Hourly getHourly() {
        return hourly;
    }

    public void setHourly(Hourly hourly) {
        this.hourly = hourly;
    }

    public static class Hourly {
        private List<String> time;
        private List<Double> wave_height;
        private List<Double> wave_direction;
        private List<Double> wave_period;
        
        private List<Double> temperature_2m;
        private List<Double> relative_humidity_2m;
        private List<Double> precipitation_probability;
        private List<Double> precipitation;
        private List<Integer> weather_code;
        private List<Double> wind_speed_10m;
        private List<Double> wind_direction_10m;

        // Getter 및 Setter
        public List<String> getTime() {
            return time;
        }

        public void setTime(List<String> time) {
            this.time = time;
        }

		public List<Double> getWave_height() {
			return wave_height;
		}

		public void setWave_height(List<Double> wave_height) {
			this.wave_height = wave_height;
		}

		public List<Double> getWave_direction() {
			return wave_direction;
		}

		public void setWave_direction(List<Double> wave_direction) {
			this.wave_direction = wave_direction;
		}

		public List<Double> getWave_period() {
			return wave_period;
		}

		public void setWave_period(List<Double> wave_period) {
			this.wave_period = wave_period;
		}

		public List<Double> getTemperature_2m() {
			return temperature_2m;
		}

		public void setTemperature_2m(List<Double> temperature_2m) {
			this.temperature_2m = temperature_2m;
		}

		public List<Double> getRelative_humidity_2m() {
			return relative_humidity_2m;
		}

		public void setRelative_humidity_2m(List<Double> relative_humidity_2m) {
			this.relative_humidity_2m = relative_humidity_2m;
		}

		public List<Double> getPrecipitation_probability() {
			return precipitation_probability;
		}

		public void setPrecipitation_probability(List<Double> precipitation_probability) {
			this.precipitation_probability = precipitation_probability;
		}

		public List<Double> getPrecipitation() {
			return precipitation;
		}

		public void setPrecipitation(List<Double> precipitation) {
			this.precipitation = precipitation;
		}

		public List<Integer> getWeather_code() {
			return weather_code;
		}

		public void setWeather_code(List<Integer> weather_code) {
			this.weather_code = weather_code;
		}

		public List<Double> getWind_speed_10m() {
			return wind_speed_10m;
		}

		public void setWind_speed_10m(List<Double> wind_speed_10m) {
			this.wind_speed_10m = wind_speed_10m;
		}

		public List<Double> getWind_direction_10m() {
			return wind_direction_10m;
		}

		public void setWind_direction_10m(List<Double> wind_direction_10m) {
			this.wind_direction_10m = wind_direction_10m;
		}

        
    }
}

