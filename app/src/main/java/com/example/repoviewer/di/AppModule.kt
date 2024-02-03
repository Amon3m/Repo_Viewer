package com.example.repoviewer.di

import android.app.Application
import androidx.room.Room
import com.example.repoviewer.database.ConcreteLocalSource
import com.example.repoviewer.database.LocalSource
import com.example.repoviewer.database.RepoDao
import com.example.repoviewer.database.RepoDataBase
import com.example.repoviewer.network.ApiService
import com.example.utils.Constants.BASE_URL
import com.example.utils.Constants.REPO_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor())
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }
    @Singleton
    @Provides
    fun provideRepoDatabase(application: Application): RepoDataBase {
        return Room.databaseBuilder(application, RepoDataBase::class.java, REPO_DATABASE).build()
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