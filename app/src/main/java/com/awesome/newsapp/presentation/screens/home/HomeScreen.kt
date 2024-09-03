package com.awesome.newsapp.presentation.screens.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.awesome.newsapp.R
import com.awesome.newsapp.presentation.navigation.ArticleDetailsScreenNavigator
import com.awesome.newsapp.presentation.utils.formatIsoDateToDateTime
import com.awesome.newsapp.presentation.utils.formatTimeAgo

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val state by homeViewModel.state.collectAsState()
    HomeContent(
        state = state,
        handleIntent = { intent ->
            when (intent) {
                is HomeIntent.NavigateToArticleDetails -> {
                    navHostController.navigate(ArticleDetailsScreenNavigator(intent.article))
                }
                else -> homeViewModel.handleIntent(intent)
            }
        }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    state: HomeViewState,
    handleIntent: (HomeIntent) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "News App") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xfff1f1f1))
            )
        },
        containerColor = Color(0xfff1f1f1)
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding)
        ) {
            LazyRow(
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(NewsCategory.entries) { category ->
                    Box(
                        modifier = Modifier
                            .size(150.dp, 80.dp)
                            .background(Color.White, RoundedCornerShape(16.dp))
                            .clip(RoundedCornerShape(16.dp))
                            .clickable { handleIntent(HomeIntent.LoadNewsByCategory(category)) }
                            .padding(horizontal = 8.dp, vertical = 16.dp),

                        contentAlignment = Center
                    ) {
                        Text(text = category.displayName)
                    }
                }
            }



            when {
                state.isLoading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Center) {
                        CircularProgressIndicator(color = Color.Red)
                    }
                }

                state.errorMessage != null -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Center) {
                        Text(text = "Error: ${state.errorMessage}", color = Color.Red)
                    }
                }

                else -> {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 32.dp)
                    ) {
                        items(state.articles) { article ->
                            Box(
                                modifier = Modifier
                                    .height(120.dp)
                                    .fillMaxWidth()
                                    .background(Color.White, RoundedCornerShape(8.dp))
                                    .clip(RoundedCornerShape(8.dp))
                                    .clickable {
                                        handleIntent(
                                            HomeIntent.NavigateToArticleDetails(
                                                article
                                            )
                                        )
                                    },
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Row(
                                    modifier = Modifier.padding(16.dp),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    AsyncImage(
                                        model = article.urlToImage,
                                        contentDescription = null,
                                        error = painterResource(id = R.drawable.ic_broken_image),
                                        modifier = Modifier
                                            .aspectRatio(1f)
                                            .clip(RoundedCornerShape(8.dp)),
                                        contentScale = ContentScale.FillBounds,
                                        clipToBounds = true
                                    )
                                    Column(
                                        modifier = Modifier.fillMaxHeight(),
                                        verticalArrangement = Arrangement.spacedBy(16.dp)
                                    ) {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text(
                                                text = article.sourceName,
                                                style = TextStyle(
                                                    color = Color(0xFF3C3C3C),
                                                    fontSize = 12.sp
                                                )
                                            )
                                            Text(
                                                text = formatTimeAgo(formatIsoDateToDateTime(article.publishedAt)),
                                                style = TextStyle(
                                                    color = Color(0xFF3C3C3C),
                                                    fontSize = 12.sp
                                                )
                                            )
                                        }
                                        Text(
                                            text = article.title,
                                            maxLines = 2,
                                            minLines = 2,
                                            overflow = TextOverflow.Ellipsis,
                                            style = TextStyle(
                                                color = Color(0xFF000000),
                                                fontSize = 16.sp
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}