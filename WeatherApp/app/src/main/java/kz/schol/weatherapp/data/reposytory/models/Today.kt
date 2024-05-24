package kz.schol.weatherapp.data.reposytory.models

data class Today(
    val city: String,
    val description: String,
    val imageUrl: String,
    val temperatureC: Int,
    val dateEpoch: Long,
)
