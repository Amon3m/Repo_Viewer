package com.example.repoviewer.database

import com.example.repoviewer.model.RepositoryEntity
import kotlinx.coroutines.flow.Flow

interface LocalSource {
        fun getAllRepos(): Flow<List<RepositoryEntity>>
        suspend fun insertAll(repos: List<RepositoryEntity>)
        suspend fun updateStarCount(repoId: Int, newStarCount: Int)
        fun searchRepos(query: String): Flow<List<RepositoryEntity>>
}