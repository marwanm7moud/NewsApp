package com.awesome.newsapp.presentation.screen.article_detials

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.awesome.newsapp.R
import com.awesome.newsapp.presentation.screen.home.ArticleUiState
import com.awesome.newsapp.presentation.util.formatIsoDateToDateTime
import com.awesome.newsapp.presentation.util.formatTimeAgo

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ArticleDetailsScreen(
    navController: NavHostController,
    article: ArticleUiState
) {
    val context = LocalContext.current


    ArticleDetailsContent(
        article = article,
        handleIntent = { intent ->
            when (intent) {
                ArticleDetailsIntent.NavigateBack -> navController.navigateUp()

                is ArticleDetailsIntent.OpenBrowserToReadFullArticle -> {
                    val openBrowserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(intent.articleUrl))
                    context.startActivity(openBrowserIntent)
                }

                is ArticleDetailsIntent.ShareArticleUrl -> {
                    val shareIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, intent.articleUrl)
                        type = "text/plain"
                    }
                    context.startActivity(Intent.createChooser(shareIntent, "Share Article"))
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ArticleDetailsContent(
    article: ArticleUiState,
    handleIntent: (ArticleDetailsIntent) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Article Details") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xfff1f1f1)),
                navigationIcon = {
                    IconButton(onClick = { handleIntent(ArticleDetailsIntent.NavigateBack) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = ""
                        )
                    }
                }
            )
        },
        containerColor = Color(0xfff1f1f1)
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            AsyncImage(
                model = article.urlToImage,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
                error = rememberAsyncImagePainter(model = R.drawable.ic_broken_image)
            )

            Text(
                text = "By ${article.author}",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Published ${formatTimeAgo(formatIsoDateToDateTime(article.publishedAt))}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary
            )

            Text(
                text = "Source: ${article.sourceName}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary
            )

            Text(
                text = article.description,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )

            Text(
                text = article.content,
                style = MaterialTheme.typography.bodyMedium,
                overflow = TextOverflow.Ellipsis,
                maxLines = 10
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Button(
                    onClick = {
                        handleIntent(ArticleDetailsIntent.OpenBrowserToReadFullArticle(article.url))
                    }
                ) {
                    Text(text = "Read Full Article")
                }

                Button(
                    onClick = {
                        handleIntent(ArticleDetailsIntent.ShareArticleUrl(article.url))
                    }
                ) {
                    Text(text = "Share")
                }
            }
        }
    }
}
