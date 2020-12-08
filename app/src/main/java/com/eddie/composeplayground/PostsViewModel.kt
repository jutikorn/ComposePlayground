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

    private val _postItemsLiveData: MutableLiveData<UiStates> = MutableLiveData(Loading)

    val postItemsLiveData: LiveData<UiStates>
    get() = _postItemsLiveData

    fun getPosts() {
        viewModelScope.launch {
            _postItemsLiveData.value = getPostItemsUseCase.execute()
        }
    }

    fun setAsFavorite(uiPost: UiPost) {
        _postItemsLiveData.value = setPostAsFavoriteUseCase.execute(_postItemsLiveData.value!!,
            uiPost)
    }

    fun removeFromFavorite(position: Int) {
        _postItemsLiveData.value = removePostFromFavoriteUseCase.execute(_postItemsLiveData.value!!, position)
    }
}