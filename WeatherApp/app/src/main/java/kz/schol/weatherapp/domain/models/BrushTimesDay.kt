package kz.schol.weatherapp.domain.models

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

enum class BrushTimesDay(val brush: Brush) {
    MORNING(
        Brush.verticalGradient(
            listOf(
                Color(0xFFBC2C35),
                Color(0xFFF1B14B),
            )
        )
    ),
    DAY(
        Brush.verticalGradient(
            listOf(
                Color(0xFF2F2CBC),
                Color(0xFF4BB5F1),
            )
        )
    ),
    NIGHT(
        Brush.verticalGradient(
            listOf(
                Color(0xFF06050E),
                Color(0xFF223076),
            )
        )
    ),
}