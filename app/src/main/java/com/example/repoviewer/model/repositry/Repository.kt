package com.example.repoviewer.model.repositry

import android.content.Context
import android.util.Log
import com.example.repoviewer.database.LocalSource
import com.example.repoviewer.model.DetailsResponse
import com.example.repoviewer.model.IssuesResponse
import com.example.repoviewer.model.RepositoriesResponse
import com.example.repoviewer.model.RepositoryEntity
import com.example.repoviewer.model.mappers.toRepositoryEntity
import com.example.repoviewer.network.RemoteSource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteSource: RemoteSource,
    private var concreteLocalSource: LocalSource,
    @ApplicationContext val context: Context
 ) : RepositoryInterface {

    override suspend fun getRepositoriesFromNetwork(): Flow<List<RepositoriesResponse>> {
        return flowOf(remoteSource.getRepositories())
    }

    override suspend fun getRepoDetails(owner: String, repo: String): Flow<DetailsResponse> {
        return flowOf(remoteSource.getRepoDetails(owner, repo))


    }

    override suspend fun getRepoIssues(owner: String, repo: String): Flow<List<IssuesResponse>> {
        return flowOf(remoteSource.getRepoIssues(owner, repo))
    }

    override fun searchRepos(query: String): Flow<List<RepositoryEntity>> {
        return concreteLocalSource.searchRepos(query)
    }

    override fun getAllRepos(): Flow<List<RepositoryEntity>> {
         val data= concreteLocalSource.getAllRepos()
        return data

    }

    override suspend fun insertAll(repos: List<RepositoryEntity>) {
        concreteLocalSource.insertAll(repos)
    }

    override suspend fun updateStarCount(repoId: Int, newStarCount: Int) {
         concreteLocalSource.updateStarCount(repoId, newStarCount)
    }

    override suspend fun cashRepos(): Flow<List<RepositoryEntity>> {
        getRepositoriesFromNetwork().collect{ data ->
            insertAll(data.map {
            it.toRepositoryEntity()
        })
        }
       return getAllRepos()
    }

    override suspend fun getStars(owner: String, repo: String) : Flow<List<RepositoryEntity>>{
    getRepoDetails(owner,repo).collect{
        val id = it.id ?:0
        val stargazersCount=it.stargazersCount ?:0
       updateStarCount(id,stargazersCount)

    }
        return getAllRepos()

    }
}