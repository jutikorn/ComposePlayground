package com.eddie.composeplayground.usecases

import com.eddie.composeplayground.ui.PostIcons.FavoriteBorder
import com.eddie.composeplayground.uistates.UiStates
import javax.inject.Inject

interface RemovePostFromFavoriteUseCase {
    fun execute(postItems: UiStates, position: Int): UiStates
}

class RemovePostFromFavoriteUseCaseImpl
@Inject internal constructor(): RemovePostFromFavoriteUseCase {

    override fun execute(postItems: UiStates, position: Int): UiStates {
        if(postItems is UiStates.LoadSuccess) {
            return postItems.also {
                it.items.getOrNull(position)?.icon = FavoriteBorder
            }
        } else {
            throw IllegalStateException("UiStates must be LoadSuccess")
        }
    }

}