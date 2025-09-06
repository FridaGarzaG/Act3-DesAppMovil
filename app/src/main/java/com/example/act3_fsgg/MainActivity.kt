package com.example.act3_fsgg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.act3_fsgg.ui.theme.Act3_FSGGTheme
import androidx.navigation.NavHostController
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
                AppNavWrapper(navController = navController)
            }
        }
    }
}

//Esto se realiza para que se pueda hacer uso del HilViewModel, porque solo funciona dentro de un Composable
@Composable
fun AppNavWrapper(navController: NavHostController) {
    val viewModel:  CardViewModel = hiltViewModel()
    AppNav(navController = navController, viewModel = viewModel)
}
