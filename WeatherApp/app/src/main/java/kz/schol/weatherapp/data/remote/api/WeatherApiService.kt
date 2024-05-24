package kz.schol.weatherapp.data.remote.api

import kz.schol.weatherapp.BuildConfig
import kz.schol.weatherapp.data.remote.api.models.WeatherForecast
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WeatherApiService {
    @Headers("Accept: application/json")
    @GET("forecast.json")
    fun getWeatherForecast(
        @Query("key") apiKey: String = BuildConfig.WEATHER_API_KEY,
//        @Query("q") location: String = BuildConfig.LOCATION,
        @Query("q") location: String = "Chelyabinsk",
        @Query("days") numDays: Int = BuildConfig.MAX_DAYS,
        @Query("lang") lang: String = BuildConfig.LANGUAGE_APP,
    ): Call<WeatherForecast>
}