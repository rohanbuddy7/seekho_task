package com.tribe7.seekho_task.data.repo

import Anime
import AnimeDetail
import com.tribe7.seekho_task.data.api.ApiService
import com.tribe7.seekho_task.data.local.dao.AnimeDao
import com.tribe7.seekho_task.data.local.dao.AnimeDetailDao
import com.tribe7.seekho_task.data.local.entity.AnimeEntity
import com.tribe7.seekho_task.data.local.entity.toAnime
import com.tribe7.seekho_task.data.local.entity.toAnimeDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import toEntity

class AnimeRepo(
    var apiService: ApiService,
    var dao: AnimeDao,
    var animeDetailDao: AnimeDetailDao,
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

    suspend fun updateAnimeDetail(id: Int){
        val response = apiService.getAnimeDetail(id)
        val animeDetail = response.data.toEntity()
        animeDetailDao.insertDetail(animeDetail)
    }

    suspend fun observeAnime(): Flow<List<Anime>> {
        return dao.observeAllAnime().map { entities ->
            entities.map { it.toAnime() }
        }
    }

    suspend fun observeAnimeDetail(id: Int): Flow<AnimeDetail?> {
        return animeDetailDao.observeDetail(id).map {entity->
            entity?.toAnimeDetail()
        }
    }

}