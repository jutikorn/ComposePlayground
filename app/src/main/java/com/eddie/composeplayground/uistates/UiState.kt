package com.eddie.composeplayground.uistates

import com.eddie.composeplayground.ui.PostIcons

sealed class UiState {
    object Loading: UiState()
    object EmptyItem: UiState()

    sealed class ConnectionError: UiState() {
        object NoInternet: ConnectionError()
        object ServerError: ConnectionError()
    }

    data class UiPost(
        val title: String,
        val body: String,
        var icon: PostIcons = PostIcons.Default
    ): UiState()

    data class LoadSuccess(
        var items: List<UiPost>
    ): UiState()
}