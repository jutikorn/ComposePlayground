package com.eddie.composeplayground.api

import com.google.gson.annotations.SerializedName

data class RawPost(
    @SerializedName("userId") val userId: String,
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String
)
