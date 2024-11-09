package com.dikin.assignment3

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dikin.assignment3.ui.theme.Assignment3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assignment3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Composable",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                    ComposableCommunication(Modifier.padding(innerPadding))
                    ComposableNavigation(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

// ------------Exercise 1------------------

@Composable
fun ComposableLifecycle(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onEvent: (LifecycleOwner, Lifecycle.Event) -> Unit
) {
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { source, event ->
            onEvent(source, event)
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val tag = "Composable LifeCycle"
    ComposableLifecycle { _, event ->
        when (event) {
            Lifecycle.Event.ON_CREATE -> Log.d(tag, "onCreate")
            Lifecycle.Event.ON_START -> Log.d(tag, "On Start")
            Lifecycle.Event.ON_RESUME -> Log.d(tag, "On Resume")
            Lifecycle.Event.ON_PAUSE -> Log.d(tag, "On Pause")
            Lifecycle.Event.ON_STOP -> Log.d(tag, "On Stop")
            Lifecycle.Event.ON_DESTROY -> Log.d(tag, "On Destroy")
            else -> {}
        }
    }
    Text(
        text = "Hello from $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Assignment3Theme {
        Greeting("Android")
    }
}

// ------------Exercise 1 END------------------

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

// ------------Exercise 3------------------
@Composable
fun ComposableNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController,
        startDestination = "firstScreen",
        modifier = modifier
    ) {
        composable("firstScreen") { FirstScreen(navController) }
        composable("secondScreen") { SecondScreen(navController) }
    }
}

@Composable
fun FirstScreen(navController: NavHostController) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "First Screen",
            fontSize = 24.sp,
            modifier = Modifier.padding(20.dp)
        )
        Button(onClick = { navController.navigate("secondScreen") }) {
            Text("Second Screen")
        }
    }
}

@Composable
fun SecondScreen(navController: NavHostController) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Second Screen",
            fontSize = 24.sp,
            modifier = Modifier.padding(20.dp)
        )
        Button(onClick = { navController.navigate("firstScreen") }) {
            Text("First Screen")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ComposableNavigationPreview() {
    Assignment3Theme {
        ComposableNavigation()
    }
}

// ------------Exercise 3 END------------------
