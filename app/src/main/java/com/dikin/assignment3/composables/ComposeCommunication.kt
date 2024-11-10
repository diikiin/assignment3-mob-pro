package com.dikin.assignment3.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dikin.assignment3.ui.theme.Assignment3Theme

// ------------Exercise 2------------------

@Composable
fun ComposableCommunication(modifier: Modifier = Modifier) {
    val sharedViewModel = SharedViewModel()
    Column(modifier = modifier) {
        InputComposable(sharedViewModel)
        OutputComposable(sharedViewModel)
    }
}

@Composable
fun InputComposable(sharedViewModel: SharedViewModel) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
            sharedViewModel.inputText.value = it
        },
        label = { Text("Enter text") },
        modifier = Modifier.padding(20.dp)
    )
}

@Composable
fun OutputComposable(sharedViewModel: SharedViewModel) {
    val text by sharedViewModel.inputText.collectAsState()

    Text(
        text = text,
        fontSize = 20.sp,
        modifier = Modifier.padding(20.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun ComposableCommunicationPreview() {
    Assignment3Theme {
        ComposableCommunication()
    }
}

// ------------Exercise 2 END------------------