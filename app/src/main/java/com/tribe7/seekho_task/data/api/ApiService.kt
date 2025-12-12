package com.tribe7.seekho_task.data.api

import AnimeListResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top/anime")
    suspend fun getAnime(
        @Query("page") page: Int = 1
    ): AnimeListResponseDto

}