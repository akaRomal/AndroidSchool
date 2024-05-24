package kz.schol.weatherapp.presintation.compponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import kz.schol.weatherapp.R
import kz.schol.weatherapp.domain.models.WeatherByDay
import kz.schol.weatherapp.domain.models.WeatherToday

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ImageLoad(imageUrl: String, modifier: Modifier = Modifier, size: Dp = 30.dp) {
    GlideImage(
        modifier = modifier.size(size),
        model = "https:${imageUrl}",
        contentDescription = null,
        failure = placeholder(painterResource(id = R.drawable.ic_launcher_foreground)),
        loading = placeholder {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.width(32.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            }
        }
    )
}

@Composable
fun WeatherTodayBox(weatherToday: WeatherToday) {

    Spacer(modifier = Modifier.height(38.dp))
    Text(
        text = weatherToday.city,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onPrimary,
    )

    Spacer(modifier = Modifier.height(36.dp))
    Text(
        text = weatherToday.description,
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.onPrimary,
    )

    Spacer(modifier = Modifier.height(24.dp))
    ImageLoad(imageUrl = weatherToday.imageUrl, size = 100.dp)

    Spacer(modifier = Modifier.height(12.dp))
    Text(
        text = weatherToday.temperatureC.toString(),
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onPrimary,
    )

    Spacer(modifier = Modifier.height(24.dp))
    Text(
        text = weatherToday.dateEpoch,
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.onPrimary,
    )
}

@Composable
fun WeatherForWeek(weatherByDay: List<WeatherByDay>) {
    Spacer(modifier = Modifier.height(50.dp))
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(13.dp)
    ) {

        items(weatherByDay){
            Divider(color = MaterialTheme.colorScheme.onPrimaryContainer)
            Spacer(modifier = Modifier.height(13.dp))
            Row(
            ) {

                Text(
                    text = it.dateEpoch,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.weight(50f)
                )

                ImageLoad(imageUrl = it.imageUlr,
                    modifier = Modifier.weight(15f))

                Text(
                    text = it.timeMaxC.toString(),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.weight(15f)
                )

                Text(
                    text = it.timeMinC.toString(),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.weight(15f)
                )
            }
        }
        item {
            Divider(color = MaterialTheme.colorScheme.onPrimaryContainer)
            Spacer(modifier = Modifier.height(26.dp))
        }
    }
}