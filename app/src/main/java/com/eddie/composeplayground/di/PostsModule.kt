package com.eddie.composeplayground.di

import androidx.lifecycle.ViewModel
import com.eddie.composeplayground.PostsViewModel
import com.eddie.composeplayground.usecases.GetPostItemsUseCase
import com.eddie.composeplayground.usecases.GetPostItemsUseCaseImpl
import com.eddie.composeplayground.usecases.PostUiMapper
import com.eddie.composeplayground.usecases.PostUiMapperImpl
import com.eddie.composeplayground.usecases.RemovePostFromFavoriteUseCase
import com.eddie.composeplayground.usecases.RemovePostFromFavoriteUseCaseImpl
import com.eddie.composeplayground.usecases.SetPostAsFavoriteUseCase
import com.eddie.composeplayground.usecases.SetPostAsFavoriteUseCaseImpl
import com.eddie.composeplayground.utils.ConnectionUtil
import com.eddie.composeplayground.utils.ConnectionUtilImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ActivityScoped
import dagger.multibindings.IntoMap

@InstallIn(ActivityComponent::class)
@Module
abstract class PostsModule {
    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel::class)
    @ActivityScoped
    abstract fun bindPostsViewModel(impl: PostsViewModel): ViewModel
}

@InstallIn(ActivityRetainedComponent::class)
@Module
abstract class UseCaseModule {

    @ActivityRetainedScoped
    @Binds
    abstract fun SetPostAsFavoriteUseCase(usecaseImpl: SetPostAsFavoriteUseCaseImpl):
    SetPostAsFavoriteUseCase

    @ActivityRetainedScoped
    @Binds
    abstract fun GetPostItemsUseCase(usecaseImpl: GetPostItemsUseCaseImpl):
        GetPostItemsUseCase

    @ActivityRetainedScoped
    @Binds
    abstract fun RemovePostFromFavoriteUseCase(usecaseImpl: RemovePostFromFavoriteUseCaseImpl):
        RemovePostFromFavoriteUseCase

}

@InstallIn(ActivityRetainedComponent::class)
@Module
abstract class MapperModule {

    @ActivityRetainedScoped
    @Binds
    abstract fun mapper(mapper: PostUiMapperImpl): PostUiMapper
}

@InstallIn(ActivityRetainedComponent::class)
@Module
abstract class UtilsModule {

    @ActivityRetainedScoped
    @Binds
    abstract fun connectionUtil(connection: ConnectionUtilImpl): ConnectionUtil
}
