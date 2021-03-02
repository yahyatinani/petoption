package com.github.whyrising.androiddevchallenge.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.github.whyrising.androiddevchallenge.R

class MainViewModel : ViewModel() {
    private fun placeholderText(): String {
        return "Lorem ipsum dolor sit amet, consectetur adipiscing elit." +
            " Sed feugiat tellus quis lobortis varius. Quisque in vestibulum " +
            "urna, non dignissim nulla. Quisque ac nulla in justo finibus " +
            "consequat vel non velit. Sed vitae purus arcu. Sed tristique " +
            "elit orci, nec sodales metus cursus non."
    }

    var locationSearch by mutableStateOf("New York, USA")

    val petCategories = listOf(Category.Dog, Category.Cat)

    val pets = listOf(
        PetViewModel(
            id = R.drawable.dog1,
            name = "Name 1",
            age = "9 mth.",
            weight = 6,
            breed = "Breed 1",
            category = Category.Dog,
            gender = Gender.Male,
            about = placeholderText(),
            isLiked = true,
        ),
        PetViewModel(
            id = R.drawable.dog2,
            name = "Name 2",
            age = "9 mth.",
            weight = 6,
            breed = "Breed 2",
            category = Category.Dog,
            gender = Gender.Female,
            about = placeholderText(),
        ),
        PetViewModel(
            id = R.drawable.dog3,
            name = "Name 3",
            age = "9 mth.",
            weight = 6,
            breed = "Breed 3",
            category = Category.Dog,
            gender = Gender.Male,
            about = placeholderText(),
        ),
        PetViewModel(
            id = R.drawable.dog4,
            name = "Name 3",
            age = "9 mth.",
            weight = 6,
            breed = "Breed 3",
            category = Category.Dog,
            gender = Gender.Female,
            about = placeholderText(),
            isLiked = true
        ),
        PetViewModel(
            id = R.drawable.dog5,
            name = "Name 5",
            age = "9 mth.",
            weight = 6,
            breed = "Breed 5",
            category = Category.Dog,
            gender = Gender.Female,
            about = placeholderText(),
        ),
        PetViewModel(
            id = R.drawable.dog6,
            name = "Name 3",
            age = "9 mth.",
            weight = 6,
            breed = "Breed 6",
            category = Category.Dog,
            gender = Gender.Male,
            about = placeholderText(),
        ),
    )
}
