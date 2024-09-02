package com.awesome.newsapp.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.awesome.newsapp.domain.util.EmptyDataException
import com.awesome.newsapp.domain.util.NetworkException
import com.awesome.newsapp.domain.util.ServerErrorException
import com.awesome.newsapp.domain.util.UnknownErrorException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<S>(initialState: S) : ViewModel() {
    private val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    protected fun <T> tryToExecute(
        function: suspend () -> T,
        onSuccess: (T) -> Unit,
        onError: (ErrorState) -> Unit,
    ): Job {
        return runWithErrorCheck(onError) {
            val result = function()
            onSuccess(result)
        }
    }

    protected fun updateState(updater: (S) -> S) {
        _state.update(updater)
    }
    private fun runWithErrorCheck(
        onError: (ErrorState) -> Unit,
        inScope: CoroutineScope = viewModelScope,
        dispatcher: CoroutineDispatcher = Dispatchers.Default,
        function: suspend () -> Unit,
    ): Job {
        return inScope.launch(dispatcher) {
            try {
                function()
            } catch (exception: EmptyDataException) {
                onError(ErrorState.EmptyData(exception.message))
            } catch (exception: NetworkException) {
                onError(ErrorState.NetworkError(exception.message))
            } catch (exception: UnknownErrorException) {
                onError(ErrorState.UnknownError(exception.message))
            } catch (exception: ServerErrorException) {
                onError(ErrorState.ServerError(exception.message))
            } catch (exception: Exception) {
                onError(ErrorState.UnknownError(exception.message))
            }
        }
    }
}