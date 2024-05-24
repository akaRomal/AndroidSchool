package kz.schol.weatherapp.data.reposytory.models

data class BoxWeather(
    val today: Today? = null,
    val byDays: List<ByDays>? = null,
    val byHour: List<ByHour>? = null,
    )
