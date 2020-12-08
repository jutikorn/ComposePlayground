package com.eddie.composeplayground

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eddie.composeplayground.uistates.UiStates
import com.eddie.composeplayground.uistates.UiStates.LoadSuccess
import com.eddie.composeplayground.uistates.UiStates.Loading
import com.eddie.composeplayground.uistates.UiStates.UiPost
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

    var postItems: UiStates by mutableStateOf(Loading)
        private set

    fun getPosts() {
        viewModelScope.launch {
            postItems = getPostItemsUseCase.execute()
        }
    }

    fun setAsFavorite(uiPost: UiPost) {
        postItems = setPostAsFavoriteUseCase.execute(postItems,
            uiPost)
    }

    fun removeFromFavorite(position: Int) {
        postItems = removePostFromFavoriteUseCase.execute(postItems, position)
    }
}