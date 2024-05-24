package kz.schol.weatherapp.data.remote.api.models

import com.google.gson.annotations.SerializedName

data class CurrentWeather(
    @SerializedName("last_updated_epoch")
    val lastUpdatedEpoch: Long,
    @SerializedName("last_updated")
    val lastUpdated: String,
    @SerializedName("temp_c")
    val temperature: Float,
    val condition: Condition,
)
