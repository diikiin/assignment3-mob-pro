package com.dikin.assignment3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.dikin.assignment3.lazycolumn.TaskListScreen
import com.dikin.assignment3.ui.theme.Assignment3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assignment3Theme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Composable",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                    ComposableCommunication(Modifier.padding(innerPadding))
//                    ComposableNavigation(Modifier.padding(innerPadding))
//                }
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TaskListScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
