package com.tribe7.seekho_task.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tribe7.seekho_task.data.local.entity.AnimeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDao {

    @Query("SELECT * FROM anime ORDER BY title COLLATE NOCASE")
    fun getAllAnime(): Flow<List<AnimeEntity>>

    @Query("SELECT * FROM anime WHERE id = :id LIMIT 1")
    fun getAnimeById(id: Int): Flow<AnimeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(anime: List<AnimeEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(anime: AnimeEntity)

    @Query("DELETE FROM anime WHERE id = :id")
    suspend fun deleteAnime(id: Int)

    @Query("DELETE FROM anime")
    suspend fun clearAll()

    @Query("SELECT * FROM anime")
    suspend fun observeAllAnime(): Flow<List<AnimeEntity>>

}