package kz.schol.weatherapp.data.reposytory.models

data class ApiResponse(
    val isConnection: Boolean = true,
    val errorText: String? = null,
    val boxWeather: BoxWeather? = null,
)