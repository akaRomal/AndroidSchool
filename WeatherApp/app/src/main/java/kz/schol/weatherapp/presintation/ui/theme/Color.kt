package kz.schol.weatherapp.presintation.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)
val onPrimary = Color(0xFFFFFFFF)
val onPrimaryVariant = Color(0xFFA8A8A8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val morningBrush = Brush.verticalGradient(
    listOf(
        Color(0xFFBC2C35),
        Color(0xFFF1B14B),
    )
)

val dayBrush = Brush.verticalGradient(
    listOf(
        Color(0xFF2F2CBC),
        Color(0xFF4BB5F1),
    )
)

val nightBrush = Brush.verticalGradient(
    listOf(
        Color(0xFF06050E),
        Color(0xFF223076),
    )
)