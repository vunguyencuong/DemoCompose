package com.vunguyencuong.democompose

import android.os.Bundle
import android.util.Log
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
import com.vunguyencuong.democompose.navigation.CustomNavType
import com.vunguyencuong.democompose.navigation.NavigationItem
import com.vunguyencuong.democompose.ui.article.Article
import com.vunguyencuong.democompose.ui.article.ArticleDetailRoute
import com.vunguyencuong.democompose.ui.article.ArticleScreen
import com.vunguyencuong.democompose.ui.home.HomeScreen
import com.vunguyencuong.democompose.ui.library.LibraryScreen
import com.vunguyencuong.democompose.ui.setting.SettingScreen
import com.vunguyencuong.democompose.ui.theme.DemoComposeTheme
import kotlinx.serialization.json.Json
import kotlin.reflect.typeOf

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

    val navTypes = mapOf(
        typeOf<Article>() to CustomNavType(Article.serializer())
    )


    Scaffold(
        bottomBar = {
            ControlBottomBar(navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavigationItem.HomeRoute.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(NavigationItem.HomeRoute.route) {
                HomeScreen(
                    onArticleClick = { article ->
                        Log.d("Article", "MainScreen: $article")
                        try {
                            // Test serialize manually
                            val json = Json.encodeToString(Article.serializer(), article)
                            Log.d("Article", "Serialized JSON: $json")
                            navController.navigate(ArticleDetailRoute(article))
                        } catch (e: Exception) {
                            Log.e("Article", "Error serializing: ${e.message}")
                        }
                    }
                )
            }
            composable<ArticleDetailRoute>(
                typeMap = navTypes
            ) { backStackEntry ->
                val route = backStackEntry.toRoute<ArticleDetailRoute>()
                Log.d("Article", "MainScreen: ArticleDetailRoute ${route.article}")
                ArticleScreen(route.article,navController)
            }
            composable(NavigationItem.LibraryRoute.route) {
                LibraryScreen()
            }
            composable(NavigationItem.SettingsRoute.route) {
                SettingScreen()
            }
        }

    }
}

