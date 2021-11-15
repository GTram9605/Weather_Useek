package za.ac.nplinnovations.weatheruseek.pojos.api.connection;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import za.ac.nplinnovations.weatheruseek.pojos.api.responses.ResponseWeather;

public interface Methods {
    @GET("data/2.5/weather")
    Call<List<ResponseWeather>> getWeatherReport(@Query("lat") double lat, @Query("lon") double lon, @Query("appid") String app_key);
}
