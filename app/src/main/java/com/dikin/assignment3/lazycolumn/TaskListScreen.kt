package com.dikin.assignment3.lazycolumn

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dikin.assignment3.ui.theme.Assignment3Theme

@Composable
fun TaskListScreen(modifier: Modifier = Modifier) {
    var id = 0
    val tasks = mutableListOf(
        Task(++id, "Create a compose", "Creating a composable function"),
        Task(++id, "Create a project", "Creating a Android Studio project", _isDone = true),
        Task(++id, "Create a LazyColumn", "Creating a compose with LazyColumn"),
        Task(
            ++id,
            "Create a data class",
            "Creating a Task data class for LazyColumn",
            _isDone = true
        ),
    )

    Column(
        modifier = modifier
    ) {
        Text(
            text = "Tasks List",
            fontSize = 32.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn {
            items(tasks) { task ->
                TaskItem(task)
            }
        }
    }
}

@Composable
fun TaskItem(task: Task) {
    var showDialog by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.Gray),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { showDialog = true }
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = task.title,
                fontSize = 20.sp,
                color = Color.Black,
            )
        }
    }

    if (showDialog) {
        TaskDetailDialog(task = task, onDismiss = { showDialog = false })
    }
}

@Composable
fun TaskDetailDialog(task: Task, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = "Task Details") },
        text = {
            Column {
                Text(text = "Title: ${task.title}", fontSize = 20.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Description: ${task.description}", fontSize = 16.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Completed: ${if (task.isDone) "Yes" else "No"}", fontSize = 16.sp)
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("Close")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun TaskListScreenPreview() {
    Assignment3Theme {
        TaskListScreen()
    }
}
