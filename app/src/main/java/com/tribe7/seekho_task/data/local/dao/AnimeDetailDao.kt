package com.tribe7.seekho_task.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tribe7.seekho_task.data.local.entity.AnimeDetailEntity
import com.tribe7.seekho_task.data.local.entity.AnimeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetail(detail: AnimeDetailEntity)

    @Query("SELECT * FROM anime_detail where id = :id")
    suspend fun getDetail(id: Int): AnimeDetailEntity

    @Query("SELECT * FROM anime_detail where id = :id")
    fun observeDetail(id: Int): Flow<AnimeDetailEntity?>

}