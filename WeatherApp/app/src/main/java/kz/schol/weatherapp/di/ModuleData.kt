package kz.schol.weatherapp.di

import kz.schol.weatherapp.BuildConfig
import kz.schol.weatherapp.data.mapper.Mapper
import kz.schol.weatherapp.data.remote.api.WeatherApiService
import kz.schol.weatherapp.domain.interfaces.WeatherRepository
import kz.schol.weatherapp.data.reposytory.WeatherRepositoryImpl
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val dataModule = module {

    single<WeatherApiService> { get<Retrofit>().create(WeatherApiService::class.java) }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.ULR_API)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .readTimeout(30000, TimeUnit.MILLISECONDS)
            .build()
    }

    single<Mapper> { Mapper() }
}