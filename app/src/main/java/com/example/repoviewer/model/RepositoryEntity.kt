package com.example.repoviewer.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.utils.Constants.REPO_TABLE

@Entity(tableName = REPO_TABLE)

data class RepositoryEntity(
    @PrimaryKey
    @ColumnInfo
    val id: Int?= null,
    @ColumnInfo
    val name: String?= null,
    @ColumnInfo
    val owner: String?= null,
    @ColumnInfo
    val description: String?= null,
    @ColumnInfo
    val starCount: String? = null
)
