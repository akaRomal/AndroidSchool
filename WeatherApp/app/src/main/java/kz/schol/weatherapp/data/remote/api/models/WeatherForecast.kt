package kz.schol.weatherapp.data.remote.api.models

data class WeatherForecast(
    val location: Location? = null,
    val current: CurrentWeather? = null,
    val forecast: Forecast? = null,
)
