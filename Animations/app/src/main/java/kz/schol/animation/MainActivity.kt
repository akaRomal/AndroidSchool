package kz.schol.animation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kz.schol.animation.ui.theme.AnimationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimationsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Animated()
                }
            }
        }
    }
}

@Composable
fun Animated() {
    var isClick by remember {
        mutableStateOf(false)
    }
    var maxLine by remember {
        mutableIntStateOf(1)
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .clickable {
            isClick = !isClick
            if (isClick) {
                maxLine = 3
            } else {
                maxLine = 1
            }
        }
    ) {
        Text(
            modifier = Modifier.animateContentSize(),
            text = "Рыбатекст используется дизайнерами, проектировщиками и фронтендерами, когда нужно быстро заполнить макеты или прототипы содержимым. Это тестовый контент, который не должен нести никакого смысла, лишь показать наличие самого текста или продемонстрировать типографику в деле",
            maxLines = maxLine
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AnimationsTheme {
    }
}