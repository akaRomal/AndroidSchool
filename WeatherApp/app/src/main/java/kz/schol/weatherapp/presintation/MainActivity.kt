package kz.schol.weatherapp.presintation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.runBlocking
import kz.schol.weatherapp.domain.interfaces.WeatherRepository
import kz.schol.weatherapp.domain.models.WeatherResponse
import kz.schol.weatherapp.presintation.ui.screens.HomeScreen
import kz.schol.weatherapp.presintation.ui.theme.WeatherAppTheme
import org.koin.android.ext.android.inject


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository: WeatherRepository by inject()
        val weatherResponse: WeatherResponse

        runBlocking {

            weatherResponse = repository.getWeather()
        }

        setContent {
            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .widthIn(min = 180.dp, max = 450.dp)
                        .fillMaxHeight(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen(weatherResponse = weatherResponse)
                }
            }
        }
    }
}