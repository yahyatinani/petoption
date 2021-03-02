package com.github.whyrising.androiddevchallenge.view

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.github.whyrising.androiddevchallenge.theme.MyTheme

@Composable
fun PetDetails() {
    Scaffold {

    }
}

/**
 *
 * Previews
 *
 **/

@Composable
@Preview
fun PetDetailsLightPreview() {
    MyTheme {
        PetDetails()
    }
}

@Composable
@Preview
fun PetDetailsDarkPreview() {
    MyTheme(isDarkTheme = true) {
        PetDetails()
    }
}