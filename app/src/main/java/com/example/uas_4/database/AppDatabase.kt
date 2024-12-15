package com.example.uas_4.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ProductEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun eventDao(): ProductDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration() // Menambahkan fallback untuk menghapus dan membangun ulang database
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

}