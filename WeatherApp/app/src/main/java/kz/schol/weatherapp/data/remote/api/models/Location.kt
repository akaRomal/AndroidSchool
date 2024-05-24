package kz.schol.weatherapp.data.remote.api.models

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("name")
    val city: String,
)
