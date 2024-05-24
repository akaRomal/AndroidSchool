package kz.schol.weatherapp.domain.models

data class WeatherByDay(
    val dateEpoch: String,
    val imageUlr: String,
    val timeMaxC: Int,
    val timeMinC: Int,
)

