package com.eddie.composeplayground.usecases

import com.eddie.composeplayground.api.PlaceHolderService
import com.eddie.composeplayground.api.RawPost
import com.eddie.composeplayground.uistates.UiStates
import com.eddie.composeplayground.uistates.UiStates.LoadSuccess
import com.eddie.composeplayground.uistates.UiStates.UiPost
import com.eddie.composeplayground.utils.ConnectionUtil
import retrofit2.HttpException
import javax.inject.Inject

interface GetPostItemsUseCase {
    suspend fun execute(): UiStates
}

class GetPostItemsUseCaseImpl
@Inject internal constructor(
        private val connectionUtil: ConnectionUtil,
        private val service: PlaceHolderService,
        private val mapper: PostUiMapper
    ): GetPostItemsUseCase {

    override suspend fun execute(): UiStates = when {
        connectionUtil.isOnline.not() -> UiStates.ConnectionError.NoInternet
        else -> runCatching {
            val posts = service.getPosts()
            when {
                posts.isEmpty() -> UiStates.EmptyItem
                else -> LoadSuccess(posts.map(mapper))
            }
        }.getOrElse(::onError)
    }

    private fun onError(throwable: Throwable): UiStates = when (throwable) {
        is HttpException -> UiStates.ConnectionError.ServerError
        else -> UiStates.EmptyItem
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