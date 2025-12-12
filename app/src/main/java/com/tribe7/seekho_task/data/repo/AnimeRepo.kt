package com.tribe7.seekho_task.data.repo

import com.tribe7.seekho_task.data.api.ApiService
import com.tribe7.seekho_task.data.local.dao.AnimeDao
import com.tribe7.seekho_task.data.local.entity.AnimeEntity
import kotlinx.coroutines.flow.Flow

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

    suspend fun observeAnime(): Flow<List<AnimeEntity>> {
        return dao.observeAllAnime()
    }

}