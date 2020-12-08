package com.eddie.composeplayground

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.setContent
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.ui.tooling.preview.Preview
import com.eddie.composeplayground.ui.screens.PostScreen
import com.eddie.composeplayground.uistates.UiStates.Loading
import com.eddie.composeplayground.utils.ConnectionUtil
import com.eddie.composeplayground.utils.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory

    @Inject
    lateinit var connectionUtil: ConnectionUtil

    private val viewModel: PostsViewModel by viewModels(factoryProducer = { factory })

    init {
        lifecycleScope.launchWhenStarted {
            viewModel.getPosts()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val state by viewModel.postItemsLiveData.observeAsState(Loading)
            PostScreen(
                uiStates = state,
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