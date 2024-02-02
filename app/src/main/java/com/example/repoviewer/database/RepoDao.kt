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

    @Query("SELECT * FROM Repositories")
    fun getAllRepos(): Flow<List<RepositoryEntity>>

    @Query("UPDATE Repositories SET starCount = :newStarCount WHERE id = :repoId")
    suspend fun updateStarCount(repoId: Int, newStarCount: Int)
}