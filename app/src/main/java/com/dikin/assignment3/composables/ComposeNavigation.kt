package com.dikin.assignment3.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dikin.assignment3.ui.theme.Assignment3Theme

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
