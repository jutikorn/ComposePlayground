package com.eddie.composeplayground.ui

import android.util.Log
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AmbientContentAlpha
import androidx.compose.material.AmbientEmphasisLevels
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideEmphasis
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.eddie.composeplayground.uistates.UiStates.UiPost

@Composable
fun PostRow(
    post: UiPost,
    onItemClicked: (UiPost) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable { onItemClicked(post) },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            PostTitle(post)
            PostBody(post)
            Divider(modifier = Modifier.fillMaxWidth())
        }
        Icon(imageVector = post.icon.vectorAsset)
    }
}

@Composable
fun PostTitle(post: UiPost) {
    Text(text = post.title,
        style = MaterialTheme.typography.subtitle1,
        maxLines = 1,
        modifier = Modifier.padding(top = 8.dp))
}

@Composable
fun PostBody(post: UiPost) {
    Text(text = post.body,
        style = MaterialTheme.typography.body1,
        maxLines = 2,
        color = Color.Gray,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.padding(bottom = 8.dp))
}

@Preview(showBackground = true)
@Composable
fun PreviewPostRow() {
    PostRow(
        post = UiPost("Some title", "quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto"),
        onItemClicked = {}
    )
}