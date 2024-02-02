package com.example.repoviewer.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.repoviewer.model.RepositoryEntity

@Database(entities = [RepositoryEntity::class], version = 1)
abstract class RepoDataBase: RoomDatabase() {
        abstract fun repoDao(): RepoDao

    }
