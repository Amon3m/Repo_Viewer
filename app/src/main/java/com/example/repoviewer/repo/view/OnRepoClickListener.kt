package com.example.repoviewer.repo.view

import com.example.repoviewer.model.RepositoriesResponse
import com.example.repoviewer.model.RepositoryEntity
import java.text.FieldPosition

interface OnRepoClickListener {
    fun onRepoClick( repositoryEntity: RepositoryEntity?)
    fun onShowStars(repositoryEntity: RepositoryEntity?)
}
