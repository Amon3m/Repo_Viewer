package com.example.repoviewer.network

import com.example.repoviewer.model.DetailsResponse
import com.example.repoviewer.model.IssuesResponse
import com.example.repoviewer.model.RepositoriesResponse
import javax.inject.Inject

class ApiClient @Inject constructor(private val apiService: ApiService):RemoteSource {
    override suspend fun getRepositories(): List<RepositoriesResponse> =apiService.getRepositories()

    override suspend fun getRepoDetails(owner: String, repo: String): DetailsResponse
    =apiService.getRepoDetails(owner,repo)

    override suspend fun getRepoIssues(owner: String, repo: String): List<IssuesResponse>
    =apiService.getRepoIssues(owner,repo)

}