package com.timmytruong.calendar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.timmytruong.calendar.ui.theme.CalendarTheme
import kotlinx.coroutines.flow.collect

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
        val context = LocalContext.current
        val navController = rememberNavController()
        var canPop by remember { mutableStateOf(false) }
        var title by remember { mutableStateOf(SampleScreen.HOME.resolveTitle(context)) }

        LaunchedEffect(navController) {
            navController.currentBackStackEntryFlow.collect { entry ->
                val route = entry.destination.route ?: SampleScreen.HOME.name
                canPop = route != SampleScreen.HOME.name
                title = SampleScreen.valueOf(route).resolveTitle(context)
            }
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    backgroundColor = MaterialTheme.colors.primarySurface,
                    title = {
                        Text(
                            text =  title,
                            color = MaterialTheme.colors.primary
                        )
                    },
                    navigationIcon = {
                        if (canPop) {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(
                                    Icons.Filled.ArrowBack,
                                    contentDescription = "backIcon",
                                    tint = MaterialTheme.colors.secondary
                                )
                            }
                        } else {
                            Icon(
                                Icons.Filled.Home,
                                contentDescription = "homeIcon",
                                modifier = Modifier.padding(12.dp),
                                tint = MaterialTheme.colors.secondary
                            )
                        }
                    }
                )
            },
            content = {
                Surface(color = MaterialTheme.colors.background) {
                    NavHost(navController = navController, startDestination = SampleScreen.HOME.name) {
                        SampleScreen.values().forEach { screen ->
                            composable(screen.name) {
                                screen.body(navController)
                            }
                        }
                    }
                }
            }
        )
    }
}
