package com.vunguyencuong.democompose

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.vunguyencuong.democompose.navigation.NavigationItem

@Composable
fun ControlBottomBar(navController : NavHostController){
    val showBottomBar = remember { mutableStateOf(true) }
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Library,
        NavigationItem.Settings
    )
    // Observe current route to update showBottomBar
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Update showBottomBar based on current route
    LaunchedEffect(currentRoute) {
        showBottomBar.value = when (currentRoute) {
            NavigationItem.Home.route, NavigationItem.Library.route, NavigationItem.Settings.route -> true
            else -> false
        }
    }


    if(showBottomBar.value){
        NavigationBar {
            items.forEach { item ->
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(
                                id = if (currentRoute == item.route) {
                                    item.iconSelected
                                } else {
                                    item.icon
                                }
                            ),
                            contentDescription = item.title
                        )
                    },
                    label = { Text(item.title) },
                    selected = currentRoute == item.route,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}