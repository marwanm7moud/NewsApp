package com.awesome.newsapp.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.awesome.newsapp.presentation.screen.article_detials.ArticleDetailsScreen
import com.awesome.newsapp.presentation.screen.home.ArticleUiState
import com.awesome.newsapp.presentation.screen.home.HomeScreen
import kotlin.reflect.typeOf

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewsNavigationHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = HomeScreenNavigator) {
        composable<HomeScreenNavigator> {
            HomeScreen(navController)
        }
        composable<ArticleDetailsScreenNavigator>(
            typeMap = mapOf(
                typeOf<ArticleUiState>() to NewsCustomNavType.article
            )
        ) {
            val argument = it.toRoute<ArticleDetailsScreenNavigator>()
            ArticleDetailsScreen(navController , argument.article)
        }
    }
}