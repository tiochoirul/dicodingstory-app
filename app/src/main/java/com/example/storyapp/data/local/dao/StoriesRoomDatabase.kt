package com.example.storyapp.data.local.dao

import android.content.Context
import androidx.room.*
import com.example.storyapp.tools.Converter

@Database(
    entities = [Stories::class, RemoteKeys::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class StoriesRoomDatabase : RoomDatabase() {

    abstract fun storiesDao(): StoriesDao
    abstract fun remoteKeysDao(): RemoteKeysDao

    companion object {
        @Volatile
        private var INSTANCE: StoriesRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): StoriesRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    StoriesRoomDatabase::class.java, "stories"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}