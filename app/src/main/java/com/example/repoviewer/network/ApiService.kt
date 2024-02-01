package com.example.repoviewer.network

import com.example.repoviewer.model.DetailsResponse
import com.example.repoviewer.model.IssuesResponse
import com.example.repoviewer.model.RepositoriesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("repositories")
    suspend fun getRepositories(): RepositoriesResponse

    @GET("/repos/{owner}/{repo}")
    suspend fun getRepoDetails(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): DetailsResponse

    @GET("repos/{owner}/{repo}/issues")
    suspend fun getRepoIssues(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): IssuesResponse
}