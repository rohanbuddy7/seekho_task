package com.tribe7.seekho_task.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tribe7.seekho_task.data.local.dao.AnimeDao
import com.tribe7.seekho_task.data.local.entity.AnimeEntity

@Database(entities = [AnimeEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    companion object{
        private var instance: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            if(instance==null){
                instance = Room.databaseBuilder(context, AppDatabase::class.java, "anime.db")
                    .build()
            }
            return instance!!
        }
    }

    abstract fun animeDao(): AnimeDao
}