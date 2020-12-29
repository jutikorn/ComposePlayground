package com.eddie.composeplayground.usecases

import com.eddie.composeplayground.ui.PostIcons.FavoriteBorder
import com.eddie.composeplayground.uistates.UiState
import javax.inject.Inject

interface RemovePostFromFavoriteUseCase {
    fun execute(postItems: UiState?, position: Int): UiState
}

class RemovePostFromFavoriteUseCaseImpl
@Inject internal constructor(): RemovePostFromFavoriteUseCase {

    override fun execute(postItems: UiState?, position: Int): UiState {
        if(postItems is UiState.LoadSuccess) {
            return postItems.also {
                it.items.getOrNull(position)?.icon = FavoriteBorder
            }
        } else {
            throw IllegalStateException("UiStates must be LoadSuccess")
        }
    }

}