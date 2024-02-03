package com.example.repoviewer.database

import com.example.repoviewer.model.RepositoryEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ConcreteLocalSource  @Inject constructor (private val dao: RepoDao) :LocalSource {
    override fun getAllRepos(): Flow<List<RepositoryEntity>> {
      return  dao.getAllRepos()
    }

    override suspend fun insertAll(repos: List<RepositoryEntity>) {
        dao.insertAll(repos)
    }

    override suspend fun updateStarCount(repoId: Int, newStarCount: Int) {
        dao.updateStarCount(repoId,newStarCount)
    }

    override fun searchRepos(query: String): Flow<List<RepositoryEntity>> {
        return dao.searchRepos("%$query%")
    }


}