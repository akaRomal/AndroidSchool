package kz.schol.weatherapp.data.remote.api.models

import com.google.gson.annotations.SerializedName

data class ForecastDay(
    val date: String,
    @SerializedName("date_epoch")
    val dateEpoch: Long,
    val day: Day,
    val hour: List<Hour>,
)
