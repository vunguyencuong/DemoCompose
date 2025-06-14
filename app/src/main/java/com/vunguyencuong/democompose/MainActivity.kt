package com.vunguyencuong.democompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.vunguyencuong.democompose.navigation.NavigationItem
import com.vunguyencuong.democompose.ui.article.Article
import com.vunguyencuong.democompose.ui.article.ArticleScreen
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
fun MainScreen() {

    val navController = rememberNavController()


    Scaffold(
        bottomBar = {
            ControlBottomBar(navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavigationItem.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(NavigationItem.Home.route) {
                HomeScreen(
                    onHomeClick = { text ->
                        navController.navigate(Article(text))
                    }
                )
            }
            composable<Article> { backStackEntry ->
                val article: Article = backStackEntry.toRoute()
                ArticleScreen(article.text)
            }
            composable(NavigationItem.Library.route) {
                LibraryScreen()
            }
            composable(NavigationItem.Settings.route) {
                SettingScreen()
            }
        }

    }
}

