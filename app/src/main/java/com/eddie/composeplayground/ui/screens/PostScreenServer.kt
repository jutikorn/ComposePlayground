package com.eddie.composeplayground.ui.screens

import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.eddie.composeplayground.ui.PostIcons.CloudOff

@Composable
fun ServerErrorScreen(onRefresh: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth().fillMaxHeight()
    ) {
        Icon(
            imageVector = CloudOff.vectorAsset,
            modifier = Modifier.size(60.dp, 60.dp)
        )
        Button(onClick = onRefresh) {
            Text(text = "Retry")
        }
    }
}

@Preview
@Composable
fun PreviewServerErrorScreen() {
    ServerErrorScreen {}
}