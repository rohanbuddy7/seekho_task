package com.tribe7.seekho_task.data.repo

import Anime
import com.tribe7.seekho_task.data.api.ApiService
import com.tribe7.seekho_task.data.local.dao.AnimeDao
import com.tribe7.seekho_task.data.local.entity.AnimeEntity
import com.tribe7.seekho_task.data.local.entity.toAnime
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AnimeRepo(
    var apiService: ApiService,
    var dao: AnimeDao
) {

    suspend fun updateAnimeList(){
        val response = apiService.getAnime(1)
        val animeList = response.data.map {
            AnimeEntity(
                id = it.malId,
                title = it.title?:"",
                posterUrl = it.images?.jpg?.imageUrl,
                episodes = it.episodes,
                rating = it.score,
            )
        }
        dao.insertAll(animeList)
    }

    suspend fun observeAnime(): Flow<List<Anime>> {
        return dao.observeAllAnime().map { entities ->
            entities.map { it.toAnime() }
        }
    }

}