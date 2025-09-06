package com.example.act3_fsgg

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.act3_fsgg.ui.theme.Act3_FSGGTheme
import com.example.act3_fsgg.ui.theme.AzuLight
import com.example.act3_fsgg.ui.theme.Azul

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CTareas(navController: NavController, viewModel: CardViewModel) {
    var titulo by rememberSaveable { mutableStateOf("") }
    var fechaEntrega by rememberSaveable { mutableStateOf("") }
    var prioridad by rememberSaveable { mutableStateOf("") }
    var descripcion by rememberSaveable { mutableStateOf("") }

    var tituloError by rememberSaveable { mutableStateOf("Ingrese un titulo") }
    var fechaError by rememberSaveable { mutableStateOf("Formato DD/MM/AA") }
    var descripcionError by rememberSaveable { mutableStateOf("Ingrese una descripción") }
    var prioridadError by rememberSaveable { mutableStateOf("Formato tipo Alta / Media / Baja") }
    val context = LocalContext.current

    //Funciona para poder darle formato al texto que ingrese en el campo de fecha,
    //donde se valida que tenga dos digitos, despues la barra, otra vez dos digitos, barra y dos digitos
    val regexFecha = Regex("""\d{2}/\d{2}/\d{2}""")

    Scaffold (
        modifier = Modifier.fillMaxWidth(),
        //Barra de arriba donde debe especificar que hace esta pantalla y el icono para regresar al listado de tareas
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {navController.navigate("pantalla1")}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                title = {
                    Text("Crear tareas", modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Right,
                        style =
                            TextStyle(
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold
                            ),
                        color =MaterialTheme.colorScheme.primary
                    )
                }
            )
        },
    ){ innerPadding ->
        //Aqui se manejan los input para crear una nueva tarea en el listado
        Column(
            modifier = Modifier.padding(innerPadding).fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Text("Titulo: ",
                    style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 17.sp),
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.fillMaxWidth().padding(top = 10.dp)
                )
                OutlinedTextField(
                    value = titulo,
                    onValueChange = {
                        titulo = it
                        if (it.isNotEmpty()) tituloError = ""
                                    },
                    singleLine = true,
                    label = { Text("Ingrese titulo de la actividad", color =Color(0xFF8F8686), fontWeight = FontWeight.Medium) },
                    isError = tituloError.isNotEmpty(),
                    modifier = Modifier.fillMaxWidth().padding(bottom = 5.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Azul,
                        unfocusedBorderColor = AzuLight
                    )
                )
                if (tituloError.isNotEmpty()) {
                    Text(
                        text = tituloError,
                        color = MaterialTheme.colorScheme.error,
                        style = TextStyle(fontSize = 12.sp)
                    )
                }

                Text("Fecha entregable: ",
                    style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 17.sp),
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.fillMaxWidth().padding(top = 25.dp)
                )
                OutlinedTextField(
                    value = fechaEntrega,
                    onValueChange = {
                        fechaEntrega = it
                        if (it.isNotEmpty()) fechaError = ""
                                    },
                    singleLine = true,
                    label = { Text("ej. 12/03/25", color =Color(0xFF8F8686), fontWeight = FontWeight.Medium) },
                    isError = fechaError.isNotEmpty(),
                    modifier = Modifier.fillMaxWidth().padding(bottom = 5.dp)
                )
                if (fechaError.isNotEmpty()) {
                    Text(
                        text = fechaError,
                        color = MaterialTheme.colorScheme.error,
                        style = TextStyle(fontSize = 12.sp)
                    )
                }

                Text("Prioridad: ",
                    style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 17.sp),
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.fillMaxWidth().padding(top = 25.dp)
                )
                OutlinedTextField(
                    value = prioridad,
                    onValueChange = {
                        prioridad = it
                        if (it.isNotEmpty()) prioridadError = ""},
                    singleLine = true,
                    label = { Text("ej. Alta / Media / Baja", color = Color(0xFF8F8686), fontWeight = FontWeight.Medium) },
                    isError = prioridadError.isNotEmpty(),
                    modifier = Modifier.fillMaxWidth().padding(bottom = 5.dp)
                )
                if (prioridadError.isNotEmpty()) {
                    Text(
                        text = prioridadError,
                        color = MaterialTheme.colorScheme.error,
                        style = TextStyle(fontSize = 12.sp)
                    )
                }

                Text("Descripción: ",
                    style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 17.sp),
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.fillMaxWidth().padding(top = 25.dp)
                )
                OutlinedTextField(
                    value = descripcion,
                    onValueChange = { descripcion = it
                        if (it.isNotEmpty()) descripcionError = ""
                                    },
                    label = { Text("Ingrese descripción de la actividad", color =Color(0xFF8F8686), fontWeight = FontWeight.Medium) },
                    isError = descripcionError.isNotEmpty(),
                    modifier = Modifier.fillMaxWidth().padding(bottom = 5.dp)
                )
                if (descripcionError.isNotEmpty()) {
                    Text(
                        text = descripcionError,
                        color = MaterialTheme.colorScheme.error,
                        style = TextStyle(fontSize = 12.sp)
                    )
                }
                Spacer(modifier = Modifier.padding(bottom = 12.dp))

                Button(
                    onClick = {
                        //FALTA MANEJO DE VALIDACIONES EN TIEMPO REAL, EL DE FECHA, SOBRE QUE SEA FUTURA
                        if (titulo.isNotEmpty() && fechaEntrega.isNotEmpty() && prioridad.isNotEmpty() && descripcion.isNotEmpty()) {
                            if (regexFecha.matches(fechaEntrega)) {
                                if (prioridad == "Alta" || prioridad == "Media" || prioridad == "Baja") {
                                    //Se crea y agrega una tarea con todos los datos que ingreso el usuario
                                    viewModel.addTarea(
                                        CardViewModel.Tareas(
                                            id = viewModel.tareas.size,
                                            titulo = titulo,
                                            fechaEntrega = fechaEntrega,
                                            prioridad = prioridad,
                                            descripcion = descripcion
                                        )
                                    )
                                    titulo = ""
                                    fechaEntrega = ""
                                    prioridad = ""
                                    descripcion = ""
                                    navController.navigate("Pantalla1")
                                } else {
                                    Toast.makeText(context, "En Prioridad solo puede ser Alta/Media/Baja", Toast.LENGTH_SHORT).show()
                                }
                            }else{
                                Toast.makeText(context, "Formato de Fecha incorrecto", Toast.LENGTH_SHORT).show()
                            }
                        }else {
                            Toast.makeText(context, "Faltan campos por rellenar", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier.fillMaxWidth().padding(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF507BA2)
                    )
                ) {
                    Text("Agregar tarea",
                        style = TextStyle(
                            fontSize = 18.sp, fontWeight = FontWeight.Medium,
                            fontFamily = FontFamily.SansSerif
                        )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun muestra() {
    Act3_FSGGTheme {
        val navController = rememberNavController()
        val ViewModel = remember { CardViewModel() }
        CTareas(viewModel = ViewModel, navController = navController)
    }
}