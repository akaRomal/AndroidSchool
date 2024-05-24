package kz.schol.weatherapp.app

import android.app.Application
import kz.schol.weatherapp.di.dataModule
import kz.schol.weatherapp.di.domainModule
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                dataModule,
                domainModule,
            )
        }
    }
}