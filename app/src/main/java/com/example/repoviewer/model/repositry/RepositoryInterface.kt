package com.example.repoviewer.model.repositry

import com.example.repoviewer.model.DetailsResponse
import com.example.repoviewer.model.IssuesResponse
import com.example.repoviewer.model.RepositoriesResponse
import com.example.repoviewer.model.RepositoryEntity
import kotlinx.coroutines.flow.Flow

interface RepositoryInterface  {
     suspend fun getRepositoriesFromNetwork(): Flow<List<RepositoriesResponse>>

     suspend fun getRepoDetails(owner: String, repo: String): Flow<DetailsResponse>

     suspend fun getRepoIssues(owner: String, repo: String): Flow<List<IssuesResponse>>

     fun getAllRepos(): Flow<List<RepositoryEntity>>
     suspend fun insertAll(repos: List<RepositoryEntity>)
     suspend fun updateStarCount(repoId: Int, newStarCount: Int)
     suspend fun cashRepos():  Flow<List<RepositoryEntity>>
     suspend fun getStars(owner: String, repo: String): Flow<List<RepositoryEntity>>

}