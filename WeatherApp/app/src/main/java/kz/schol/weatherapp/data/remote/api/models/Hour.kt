package kz.schol.weatherapp.data.remote.api.models

import com.google.gson.annotations.SerializedName

data class Hour(
    @SerializedName("time_epoch")
    val timeEpoch: Long,
    val time: String,
    @SerializedName("temp_c")
    val temperature: Float,
    val condition: Condition
)
