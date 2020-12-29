package com.eddie.composeplayground

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eddie.composeplayground.uistates.UiState
import com.eddie.composeplayground.uistates.UiState.Loading
import com.eddie.composeplayground.uistates.UiState.UiPost
import com.eddie.composeplayground.usecases.GetPostItemsUseCase
import com.eddie.composeplayground.usecases.RemovePostFromFavoriteUseCase
import com.eddie.composeplayground.usecases.SetPostAsFavoriteUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class PostsViewModel @Inject internal constructor(
    private val getPostItemsUseCase: GetPostItemsUseCase,
    private val setPostAsFavoriteUseCase: SetPostAsFavoriteUseCase,
    private val removePostFromFavoriteUseCase: RemovePostFromFavoriteUseCase
): ViewModel() {

    private val _postItems: MutableLiveData<UiState> by lazy {
        MutableLiveData<UiState>(Loading)
    }
    val postItems: LiveData<UiState>
        get() = _postItems

    fun getPosts() {
        viewModelScope.launch {
            _postItems.value = getPostItemsUseCase.execute()
        }
    }

    fun setAsFavorite(uiPost: UiPost) {
        _postItems.value = setPostAsFavoriteUseCase.execute(postItems.value,
            uiPost)
    }

    fun removeFromFavorite(position: Int) {
        _postItems.value = removePostFromFavoriteUseCase.execute(postItems.value, position)
    }
}