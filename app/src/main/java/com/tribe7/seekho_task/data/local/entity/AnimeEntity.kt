package com.tribe7.seekho_task.data.local.entity

import Anime
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime")
data class AnimeEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val episodes: Int?,
    val rating: Double?,
    val posterUrl: String?,
    val lastRefreshedMillis: Long = System.currentTimeMillis()
)

fun AnimeEntity.toAnime() = Anime(
    id = id,
    title = title,
    episodes = episodes,
    rating = rating,
    posterUrl = posterUrl
)
