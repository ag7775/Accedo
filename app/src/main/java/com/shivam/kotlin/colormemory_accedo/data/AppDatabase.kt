package com.shivam.kotlin.colormemory_accedo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.hilt.android.qualifiers.ApplicationContext

//Database
@Database(entities = [Score::class],version = 1,exportSchema = false)
abstract class AppDatabase : RoomDatabase(){

    abstract fun getScoresDao() : ScoresDao

    companion object{

        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getInstance(@ApplicationContext context: Context): AppDatabase {
            synchronized(this){
                var instance = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        "color_memory_db"
                    ).fallbackToDestructiveMigration().build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}