package com.example.act3_fsgg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.act3_fsgg.ui.theme.Act3_FSGGTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.act3_fsgg.navagation.AppNav


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Act3_FSGGTheme {
                //Se usa el AppNav para dar muestra de PPrincipal, donde se usa la funcion Listado y
                //da comienzo a la APP
                val navController = rememberNavController()
                val viewModel: CardViewModel = viewModel()
                AppNav(navController = navController, viewModel = viewModel)
            }
        }
    }
}