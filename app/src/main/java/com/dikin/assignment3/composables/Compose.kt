package com.dikin.assignment3.composables

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.dikin.assignment3.ui.theme.Assignment3Theme

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