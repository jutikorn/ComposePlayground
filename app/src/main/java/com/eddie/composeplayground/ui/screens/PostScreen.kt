package com.eddie.composeplayground.ui.screens

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.ui.tooling.preview.Preview
import com.eddie.composeplayground.ui.ComposePlaygroundTheme
import com.eddie.composeplayground.uistates.UiState
import com.eddie.composeplayground.uistates.UiState.ConnectionError.NoInternet
import com.eddie.composeplayground.uistates.UiState.ConnectionError.ServerError
import com.eddie.composeplayground.uistates.UiState.EmptyItem
import com.eddie.composeplayground.uistates.UiState.LoadSuccess
import com.eddie.composeplayground.uistates.UiState.Loading
import com.eddie.composeplayground.uistates.UiState.UiPost

@Composable
fun PostScreen(
    uiState: UiState?,
    onItemClick: (UiPost) -> Unit,
    onRefresh: () -> Unit
) {
    ComposePlaygroundTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            when (uiState) {
                is LoadSuccess -> {
                    PostsScreenDataLoadingSuccess(
                        dataLoadedSuccess = uiState,
                        onItemClicked = onItemClick
                    )
                }
                is NoInternet -> NoInternetScreen()
                is ServerError -> ServerErrorScreen(onRefresh = onRefresh)
                is EmptyItem -> EmptyScreen()
                is Loading -> LoadingScreen()
                else -> Unit
            }
        }
    }
}

@Preview
@Composable
fun PreviewPostScreenLoadDataSuccessful() {
    PostScreen(
        uiState = LoadSuccess(
            items = listOf(
                UiPost("title1", "desc1"),
                UiPost("title2", "desc2"),
                UiPost("title3", "desc3"),
                UiPost("title4", "desc4"),
                UiPost("title5", "desc5"),
                UiPost("title6", "desc6"),
                UiPost("title7", "desc7")
            ),
        ), {}, {})
}

@Preview
@Composable
fun PreviewPostScreenNoInternet() {
    PostScreen(
        uiState = NoInternet, {}, {})
}

@Preview
@Composable
fun PreviewPostScreenServerError() {
    PostScreen(
        uiState = ServerError, {}, {})
}

@Preview
@Composable
fun PreviewPostScreenEmptyItem() {
    PostScreen(
        uiState = EmptyItem, {}, {})
}

@Preview
@Composable
fun PreviewPostScreenLoading() {
    PostScreen(
        uiState = Loading, {}, {})
}