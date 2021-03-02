package com.github.whyrising.androiddevchallenge.viewmodels

enum class Gender {
    Male,
    Female
}

enum class Category {
    Dog,
    Cat,
    Bird,
}

data class PetViewModel(
    val id: Int = -1,
    val name: String,
    val age: String,
    val weight: Int,
    val breed: String,
    val category: Category,
    val gender: Gender,
    val about: String,
    val isLiked: Boolean = false,
)
