package com.timmytruong.calendar.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@ExperimentalFoundationApi
@Composable
internal fun Home(navController: NavController) {
    val context = LocalContext.current
    val screens = remember { ScreenHandler.values().toList().filterNot { it == ScreenHandler.HOME } }
    LazyColumn(Modifier.fillMaxWidth(), content = {
        items(screens) { item ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate(item.name) },
            ) {
                Icon(
                    Icons.Filled.DateRange,
                    contentDescription = item.name,
                    tint = MaterialTheme.colors.secondary,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
                Text(
                    text = item.resolveTitle(context),
                    modifier = Modifier.padding(all = 8.dp),
                    fontSize = 20.sp,
                    color = MaterialTheme.colors.primary
                )
            }
        }
    })
}
