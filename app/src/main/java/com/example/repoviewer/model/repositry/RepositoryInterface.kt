package com.example.repoviewer.model.repositry

import com.example.repoviewer.model.DetailsResponse
import com.example.repoviewer.model.IssuesResponse
import com.example.repoviewer.model.RepositoriesResponse
import kotlinx.coroutines.flow.Flow

interface RepositoryInterface  {
     suspend fun getRepositories(): Flow<List<RepositoriesResponse>>

     suspend fun getRepoDetails(owner: String, repo: String): Flow<DetailsResponse>

     suspend fun getRepoIssues(owner: String, repo: String): Flow<List<IssuesResponse>>

}