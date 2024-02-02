package com.example.repoviewer.di

import com.example.repoviewer.model.Repository
import com.example.repoviewer.model.RepositoryInterface
import com.example.repoviewer.network.ApiClient
import com.example.repoviewer.network.RemoteSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
abstract class RepoModule {

    @Binds
    @ViewModelScoped
    abstract fun bindRemoteSource(apiClient: ApiClient): RemoteSource

    @Binds
    @ViewModelScoped
    abstract fun bindRepo(repository: Repository): RepositoryInterface
}