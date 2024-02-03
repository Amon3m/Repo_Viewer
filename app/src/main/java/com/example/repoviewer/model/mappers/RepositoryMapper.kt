package com.example.repoviewer.model.mappers

import com.example.repoviewer.model.DetailsResponse
import com.example.repoviewer.model.RepositoriesResponse
import com.example.repoviewer.model.RepositoryEntity

fun RepositoriesResponse.toRepositoryEntity(): RepositoryEntity =
    RepositoryEntity(
        id = this.id,
        name = this.name,
        owner=this.owner?.login,
        description=this.description
    )
fun DetailsResponse.toRepositoryEntity(): RepositoryEntity =
    RepositoryEntity(starCount = this.stargazersCount.toString())
