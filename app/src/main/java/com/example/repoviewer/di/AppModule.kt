package com.example.repoviewer.di

import android.app.Application
import androidx.room.Room
import com.example.repoviewer.database.ConcreteLocalSource
import com.example.repoviewer.database.LocalSource
import com.example.repoviewer.database.RepoDao
import com.example.repoviewer.database.RepoDataBase
import com.example.repoviewer.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL= "https://api.github.com"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor()).build())
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideRepoDatabase(application: Application): RepoDataBase {
        return Room.databaseBuilder(application, RepoDataBase::class.java, "Repo_database").build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(movieDatabase: RepoDataBase): RepoDao {
        return movieDatabase.repoDao()
    }

    @Provides
    @Singleton
    fun bindLocalSource(repoDao: RepoDao): LocalSource {
        return ConcreteLocalSource(repoDao)
    }
}