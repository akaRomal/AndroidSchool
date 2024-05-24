package kz.schol.weatherapp.data.remote.api.models

import com.google.gson.annotations.SerializedName

data class Condition(
    @SerializedName("text")
    val conditionText: String,
    @SerializedName("icon")
    val conditionIcon: String,
)
