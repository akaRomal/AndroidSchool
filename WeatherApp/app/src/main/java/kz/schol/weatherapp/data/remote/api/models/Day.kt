package kz.schol.weatherapp.data.remote.api.models

import com.google.gson.annotations.SerializedName

data class Day(
    @SerializedName("maxtemp_c")
    val maxTemperature: Float,
    @SerializedName("mintemp_c")
    val minTemperature: Float,
    val condition: Condition,
)
