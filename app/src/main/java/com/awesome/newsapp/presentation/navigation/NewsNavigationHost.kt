package com.awesome.newsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.awesome.newsapp.presentation.article_detials.ArticleDetailsScreen
import com.awesome.newsapp.presentation.home.HomeScreen

@Composable
fun NewsNavigationHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = HomeScreenNavigator) {
        composable<HomeScreenNavigator> {
            HomeScreen()
        }
        composable<ArticleDetailsScreenNavigator> {
            ArticleDetailsScreen()
        }
    }
}