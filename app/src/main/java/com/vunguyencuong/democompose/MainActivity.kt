package com.vunguyencuong.democompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vunguyencuong.democompose.navigation.NavigationItem
import com.vunguyencuong.democompose.ui.ArticleScreen
import com.vunguyencuong.democompose.ui.home.HomeScreen
import com.vunguyencuong.democompose.ui.library.LibraryScreen
import com.vunguyencuong.democompose.ui.setting.SettingScreen
import com.vunguyencuong.democompose.ui.theme.DemoComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DemoComposeTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen(){
    val showBottomBar = remember { mutableStateOf(true) }
    val navController = rememberNavController()
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Library,
        NavigationItem.Settings
    )
    // Observe current route to update showBottomBar
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Update showBottomBar based on current route
    showBottomBar.value = when (currentRoute) {
        NavigationItem.Home.route,
        NavigationItem.Library.route,
        NavigationItem.Settings.route -> true
        else -> false
    }

    Scaffold(
        bottomBar = {
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
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavigationItem.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(NavigationItem.Home.route) {
                HomeScreen(
                    onHomeClick = {
                        navController.navigate(NavigationItem.Article.createRoute(it))
                    }
            )
            }
            composable(
                NavigationItem.Article.route,
                arguments = listOf(
                    navArgument("text"){type = NavType.StringType}
                )
            ){
                val text=  it.arguments?.getString("text") ?: "No text provided"
                ArticleScreen(text)
            }
            composable(NavigationItem.Library.route) { LibraryScreen() }
            composable(NavigationItem.Settings.route) { SettingScreen() }
        }

    }
}

