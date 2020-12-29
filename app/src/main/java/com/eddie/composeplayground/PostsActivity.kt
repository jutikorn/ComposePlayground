package com.eddie.composeplayground

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.setContent
import androidx.lifecycle.lifecycleScope
import androidx.ui.tooling.preview.Preview
import com.eddie.composeplayground.ui.screens.PostScreen
import com.eddie.composeplayground.uistates.UiStates.Loading
import com.eddie.composeplayground.utils.ConnectionUtil
import com.eddie.composeplayground.utils.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory

    @Inject
    lateinit var connectionUtil: ConnectionUtil

    private val viewModel: PostsViewModel by viewModels(factoryProducer = { factory })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val uiState by viewModel.uiStateFlow.collectAsState(lifecycleScope.coroutineContext)
            PostScreen(
                uiStates = uiState,
                onItemClick = viewModel::setAsFavorite,
                onRefresh = viewModel::getPosts
            )
        }
        lifecycle.addObserver(connectionUtil)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PostScreen(uiStates = Loading, onItemClick = {}, onRefresh = {})
}