package kz.schol.weatherapp.data.reposytory

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kz.schol.weatherapp.data.mapper.Mapper
import kz.schol.weatherapp.data.remote.api.WeatherApiService
import kz.schol.weatherapp.data.reposytory.models.ApiResponse
import kz.schol.weatherapp.domain.interfaces.WeatherRepository
import kz.schol.weatherapp.domain.models.WeatherResponse


private const val MESSAGE_ERROR_CONNECTION ="Ошибка соединения с сервером."
class WeatherRepositoryImpl(
    private val api: WeatherApiService,
    private val mapper: Mapper
) : WeatherRepository {
    override suspend fun getWeather(): WeatherResponse {
        return withContext(Dispatchers.IO) {
            with(mapper) {
                toDomainFromApi(getWeatherFromApi())
            }
        }
    }

    private suspend fun getWeatherFromApi(): ApiResponse {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getWeatherForecast().execute()
                if (response.isSuccessful) {
                    println(response.body())
                    ApiResponse(
                        boxWeather =
                        with(mapper) {
                            toWeatherFromApi(response.body()!!)
                        }
                    )
                } else {
                    ApiResponse(
                        errorText = MESSAGE_ERROR_CONNECTION,
                    )
                }
            } catch (e: Exception) {
                ApiResponse(isConnection = false)
            }
        }
    }

}