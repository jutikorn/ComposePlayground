package com.eddie.composeplayground.api

import retrofit2.http.GET

interface PlaceHolderService {

    @GET("posts")
    suspend fun getPosts(): List<RawPost>
}