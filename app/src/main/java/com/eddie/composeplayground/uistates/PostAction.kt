package com.eddie.composeplayground.uistates

sealed class PostAction {
    object FetchingPosts: PostAction()
    data class SetAsFavorite(val position: Int): PostAction()
    data class RemoveFromFavorite(val position: Int): PostAction()
}