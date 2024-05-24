package kz.schol.weatherapp.di

import kz.schol.weatherapp.data.reposytory.WeatherRepositoryImpl
import kz.schol.weatherapp.domain.interfaces.WeatherRepository
import org.koin.dsl.module

val domainModule = module{

    single<WeatherRepository> { WeatherRepositoryImpl(api = get(), mapper = get()) }

}