package com.eddie.composeplayground.usecases

import com.eddie.composeplayground.api.PlaceHolderService
import com.eddie.composeplayground.api.RawPost
import com.eddie.composeplayground.uistates.UiState
import com.eddie.composeplayground.uistates.UiState.LoadSuccess
import com.eddie.composeplayground.uistates.UiState.UiPost
import com.eddie.composeplayground.utils.ConnectionUtil
import retrofit2.HttpException
import javax.inject.Inject

interface GetPostItemsUseCase {
    suspend fun execute(): UiState
}

class GetPostItemsUseCaseImpl
@Inject internal constructor(
        private val connectionUtil: ConnectionUtil,
        private val service: PlaceHolderService,
        private val mapper: PostUiMapper
    ): GetPostItemsUseCase {

    override suspend fun execute(): UiState = when {
        connectionUtil.isOnline.not() -> UiState.ConnectionError.NoInternet
        else -> runCatching {
            val posts = service.getPosts()
            when {
                posts.isEmpty() -> UiState.EmptyItem
                else -> LoadSuccess(posts.map(mapper))
            }
        }.getOrElse(::onError)
    }

    private fun onError(throwable: Throwable): UiState = when (throwable) {
        is HttpException -> UiState.ConnectionError.ServerError
        else -> UiState.EmptyItem
    }
}

interface PostUiMapper: Function1<RawPost, UiPost>

class PostUiMapperImpl
    @Inject internal constructor(): PostUiMapper {
    override fun invoke(post: RawPost): UiPost = UiPost(
        title = post.title,
        body = post.body
    )
}