package com.eddie.composeplayground.usecases

import com.eddie.composeplayground.ui.PostIcons.Favorite
import com.eddie.composeplayground.uistates.UiStates
import com.eddie.composeplayground.uistates.UiStates.UiPost
import javax.inject.Inject

interface SetPostAsFavoriteUseCase {
    fun execute(postItems: UiStates, uiPost: UiPost): UiStates
}

class SetPostAsFavoriteUseCaseImpl
@Inject internal constructor(): SetPostAsFavoriteUseCase {

    override fun execute(postItems: UiStates, uiPost: UiPost): UiStates {
        if(postItems is UiStates.LoadSuccess) {
            return postItems.also {
                val position = it.items.indexOf(uiPost)
                it.items[position].icon = Favorite
            }
        } else {
            throw IllegalStateException("UiStates must be LoadSuccess")
        }
    }

}