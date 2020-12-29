package com.eddie.composeplayground.usecases

import com.eddie.composeplayground.ui.PostIcons.Favorite
import com.eddie.composeplayground.uistates.UiState
import com.eddie.composeplayground.uistates.UiState.UiPost
import javax.inject.Inject

interface SetPostAsFavoriteUseCase {
    fun execute(postItems: UiState?, uiPost: UiPost): UiState
}

class SetPostAsFavoriteUseCaseImpl
@Inject internal constructor(): SetPostAsFavoriteUseCase {

    override fun execute(postItems: UiState?, uiPost: UiPost): UiState {
        if(postItems is UiState.LoadSuccess) {
            return postItems.also {
                val position = it.items.indexOf(uiPost)
                it.items[position].icon = Favorite
            }
        } else {
            throw IllegalStateException("UiStates must be LoadSuccess")
        }
    }

}