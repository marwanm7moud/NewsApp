package com.awesome.newsapp.presentation.screens.article_detials

sealed class ArticleDetailsIntent{
    data object NavigateBack : ArticleDetailsIntent()
    data class ShareArticleUrl(val articleUrl: String) : ArticleDetailsIntent()
    data class OpenBrowserToReadFullArticle(val articleUrl: String) : ArticleDetailsIntent()
}