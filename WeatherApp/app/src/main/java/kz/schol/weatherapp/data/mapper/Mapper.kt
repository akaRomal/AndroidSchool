package kz.schol.weatherapp.data.mapper

import kz.schol.weatherapp.data.remote.api.models.WeatherForecast
import kz.schol.weatherapp.data.reposytory.models.ApiResponse
import kz.schol.weatherapp.data.reposytory.models.BoxWeather
import kz.schol.weatherapp.data.reposytory.models.ByDays
import kz.schol.weatherapp.data.reposytory.models.ByHour
import kz.schol.weatherapp.data.reposytory.models.Today
import kz.schol.weatherapp.domain.models.BrushTimesDay
import kz.schol.weatherapp.domain.models.WeatherByDay
import kz.schol.weatherapp.domain.models.WeatherResponse
import kz.schol.weatherapp.domain.models.WeatherToday
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

class Mapper {

    fun toDomainFromApi(apiResponse: ApiResponse): WeatherResponse {
        val hour = LocalDateTime.now().hour

        val brushBackground = when (hour) {
            in 6..12 -> BrushTimesDay.MORNING.brush
            in 12..20 -> BrushTimesDay.DAY.brush
            else -> BrushTimesDay.NIGHT.brush
        }

        return WeatherResponse(
            isConnection = apiResponse.isConnection,
            errorText = apiResponse.errorText,
            background = brushBackground,
            weatherToday = toTodayApiFromTodayDomain(apiResponse.boxWeather?.today),
            weatherByDay = byDayForDomain(apiResponse.boxWeather?.byDays)
        )
    }

    private fun byDayForDomain(byDays: List<ByDays>?): List<WeatherByDay>? {
        return byDays?.map {
            WeatherByDay(
                dateEpoch = it.dateEpoch,
                imageUlr = it.imageUlr,
                timeMaxC = it.timeMaxC,
                timeMinC = it.timeMinC,
            )
        }
    }

    fun toWeatherFromApi(weatherForecast: WeatherForecast): BoxWeather {

        val dailyForecast = weatherForecast.forecast?.forecastDay
        val listByDays: MutableList<ByDays> = mutableListOf()
        val hourlyForecast: MutableList<ByHour> = mutableListOf()

        dailyForecast?.forEach {
            it.apply {
                listByDays.add(
                    ByDays(
                        dateEpoch = dateEpoch.toDayWeek(),
                        imageUlr = day.condition.conditionIcon,
                        timeMaxC = day.maxTemperature.toInt(),
                        timeMinC = day.minTemperature.toInt(),
                    )
                )

                hour.map { hoursDay ->
                    hourlyForecast.add(
                        ByHour(
                            title = hoursDay.condition.conditionText,
                            imageUlr = hoursDay.condition.conditionIcon,
                            timeC = hoursDay.temperature.toInt(),
                            dateEpoch = hoursDay.timeEpoch,
                        )
                    )
                }
            }
        }

        return BoxWeather(
            today = Today(
                city = weatherForecast.location?.city ?: "",
                description = weatherForecast.current?.condition?.conditionText ?: "",
                imageUrl = weatherForecast.current?.condition?.conditionIcon ?: "",
                temperatureC = weatherForecast.current?.temperature?.toInt() ?: 0,
                dateEpoch = weatherForecast.current?.lastUpdatedEpoch ?: 0,
            ),
            byDays = if (listByDays.isEmpty()) null else listByDays,
            byHour = if (hourlyForecast.isEmpty()) null else hourlyForecast,
        )
    }

    private fun toTodayApiFromTodayDomain(today: Today?): WeatherToday? {
        return today?.let {
            WeatherToday(
                city = it.city,
                description = it.description,
                imageUrl = it.imageUrl,
                temperatureC = it.temperatureC,
                dateEpoch = it.dateEpoch.toDate(),
            )
        }
    }
}

private fun Long.toDate(): String {
    val instant = Instant.ofEpochMilli(this * 1000)
    val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
    val formatter = DateTimeFormatter.ofPattern("dd MMM, yyyy HH:mm")
    return localDateTime.format(formatter)
}

private fun Long.toDayWeek(): String {
    val instant = Instant.ofEpochMilli(this * 1000)
    val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
    return localDateTime.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
}