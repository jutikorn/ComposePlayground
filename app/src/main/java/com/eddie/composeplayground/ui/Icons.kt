package com.eddie.composeplayground.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudOff
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.SignalWifiOff
import androidx.compose.ui.graphics.vector.ImageVector

enum class PostIcons(val vectorAsset: ImageVector) {
    Favorite(Icons.Default.Favorite),
    FavoriteBorder(Icons.Default.FavoriteBorder),
    WifiOff(Icons.Default.SignalWifiOff),
    CloudOff(Icons.Default.CloudOff),
    Empty(Icons.Default.List);
    companion object {
        val Default = FavoriteBorder
    }
}