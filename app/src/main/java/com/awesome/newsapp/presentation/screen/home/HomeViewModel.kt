package com.awesome.newsapp.presentation.screen.home

import com.awesome.newsapp.domain.usecase.GetArticlesUseCase
import com.awesome.newsapp.presentation.base.BaseViewModel
import com.awesome.newsapp.presentation.base.ErrorState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getArticlesUseCase: GetArticlesUseCase
) : BaseViewModel<HomeViewState>(HomeViewState()) {

    init {
        handleIntent(HomeIntent.LoadNewsByCategory(state.value.currentCategory))
    }

    fun handleIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.LoadNewsByCategory -> loadNews(intent.category.displayName)
            is HomeIntent.ChangeCurrentCategory -> changeCurrentCategory(intent.category)
            is HomeIntent.ShowError -> showError(intent.message)
            else -> {}
        }
    }

    private fun changeCurrentCategory(category: NewsCategory) {
        updateState { it.copy(currentCategory = category, articles = emptyList() , errorMessage = null) }
        loadNews(category.displayName)
    }

    private fun loadNews(category: String) {
        updateState { state ->
            state.copy(isLoading = true)
        }
        tryToExecute(
            function = { getArticlesUseCase(category) },
            onSuccess = { articles ->
                updateState { state ->
                    state.copy(articles = articles.map { it.toUIModel() } , errorMessage = null , isLoading = false)
                }
            },
            onError = { error ->
                updateState {
                    it.copy(
                        isLoading = false,
                        errorMessage = when (error) {
                            is ErrorState.UnknownError -> error.message.toString()
                            is ErrorState.ServerError -> error.message.toString()
                            is ErrorState.NetworkError -> error.message.toString()
                            is ErrorState.EmptyData -> error.message.toString()
                        }
                    )
                }
            }
        )
    }

    private fun showError(message: String) {
        updateState { state -> state.copy(errorMessage = message) }
    }
}
