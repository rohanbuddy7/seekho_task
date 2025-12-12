package com.tribe7.seekho_task.data.api

import AnimeDetailResponseDto
import AnimeListResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("top/anime")
    suspend fun getAnime(
        @Query("page") page: Int = 1
    ): AnimeListResponseDto

    @GET("anime/{id}")
    suspend fun getAnimeDetail(
        @Path("id") id: Int
    ): AnimeDetailResponseDto

}