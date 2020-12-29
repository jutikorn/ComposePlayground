package com.eddie.composeplayground.usecases

import com.eddie.composeplayground.ui.PostIcons.Favorite
import com.eddie.composeplayground.uistates.UiStates
import com.eddie.composeplayground.uistates.UiStates.UiPost
import javax.inject.Inject

interface SetPostAsFavoriteUseCase {
    fun execute(postItems: UiStates, position: Int): UiStates
}

class SetPostAsFavoriteUseCaseImpl
@Inject internal constructor(): SetPostAsFavoriteUseCase {

    override fun execute(postItems: UiStates, position: Int): UiStates {
        if(postItems is UiStates.LoadSuccess) {
            return postItems.also {
                it.items[position].icon = Favorite
            }
        } else {
            throw IllegalStateException("UiStates must be LoadSuccess")
        }
    }

}