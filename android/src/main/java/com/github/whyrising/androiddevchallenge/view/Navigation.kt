package com.github.whyrising.androiddevchallenge.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.github.whyrising.androiddevchallenge.viewmodels.MainViewModel

const val petList = "PetsList"
const val petDetails = "PetDetails"

@ExperimentalFoundationApi
@Composable
fun ComposeNavigation(mainViewModel: MainViewModel) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = petList) {
        composable(petList) {
            MyApp(mainViewModel, navController)
        }
        composable(
            route = "$petDetails/{id}/{name}/{breed}/{gender}/{liked}",
            arguments = listOf(
                navArgument("id") { type = NavType.IntType },
                navArgument("gender") { type = NavType.IntType },
                navArgument("liked") { type = NavType.BoolType },
            )
        ) {
            val map = mapOf(
                "id" to it.arguments?.getInt("id")!!,
                "name" to it.arguments?.getString("name")!!,
                "breed" to it.arguments?.getString("breed")!!,
                "gender" to it.arguments?.getInt("gender")!!,
                "liked" to it.arguments?.getBoolean("liked")!!,
            )
            PetDetails(navController, map, mainViewModel)
        }
    }
}
