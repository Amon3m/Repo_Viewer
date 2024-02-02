package com.example.repoviewer.repo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.repoviewer.model.RepositoryInterface
import com.example.repoviewer.network.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepoViewModel @Inject constructor(private val repo: RepositoryInterface) :ViewModel(){
    private val _repositories = MutableStateFlow<ApiState>(ApiState.Loading)
    val repositories: StateFlow<ApiState>
        get() = _repositories

    private val _details = MutableStateFlow<ApiState>(ApiState.Loading)
    val details: StateFlow<ApiState>
        get() = _details


    init {
        getRepositories()
    }

    fun getRepositories() {
        viewModelScope.launch(Dispatchers.IO) {
            _repositories.emit(ApiState.Loading)

            repo.getRepositories().catch { e ->
                _repositories.emit(ApiState.Failure(e.message ?: ""))
            }.collect {
                _repositories.emit(ApiState.Success(it))
            }

        }
    }
    fun getDetails(owner: String, repoName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _details.emit(ApiState.Loading)

            repo.getRepoDetails(owner,repoName).catch { e ->
                _details.emit(ApiState.Failure(e.message ?: ""))
            }.collect {
                _details.emit(ApiState.Success(it))
            }

        }
    }

}