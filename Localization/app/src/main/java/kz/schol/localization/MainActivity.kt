package kz.schol.localization

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kz.schol.localization.ui.theme.LocalizationTheme
import java.util.Locale

private const val LOCAL_APP = "lang_setting"
private const val LOCAL_LANGUAGE = "language"

class MainActivity : ComponentActivity() {

    private var settings: SharedPreferences? = null

    override fun attachBaseContext(base: Context) {
        settings = base.getSharedPreferences(LOCAL_APP, Context.MODE_PRIVATE)
        val context = if (!loadLanguage().isNullOrEmpty()) {
            setLocale(context = base)
        } else {
            base
        }
        super.attachBaseContext(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LocalizationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScreenOne(
                        activated = loadLanguage() ?: getDeviceLocale()
                    ) {
                        saveLanguage(
                            language = it
                        )
                        recreate()
                    }
                }
            }
        }
    }

    private fun setLocale(context: Context): Context {
        val locale = Locale(loadLanguage() ?: LocaleApp.ENGLISH.locale)
        Locale.setDefault(locale)
        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)
        return context.createConfigurationContext(config)
    }

    private fun loadLanguage(): String? {
        return settings?.getString(LOCAL_LANGUAGE, null)
    }

    private fun saveLanguage(language: String) {
        settings?.edit()?.apply {
            putString(LOCAL_LANGUAGE, language)
            apply()
        }
    }

    private fun getDeviceLocale(): String {
        val currentLocale: Locale = resources.configuration.locales[0]
        return when (currentLocale.language) {
            "en" -> LocaleApp.ENGLISH.locale
            "ru" -> LocaleApp.RUSSIAN.locale
            else -> LocaleApp.ENGLISH.locale
        }
    }
}

@Composable
fun ScreenOne(
    activated: String,
    saveLocale: (String) -> Unit
) {
    val selectedOption = remember {
        mutableStateOf(activated)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Spacer(modifier = Modifier.height(92.dp))
        Text(
            text = stringResource(id = R.string.text_title_start_screen),
            fontSize = 34.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.W600,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
        )
        Spacer(modifier = Modifier.height(343.dp))
        Row {
            RadioButton(
                selected = selectedOption.value == LocaleApp.ENGLISH.locale,
                onClick = {
                    selectedOption.value = LocaleApp.ENGLISH.locale
                    saveLocale(selectedOption.value)
                })
            Text(
                text = stringResource(id = R.string.lang_text_eng),
                color = Color.White
            )
        }
        Row {
            RadioButton(
                selected = selectedOption.value == LocaleApp.RUSSIAN.locale,
                onClick = {
                    selectedOption.value = LocaleApp.RUSSIAN.locale
                    saveLocale(selectedOption.value)
                })

            Text(
                text = stringResource(id = R.string.lang_text_ru),
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LocalizationTheme {
        ScreenOne(
            activated = "en",
            saveLocale = {}
        )
    }
}