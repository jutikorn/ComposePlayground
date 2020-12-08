package com.eddie.composeplayground.ui.screens

import android.util.Log
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.ui.tooling.preview.Preview
import com.eddie.composeplayground.ui.ComposePlaygroundTheme
import com.eddie.composeplayground.uistates.UiStates
import com.eddie.composeplayground.uistates.UiStates.ConnectionError.NoInternet
import com.eddie.composeplayground.uistates.UiStates.ConnectionError.ServerError
import com.eddie.composeplayground.uistates.UiStates.EmptyItem
import com.eddie.composeplayground.uistates.UiStates.LoadSuccess
import com.eddie.composeplayground.uistates.UiStates.Loading
import com.eddie.composeplayground.uistates.UiStates.UiPost

@Composable
fun PostScreen(
    uiStates: UiStates,
    onItemClick: (UiPost) -> Unit,
    onRefresh: () -> Unit
) {
    ComposePlaygroundTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            when (uiStates) {
                is LoadSuccess -> {
                    PostsScreenDataLoadingSuccess(
                        dataLoadedSuccess = uiStates,
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
        uiStates = LoadSuccess(items = listOf(
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
        uiStates = NoInternet, {}, {})
}

@Preview
@Composable
fun PreviewPostScreenServerError() {
    PostScreen(
        uiStates = ServerError, {}, {})
}

@Preview
@Composable
fun PreviewPostScreenEmptyItem() {
    PostScreen(
        uiStates = EmptyItem, {}, {})
}

@Preview
@Composable
fun PreviewPostScreenLoading() {
    PostScreen(
        uiStates = Loading, {}, {})
}