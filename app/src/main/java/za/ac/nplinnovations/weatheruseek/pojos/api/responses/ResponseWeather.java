package za.ac.nplinnovations.weatheruseek.pojos.api.responses;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import za.ac.nplinnovations.weatheruseek.pojos.api.responses.inner.Clouds;
import za.ac.nplinnovations.weatheruseek.pojos.api.responses.inner.Coord;
import za.ac.nplinnovations.weatheruseek.pojos.api.responses.inner.Main;
import za.ac.nplinnovations.weatheruseek.pojos.api.responses.inner.Rain;
import za.ac.nplinnovations.weatheruseek.pojos.api.responses.inner.Sys;
import za.ac.nplinnovations.weatheruseek.pojos.api.responses.inner.Weather;
import za.ac.nplinnovations.weatheruseek.pojos.api.responses.inner.Wind;

public class ResponseWeather {
    private Coord coord;
    private Sys sys;
    private Weather weather;
    private Main main;
    private Wind wind;
    private Rain rain;
    private Clouds clouds;
    private Integer dt;
    private Integer id;
    private String name;
    private Integer cod;

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public String getDt() {
        Calendar cal = Calendar.getInstance();
        Calendar calHour = Calendar.getInstance();
        calHour.add(Calendar.HOUR, -1);

        Calendar calDay = Calendar.getInstance();
        calDay.add(Calendar.HOUR, -24);

        Calendar calWeek = Calendar.getInstance();
        calHour.add(Calendar.HOUR, -168);


        if(dt == cal.getTimeInMillis())
                return "Now";
        else if (dt <= calHour.getTimeInMillis()) {
            long diff = dt - cal.getTimeInMillis();

            return TimeUnit.MILLISECONDS.toMinutes(diff) +  " minutes ago";
        }else if (dt <= calDay.getTimeInMillis()) {
            long diff = dt - cal.getTimeInMillis();

            return TimeUnit.MILLISECONDS.toHours(diff) +  " hours ago";
        }else if (dt <= calWeek.getTimeInMillis()) {
            long diff = dt - cal.getTimeInMillis();

            return TimeUnit.MILLISECONDS.toDays(diff) +  " days ago";
        }

        return null;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }
}
