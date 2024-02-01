package com.example.repoviewer.model

import com.example.repoviewer.network.RemoteSource
import kotlinx.coroutines.flow.Flow

interface RepositoryInterface  {
     suspend fun getRepositories(): Flow<RepositoriesResponse>

     suspend fun getRepoDetails(owner: String, repo: String): Flow<DetailsResponse>

     suspend fun getRepoIssues(owner: String, repo: String): Flow<IssuesResponse>

}