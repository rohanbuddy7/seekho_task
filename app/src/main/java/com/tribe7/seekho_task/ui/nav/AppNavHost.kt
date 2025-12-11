package com.tribe7.seekho_task.ui.nav

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tribe7.seekho_task.ui.detail.DetailScreen
import com.tribe7.seekho_task.ui.home.HomeScreen

@Composable
fun AppNavHost (){
    val navController = rememberNavController()
    NavHost(navController, startDestination = "home"){
        composable("home"){
            HomeScreen(
                onNavigate = { navController.navigate(it) },
                onBack = {},
                modifier = Modifier.fillMaxSize()
            )
        }
        composable(
            "detail/{id}",
            arguments = listOf(
                navArgument("id"){ type = NavType.IntType })
        ){entry->
            val id = entry.arguments!!.getInt("id")
            DetailScreen(
                id = id,
                onBack = {navController.popBackStack()},
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}