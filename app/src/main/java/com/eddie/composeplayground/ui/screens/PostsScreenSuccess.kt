package com.eddie.composeplayground.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.lazy.LazyColumnForIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.eddie.composeplayground.ui.PostRow
import com.eddie.composeplayground.uistates.UiStates.LoadSuccess
import com.eddie.composeplayground.uistates.UiStates.UiPost

@Composable
fun PostsScreenDataLoadingSuccess(
    dataLoadedSuccess: LoadSuccess,
    onItemClicked: (Int) -> Unit
) {
    Column {
        LazyColumnForIndexed(
            items = dataLoadedSuccess.items,
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(top = 8.dp)
        ) { index, post ->
            PostRow(
                index = index,
                post = post,
                onItemClicked = onItemClicked
            )
        }
    }
}

@Preview
@Composable
fun PreviewPostsScreenDataLoadingSuccess(){
    PostsScreenDataLoadingSuccess(
        dataLoadedSuccess = LoadSuccess(items = listOf(
            UiPost("title1", "desc1"),
            UiPost("title2", "desc2"),
            UiPost("title3", "desc3"),
            UiPost("title4", "desc4"),
            UiPost("title5", "desc5"),
            UiPost("title6", "desc6"),
            UiPost("title7", "desc7")
        ))
    ) {

    }
}