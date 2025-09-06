package com.example.act3_fsgg

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.act3_fsgg.ui.theme.Act3_FSGGTheme
import com.example.act3_fsgg.ui.theme.Azul
import com.example.act3_fsgg.ui.theme.CafeLight
import kotlin.collections.set


//Encargada de hacer uso de un LazyColumn las tareas en Card con su información, en donde ya manejamos la
//expansion y eliminación por desplazamiento
@Composable
fun MuestraTareas(TviewModel: CardViewModel,  remover: (CardViewModel.Tareas) -> Unit, modifier: Modifier = Modifier){
    val tareas = TviewModel.tareas
    //Para cada Card que sea expandida
    val expandirTarea = remember { mutableStateMapOf<Int, Boolean>() }

    LazyColumn (
        modifier = Modifier.fillMaxSize().padding(5.dp)
    ) {
        items(tareas) { tarea ->
            //Estado del componente que se desliza
            val swipeState = rememberSwipeToDismissBoxState(
                //Si el usuario completa el deslizamiento de izquierda a derecha de la tarea
                //se debera eliminar porque llame la funcion de removerTarea
                confirmValueChange = {
                    if (it == SwipeToDismissBoxValue.EndToStart) {
                        remover(tarea)
                    }
                    //Para que no se autoelimine, debe servir para tener control en el deslizamiento
                    false
                }
            )
            //Estado que ayuda en la expansión de cada una de las tareas
            val expandir = expandirTarea[tarea.id] ?: false

            //Ayuda en el deslizamiento de las Card
            SwipeToDismissBox(
                state = swipeState,
                modifier = modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp)),
                backgroundContent = {
                    //Si dezlizamos a la izquierda debera mostrar el estilo que definimos
                    if (swipeState.dismissDirection == SwipeToDismissBoxValue.EndToStart) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Eliminar tarea",
                            modifier = Modifier
                                .fillMaxWidth().fillMaxSize()
                                .background(Color(0xFFCD5656))
                                //.background(AzuLightt2)
                                .wrapContentSize(Alignment.CenterEnd)
                                .padding(12.dp),
                            tint = Color.White
                        )
                    }
                }
            ) {
                Card(
//                    shape = RectangleShape,
                    modifier = Modifier
                        .fillMaxWidth().fillMaxSize()
                        .clickable{ expandirTarea[tarea.id] = !expandir },
                    colors = CardDefaults.cardColors(containerColor = CafeLight),
                ) {
                    Text(
                        tarea.titulo,
                        style = TextStyle(textAlign = TextAlign.Center, fontSize = 18.sp, fontWeight = FontWeight.Medium),
                        modifier = Modifier.fillMaxWidth().padding(top = 10.dp, bottom = 5.dp),
                        color = Color.Black
                    )
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            tarea.prioridad,
                            style = TextStyle(textAlign = TextAlign.Center, fontStyle = FontStyle.Italic, color = Azul,
                                fontWeight = FontWeight.Medium),
                            modifier = Modifier.width(195.dp).fillMaxWidth()
                        )
                        Text(
                            tarea.fechaEntrega,
                            style = TextStyle(textAlign = TextAlign.Center, color = Color(0xFF456882),
                                fontWeight = FontWeight.ExtraBold),
                            modifier = Modifier.width(195.dp).fillMaxWidth()
                        )
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    //Animación para que se expanda la Card y se vea la descripción
                    AnimatedVisibility(visible = expandir) {
                        Text(
                            tarea.descripcion,
                            style = TextStyle(
                                color = Color.Black,
                                fontWeight = FontWeight.SemiBold
                            ),
                            modifier = Modifier.fillMaxWidth().padding(start = 5.dp, end = 5.dp)
                        )
                        Spacer(modifier = Modifier.size(35.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.size(20.dp))
        }
    }
}

//Encargada de dar la barra top que contenga titulo y icono, y que tenga validación de si hay tareas o no en la lista, para ya sea
//mostrarlas o notificar que no hay
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Listado(viewModel: CardViewModel, navController: NavController) {
    val tareas = viewModel.tareas
    Scaffold(
        modifier = Modifier.fillMaxWidth().background(Color(0xFF1A3C57)),
        topBar = {
            TopAppBar(
                title = {
                    Text("Listado de tareas", modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Left,
                        style =
                            TextStyle(
                                fontSize = 25.sp,
                                fontWeight = FontWeight.ExtraBold,
                                fontFamily = FontFamily.Default
                            ),
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                actions = {
                    IconButton(onClick = {navController.navigate("pantalla2")}) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Agregar tarea",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }
    ){ innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
//                .padding(top = 30.dp, bottom = 10.dp, start = 10.dp, end = 10.dp)
                .fillMaxSize()
        ) {

            if (tareas.isEmpty()) {
                Text(
                    text = "No hay tareas registradas",
                    style = TextStyle(fontSize = 25.sp, textAlign = TextAlign.Center, fontWeight = FontWeight.SemiBold, fontStyle = FontStyle.Italic, color = Color.Gray),
                    modifier = Modifier.fillMaxWidth().fillMaxSize().padding(top= 210.dp)
                )
            } else {
//                Spacer(modifier = Modifier.size(20.dp))
                MuestraTareas(
                    TviewModel = viewModel,
                    remover = { tarea -> viewModel.removerTarea(tarea.id) }
                )
            }

        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PPrinciapal() {
    Act3_FSGGTheme {
        val navController = rememberNavController()
        val ViewModel = remember { CardViewModel() }
        Listado(viewModel = ViewModel, navController = navController)
    }
}