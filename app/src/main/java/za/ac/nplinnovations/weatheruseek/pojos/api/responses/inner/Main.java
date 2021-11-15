package za.ac.nplinnovations.weatheruseek.pojos.api.responses.inner;

public class Main {
    private Double temp;
    private Double feels_like;
    private Integer humidity;
    private Integer pressure;
    private Double temp_min;
    private Double temp_max;

    public String getTemp() {
        double answer = temp - 32;
        answer = answer * 5 / 9;
        return "" + answer;
    }

    public String getFeels_like() {
        double answer = feels_like - 32;
        answer = answer * 5 / 9;

        return "" + answer;
    }

    public void setFeels_like(Double feels_like) {
        this.feels_like = feels_like;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public String getTemp_min() {
        double answer = temp_min - 32;
        answer = answer * 5 / 9;

        return "" + answer;
    }

    public void setTemp_min(Double temp_min) {
        this.temp_min = temp_min;
    }

    public String getTemp_max() {
        double answer = temp_max - 32;
        answer = answer * 5 / 9;

        return "" + answer;
    }

    public void setTemp_max(Double temp_max) {
        this.temp_max = temp_max;
    }
}
