package com.eddie.composeplayground.uistates

import com.eddie.composeplayground.ui.PostIcons

sealed class UiStates {
    object Loading: UiStates()
    object EmptyItem: UiStates()

    sealed class ConnectionError: UiStates() {
        object NoInternet: ConnectionError()
        object ServerError: ConnectionError()
    }

    data class UiPost(
        val title: String,
        val body: String,
        var icon: PostIcons = PostIcons.Default
    ): UiStates()

    data class LoadSuccess(
        var items: List<UiPost>
    ): UiStates()
}