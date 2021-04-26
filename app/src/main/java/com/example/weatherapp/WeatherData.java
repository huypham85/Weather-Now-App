package com.example.weatherapp;

import android.graphics.Bitmap;

class WeatherData {
    private String city;
    private String temp;
    private String time;
    private String vision;
    private String humid;
    private String feel_like;
    private Bitmap bitmap;

    public WeatherData(String city, String temp, String time, String vision, String humid, String feel_like,Bitmap bitmap) {
        this.city = city;
        this.temp = temp;
        this.time = time;
        this.vision = vision;
        this.humid = humid;
        this.feel_like = feel_like;
        this.bitmap = bitmap;
    }

    public WeatherData(String newCity, String newTemp) {
        this.city = newCity;
        this.temp = newTemp;
    }

    public WeatherData() {

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
