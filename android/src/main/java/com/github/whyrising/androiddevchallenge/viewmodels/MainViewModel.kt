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
package com.github.whyrising.androiddevchallenge.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.github.whyrising.androiddevchallenge.R

class MainViewModel : ViewModel() {
    var isDarkTheme by mutableStateOf(false)
        private set

    var selectedCategory by mutableStateOf(Category.Dog)

    var locationSearch by mutableStateOf("New York, USA")

    var petSearch by mutableStateOf("")

    val petCategories = listOf(Category.Dog, Category.Cat)

    val pets = listOf(
        PetViewModel(
            id = R.drawable.dog1,
            name = "Charlie",
            age = "9 mth.",
            weight = 6,
            breed = "Breed",
            category = Category.Dog,
            gender = Gender.Male,
            about = placeholderText(),
            isLiked = true,
        ),
        PetViewModel(
            id = R.drawable.dog2,
            name = "Sofi",
            age = "9 mth.",
            weight = 6,
            breed = "Breed",
            category = Category.Dog,
            gender = Gender.Female,
            about = placeholderText(),
        ),
        PetViewModel(
            id = R.drawable.dog3,
            name = "Henry",
            age = "9 mth.",
            weight = 6,
            breed = "Breed",
            category = Category.Dog,
            gender = Gender.Male,
            about = placeholderText(),
        ),
        PetViewModel(
            id = R.drawable.dog4,
            name = "Mocha",
            age = "9 mth.",
            weight = 6,
            breed = "Breed",
            category = Category.Dog,
            gender = Gender.Female,
            about = placeholderText(),
            isLiked = true
        ),
        PetViewModel(
            id = R.drawable.dog5,
            name = "Molly",
            age = "9 mth.",
            weight = 6,
            breed = "Breed",
            category = Category.Dog,
            gender = Gender.Female,
            about = placeholderText(),
        ),
        PetViewModel(
            id = R.drawable.dog6,
            name = "Teddy",
            age = "9 mth.",
            weight = 6,
            breed = "Breed",
            category = Category.Dog,
            gender = Gender.Male,
            about = placeholderText(),
        ),
    )

    private fun darkThemeOff() {
        isDarkTheme = false
    }

    private fun darkThemeOn() {
        isDarkTheme = true
    }

    fun toggleLightDarkTheme() = when {
        isDarkTheme -> darkThemeOff()
        else -> darkThemeOn()
    }

    companion object {
        fun placeholderText(): String {
            return "Lorem ipsum dolor sit amet, consectetur adipiscing elit." +
                " Sed feugiat tellus quis lobortis varius. Quisque in vestibulum " +
                "urna, non dignissim nulla. Quisque ac nulla in justo finibus " +
                "consequat vel non velit. Sed vitae purus arcu. Sed tristique " +
                "elit orci, nec sodales metus cursus non."
        }
    }
}
