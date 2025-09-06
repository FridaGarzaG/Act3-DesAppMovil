package com.example.act3_fsgg.navagation

import  androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.act3_fsgg.CTareas
import com.example.act3_fsgg.CardViewModel
import com.example.act3_fsgg.Listado

@Composable
fun AppNav(navController: NavHostController, viewModel: CardViewModel){
    NavHost(
        navController = navController,
        startDestination = "pantalla1"
    ){
        composable("pantalla1"){ Listado(navController = navController, viewModel = viewModel) }
        composable("pantalla2"){ CTareas(navController, viewModel) }
    }
}

