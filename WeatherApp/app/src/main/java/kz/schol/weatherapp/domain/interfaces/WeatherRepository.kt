package kz.schol.weatherapp.domain.interfaces

import kz.schol.weatherapp.domain.models.WeatherResponse

interface WeatherRepository {
    suspend fun getWeather(): WeatherResponse
}