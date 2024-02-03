package com.example.repoviewer.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.repoviewer.model.RepositoryEntity
import kotlinx.coroutines.flow.Flow
@Dao
interface RepoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repos: List<RepositoryEntity>)

    @Query("SELECT * FROM repositories_table")
    fun getAllRepos(): Flow<List<RepositoryEntity>>

    @Query("UPDATE repositories_table SET starCount = :newStarCount WHERE id = :repoId")
    suspend fun updateStarCount(repoId: Int, newStarCount: Int)

    @Query("SELECT * FROM repositories_table WHERE name LIKE :query OR owner LIKE :query")
    fun searchRepos(query: String): Flow<List<RepositoryEntity>>
}