package com.lalaping.mall.weather;

import java.util.List;

public class TideForecastResponse {

    private Result result;

    // Getter and Setter
    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    // 내부 클래스 Result (static)
    public static class Result {
        private List<Data> data;
        private Meta meta;

        // Getter and Setter
        public List<Data> getData() {
            return data;
        }

        public void setData(List<Data> data) {
            this.data = data;
        }

        public Meta getMeta() {
            return meta;
        }

        public void setMeta(Meta meta) {
            this.meta = meta;
        }
    }

    // 내부 클래스 Data (static)
    public static class Data {
        private String tph_level;
        private String tph_time;
        private String hl_code;

        // Getter and Setter
        public String getTph_level() {
            return tph_level;
        }

        public void setTph_level(String tph_level) {
            this.tph_level = tph_level;
        }

        public String getTph_time() {
            return tph_time;
        }

        public void setTph_time(String tph_time) {
            this.tph_time = tph_time;
        }

        public String getHl_code() {
            return hl_code;
        }

        public void setHl_code(String hl_code) {
            this.hl_code = hl_code;
        }
    }

    // 내부 클래스 Meta (static)
    public static class Meta {
        private String obs_post_id;
        private String obs_last_req_cnt;
        private double obs_lat;
        private double obs_lon;
        private String obs_post_name;

        // Getter and Setter
        public String getObs_post_id() {
            return obs_post_id;
        }

        public void setObs_post_id(String obs_post_id) {
            this.obs_post_id = obs_post_id;
        }

        public String getObs_last_req_cnt() {
            return obs_last_req_cnt;
        }

        public void setObs_last_req_cnt(String obs_last_req_cnt) {
            this.obs_last_req_cnt = obs_last_req_cnt;
        }

        public double getObs_lat() {
            return obs_lat;
        }

        public void setObs_lat(double obs_lat) {
            this.obs_lat = obs_lat;
        }

        public double getObs_lon() {
            return obs_lon;
        }

        public void setObs_lon(double obs_lon) {
            this.obs_lon = obs_lon;
        }

        public String getObs_post_name() {
            return obs_post_name;
        }

        public void setObs_post_name(String obs_post_name) {
            this.obs_post_name = obs_post_name;
        }
    }
}
