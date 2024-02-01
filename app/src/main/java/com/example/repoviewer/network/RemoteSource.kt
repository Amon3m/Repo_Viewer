package com.example.repoviewer.network

import com.example.repoviewer.model.DetailsResponse
import com.example.repoviewer.model.IssuesResponse
import com.example.repoviewer.model.RepositoriesResponse
interface RemoteSource {
    suspend fun getRepositories(): RepositoriesResponse

    suspend fun getRepoDetails( owner: String, repo: String): DetailsResponse
    suspend fun getRepoIssues(owner: String,
                              repo: String): IssuesResponse

}