package com.eddie.composeplayground

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eddie.composeplayground.uistates.PostAction
import com.eddie.composeplayground.uistates.PostAction.FetchingPosts
import com.eddie.composeplayground.uistates.PostAction.RemoveFromFavorite
import com.eddie.composeplayground.uistates.PostAction.SetAsFavorite
import com.eddie.composeplayground.uistates.UiStates
import com.eddie.composeplayground.uistates.UiStates.Loading
import com.eddie.composeplayground.usecases.GetPostItemsUseCase
import com.eddie.composeplayground.usecases.RemovePostFromFavoriteUseCase
import com.eddie.composeplayground.usecases.SetPostAsFavoriteUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class PostsViewModel @Inject internal constructor(
    private val getPostItemsUseCase: GetPostItemsUseCase,
    private val setPostAsFavoriteUseCase: SetPostAsFavoriteUseCase,
    private val removePostFromFavoriteUseCase: RemovePostFromFavoriteUseCase
): ViewModel() {

    val actionFlow = MutableSharedFlow<PostAction>()

    private val _mutableUiStateFlow = MutableStateFlow<UiStates>(Loading)
    val uiStateFlow: StateFlow<UiStates>
        get() = _mutableUiStateFlow

    init {
        handleActions()
        getPosts()
    }

    fun getPosts() {
        viewModelScope.launch {
            actionFlow.emit(FetchingPosts)
        }
    }

    fun setAsFavorite(position: Int) {
        viewModelScope.launch {
            actionFlow.emit(SetAsFavorite(position))
        }
    }

    fun removeFromFavorite(position: Int) {
        viewModelScope.launch {
            actionFlow.emit(RemoveFromFavorite(position))
        }
    }

    private fun handleActions() {
        viewModelScope.launch {
            actionFlow.collect { action ->
                when(action) {
                    is SetAsFavorite -> {
                        _mutableUiStateFlow.emit(setPostAsFavoriteUseCase.execute(uiStateFlow.value,
                            action.position))
                    }
                    is RemoveFromFavorite -> {
                        _mutableUiStateFlow.emit(removePostFromFavoriteUseCase.execute(uiStateFlow.value,
                            action.position))
                    }
                    is FetchingPosts -> {
                        _mutableUiStateFlow.emit(getPostItemsUseCase.execute())
                    }
                }
            }
        }
    }
}