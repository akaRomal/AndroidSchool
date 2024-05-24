package kz.schol.weatherapp.domain.models

data class WeatherToday(
    val city: String,
    val description: String,
    val imageUrl: String,
    val temperatureC: Int,
    val dateEpoch: String,
)
