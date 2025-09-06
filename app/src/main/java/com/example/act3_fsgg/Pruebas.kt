package com.example.act3_fsgg

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.act3_fsgg.ui.theme.Act3_FSGGTheme

data class TodoItem(
    val itemDescription: String,
    var isItemDone: Boolean = false
)

@Composable
fun TodoListItem(todoItem: TodoItem, onToggleDone: (TodoItem) -> Unit, onRemove: (TodoItem) -> Unit, modifier: Modifier = Modifier, ) {
    val swipeToDismissBoxState = rememberSwipeToDismissBoxState(
        confirmValueChange = {
            if (it == SwipeToDismissBoxValue.StartToEnd) onToggleDone(todoItem)
            else if (it == SwipeToDismissBoxValue.EndToStart) onRemove(todoItem)
            // Reset item when toggling done status
            it != SwipeToDismissBoxValue.StartToEnd
        }
    )

    SwipeToDismissBox(
        state = swipeToDismissBoxState,
        modifier = modifier.fillMaxSize(),
        backgroundContent = {
            when (swipeToDismissBoxState.dismissDirection) {
                SwipeToDismissBoxValue.StartToEnd -> {
                    Icon(
                        if (todoItem.isItemDone) Icons.Filled.Delete else Icons.Filled.Done,
                        contentDescription = if (todoItem.isItemDone) "Done" else "Not done",
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Blue)
                            .wrapContentSize(Alignment.CenterStart)
                            .padding(12.dp),
                        tint = Color.White
                    )
                }
                SwipeToDismissBoxValue.EndToStart -> {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Remove item",
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Red)
                            .wrapContentSize(Alignment.CenterEnd)
                            .padding(12.dp),
                        tint = Color.White
                    )
                }
                SwipeToDismissBoxValue.Settled -> {}
            }
        }
    ) {
        ListItem(
            headlineContent = { Text(todoItem.itemDescription) },
            supportingContent = { Text("swipe me to update or remove.") }
        )
    }
}

@Composable
private fun SwipeItemExample() {
    val todoItems = remember {
        mutableStateListOf(
            TodoItem("Pay bills"), TodoItem("Buy groceries"),
            TodoItem("Go to gym"), TodoItem("Get dinner")
        )
    }

    LazyColumn {
        items(
            items = todoItems,
            key = { it.itemDescription }
        ) { todoItem ->
            TodoListItem(
                todoItem = todoItem,
                onToggleDone = { todoItem ->
                    todoItem.isItemDone = !todoItem.isItemDone
                },
                onRemove = { todoItem ->
                    todoItems -= todoItem
                },
                modifier = Modifier.animateItem()
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Muestraa() {
    Act3_FSGGTheme {
        SwipeItemExample()
    }
}