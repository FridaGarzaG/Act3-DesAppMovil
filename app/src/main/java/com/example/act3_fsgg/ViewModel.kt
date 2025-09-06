package com.example.act3_fsgg

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(): ViewModel(){
    data class Tareas(val id:Int, val titulo:String, val fechaEntrega:String, val prioridad:String, val descripcion: String)

    val tareas = mutableStateListOf<Tareas>()

//    init{
//        tareas.addAll(
//            listOf(
//                Tareas(0,"Actividad 1","12/11/25","Alta", "Realizar varios..."),
//                Tareas(1,"Actividad 2", "12/11/25","Baja","Realizar varios..."),
//                Tareas(2,"Actividad","12/11/25", "Alta","Realizar varios..."),
//                Tareas(3,"Actividad", "12/11/25", "Media","Realizar varios..."),
//                Tareas(4,"Actividad", "12/11/25","Alta","Realizar varios..."),
//                Tareas(5,"Actividad", "12/11/25", "Media","Realizar varios...")
//            )
//        )
//    }

    //Se encarga de agregar la nueva tarea a la lista mutable
    fun addTarea(Tarea: Tareas){
        tareas.add(Tarea)
    }

    //Se encarga de remover la tarea base deslizamiento
    fun removerTarea(IdTarea: Int): Boolean{
        val tarea = tareas.find { it.id == IdTarea }
        return if (tarea != null) {
            tareas.remove(tarea)
        } else false
    }

}
