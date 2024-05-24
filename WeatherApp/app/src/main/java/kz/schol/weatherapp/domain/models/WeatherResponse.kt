package kz.schol.weatherapp.domain.models

import androidx.compose.ui.graphics.Brush

data class WeatherResponse(
    val isConnection: Boolean = true,
    val errorText: String? = null,
    val background: Brush,
    val weatherToday: WeatherToday? = null,
    val weatherByDay: List<WeatherByDay>? = null,
)
