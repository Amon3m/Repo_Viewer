package com.example.repoviewer.repo.view

import com.example.repoviewer.model.RepositoriesResponse
import java.text.FieldPosition

interface OnRepoClickListener {
    fun onRepoClick( repositoriesResponse: RepositoriesResponse?)
    fun onShowStars(repositoriesResponse: RepositoriesResponse?,position: Int)
}
