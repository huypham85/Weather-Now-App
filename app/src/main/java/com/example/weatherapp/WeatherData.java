package com.example.weatherapp;

import android.graphics.Bitmap;

class WeatherData {
    private String city;
    private String temp;
    private String maxTemp;
    private String minTemp;
    private String time;
    private String vision;
    private String humid;
    private String feel_like;
    private Bitmap bitmap;
    private String country;

    public WeatherData(String city, String temp, String maxTemp, String minTemp, String time, String vision, String humid, String feel_like, Bitmap bitmap,String country) {
        this.city = city;
        this.temp = temp;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.time = time;
        this.vision = vision;
        this.humid = humid;
        this.feel_like = feel_like;
        this.bitmap = bitmap;
        this.country=country;
    }

    public WeatherData(String newCity, String newTemp) {
        this.city = newCity;
        this.temp = newTemp;
    }

    public WeatherData() {

    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVision() {
        return vision;
    }

    public void setVision(String vision) {
        this.vision = vision;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getHumid() {
        return humid;
    }

    public void setHumid(String humid) {
        this.humid = humid;
    }

    public String getFeel_like() {
        return feel_like;
    }

    public void setFeel_like(String feel_like) {
        this.feel_like = feel_like;
    }
}
