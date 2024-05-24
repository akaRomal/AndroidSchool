package kz.schol.weatherapp.presintation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import kz.schol.weatherapp.R
import kz.schol.weatherapp.domain.models.WeatherResponse
import kz.schol.weatherapp.presintation.compponents.WeatherForWeek
import kz.schol.weatherapp.presintation.compponents.WeatherTodayBox


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HomeScreen(weatherResponse: WeatherResponse) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(weatherResponse.background),
//            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (!weatherResponse.isConnection) {
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(id = R.drawable.connection_off),
                contentDescription = "No internet"
            )
        }
        weatherResponse.errorText?.let {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = it,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
        weatherResponse.weatherToday?.let {
            WeatherTodayBox(weatherToday = it)
        }

        weatherResponse.weatherByDay?.let {
            WeatherForWeek(weatherByDay = it)
        }





    }
}