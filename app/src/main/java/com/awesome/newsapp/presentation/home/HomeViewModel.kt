package com.awesome.newsapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.awesome.newsapp.domain.usecases.GetArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getArticlesUseCase: GetArticlesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeViewState())
    val state: StateFlow<HomeViewState> = _state

    init {
        handleIntent(HomeIntent.LoadNewsByCategory(state.value.currentCategory))
    }

    private fun handleIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.LoadNewsByCategory -> loadNews(intent.category.displayName)
            is HomeIntent.ChangeCurrentCategory -> changeCurrentCategory(intent.category)
            is HomeIntent.ShowError -> showError(intent.message)
            is HomeIntent.NavigateToArticleDetails -> TODO()
        }
    }
    private fun changeCurrentCategory(category: NewsCategory) {
        updateState { it.copy(currentCategory = category) }
        loadNews(category.displayName)
    }

    private fun loadNews(category: String) {
        viewModelScope.launch {
            updateState { state -> state.copy(isLoading = true) }
            try {
                val articles = getArticlesUseCase.getArticlesByCategory(category)
                updateState { state -> state.copy(articles = articles.map { it.toUIModel() }) }
            } catch (e: Exception) {
                updateState { state -> state.copy(errorMessage = e.message) }
            }
        }
    }
    private fun showError(message: String) {
        updateState { state -> state.copy(errorMessage = message) }
    }

    private fun updateState(updater: (HomeViewState) -> HomeViewState) {
        _state.update(updater)
    }
}
