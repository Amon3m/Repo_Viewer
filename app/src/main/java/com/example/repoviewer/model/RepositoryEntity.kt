package com.example.repoviewer.model

data class RepositoryEntity(
    val id: Int?= null,
    val name: String?= null,
    val owner: String?= null,
    val description: String?= null,
    val starCount: Int? = null
)
