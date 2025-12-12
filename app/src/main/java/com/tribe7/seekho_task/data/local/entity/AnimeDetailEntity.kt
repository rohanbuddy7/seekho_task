package com.tribe7.seekho_task.data.local.entity

import AnimeDetail
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime_detail")
data class AnimeDetailEntity(
    @PrimaryKey val id: Int,
    val title: String?,
    val synopsis: String?,
    val episodes: Int?,
    val rating: Double?,
    val posterUrl: String?,
    val trailerUrl: String?,
    val genres: String?,
    val studios: String?
)

fun AnimeDetailEntity.toAnimeDetail() = AnimeDetail(
    id = id,
    title = title?:"",
    synopsis = synopsis?:"",
    episodes = episodes?:0,
    rating = rating?:0.0,
    posterUrl = posterUrl?:"",
    trailerUrl = trailerUrl?:"",
    genres = genres?.split(",") ?: emptyList(),
    studios = studios?.split(",") ?: emptyList()
)
