package com.example.act3_fsgg.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

/* Paleta de colores (NO LA PIERDAS)
* 1B3C53
* 456882
* D2C1B6
* F9F3EF
*/

//Modificar colores para el sistema de la aplicación
private val DarkColorScheme = darkColorScheme(
    primary = Azul,
    secondary = CafeLight,
    tertiary = CafeLight2
)

private val LightColorScheme = lightColorScheme(
    primary = Azul,
    secondary = CafeLight,
    tertiary = CafeLight2
)

@Composable
fun Act3_FSGGTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}