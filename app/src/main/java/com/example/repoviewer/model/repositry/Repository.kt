package com.example.repoviewer.model.repositry

import android.content.Context
import com.example.repoviewer.model.DetailsResponse
import com.example.repoviewer.model.IssuesResponse
import com.example.repoviewer.model.RepositoriesResponse
import com.example.repoviewer.network.RemoteSource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteSource: RemoteSource,
    @ApplicationContext val context: Context
 ) : RepositoryInterface {

    override suspend fun getRepositories(): Flow<List<RepositoriesResponse>> {
        return flowOf(remoteSource.getRepositories())
    }

    override suspend fun getRepoDetails(owner: String, repo: String): Flow<DetailsResponse> {
        return flowOf(remoteSource.getRepoDetails(owner, repo))
    }

    override suspend fun getRepoIssues(owner: String, repo: String): Flow<List<IssuesResponse>> {
        return flowOf(remoteSource.getRepoIssues(owner, repo))
    }
}