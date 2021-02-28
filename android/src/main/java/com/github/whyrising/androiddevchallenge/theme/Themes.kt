/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.whyrising.androiddevchallenge.theme

import android.view.Window
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

private val LightColors = lightColors(
    primary = Purple200,
    primaryVariant = Purple300,
    onPrimary = Color.White,
//    secondary = Blue700,
//    secondaryVariant = Blue900,
//    onSecondary = Color.White,
    error = Red800
)

private val DarkColors = darkColors(
    primary = Purple100,
    primaryVariant = Blue700,
    onPrimary = Color.Black,
//    secondary = Blue300,
//    onSecondary = Color.White,
    error = Red200
)

private fun getAppropriateColors(darkTheme: Boolean) = when {
    darkTheme -> DarkColors
    else -> LightColors
}

@Composable
fun MyTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    windows: Window? = null,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = getAppropriateColors(isDarkTheme),
        typography = TemplateTypography,
        shapes = MaterialTheme.shapes
    ) {
        windows?.statusBarColor = MaterialTheme.colors.primaryVariant.toArgb()
        content()
    }
}
