package com.timmytruong.calendar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.timmytruong.calendar.ui.theme.CalendarTheme

@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalendarApp()
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun CalendarApp() {
    CalendarTheme {
        val navController = rememberNavController()
        Surface(color = MaterialTheme.colors.background) {
            NavHost(navController = navController, startDestination = SampleScreen.HOME.name) {
                SampleScreen.values().forEach { screen ->
                    composable(screen.name) { screen.body(navController) }
                }
            }
        }
    }
}
