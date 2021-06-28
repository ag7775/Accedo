package com.shivam.kotlin.moengage.di

import android.content.Context
import com.shivam.kotlin.colormemory_accedo.data.AppDatabase
import com.shivam.kotlin.colormemory_accedo.data.ScoresDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//Dependency Injection Using HILT
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideGameDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context = context)
    }

    @Provides
    fun provideScoresDao(db: AppDatabase): ScoresDao {
        return db.getScoresDao()
    }
    
}