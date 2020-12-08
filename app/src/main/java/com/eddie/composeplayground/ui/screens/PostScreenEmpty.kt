package com.eddie.composeplayground.ui.screens

import androidx.compose.material.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.eddie.composeplayground.ui.PostIcons.Empty

@Composable
fun EmptyScreen() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Empty.vectorAsset,
            modifier = Modifier.size(60.dp, 60.dp)
        )
    }
}

@Preview
@Composable
fun PreviewEmptyScreen() {
    EmptyScreen()
}