package com.example.repoviewer.issues.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.repoviewer.model.repositry.RepositoryInterface
import com.example.repoviewer.network.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IssuesViewModel @Inject constructor(private val repo: RepositoryInterface) : ViewModel(){

    private val _issues = MutableStateFlow<ApiState>(ApiState.Loading)
    val issues: StateFlow<ApiState>
        get() = _issues


    fun getIssues(owner: String, repoName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _issues.emit(ApiState.Loading)
            repo.getRepoIssues(owner,repoName).catch { e ->
                _issues.emit(ApiState.Failure(e.message ?: ""))
            }.collect {
                _issues.emit(ApiState.Success(it))
            }

        }
    }
}