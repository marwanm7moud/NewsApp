package com.awesome.newsapp.presentation.screen.article_detials

sealed interface ArticleDetailsIntent{
    data object NavigateBack : ArticleDetailsIntent
    data class ShareArticleUrl(val articleUrl: String) : ArticleDetailsIntent
    data class OpenBrowserToReadFullArticle(val articleUrl: String) : ArticleDetailsIntent
}