package kz.schol.weatherapp.data.reposytory.models

data class ByDays(
    val dateEpoch: String,
    val imageUlr: String,
    val timeMaxC: Int,
    val timeMinC: Int,
)
