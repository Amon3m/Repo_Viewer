package com.example.repoviewer.repo.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.repoviewer.model.repositry.RepositoryInterface
import com.example.repoviewer.network.ApiState
import com.example.utils.NetworkUtils
import com.example.utils.NetworkUtils.isNetworkAvailable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepoViewModel @Inject constructor(private val repo: RepositoryInterface,
    ) :ViewModel(){
    private val _repositories = MutableStateFlow<ApiState>(ApiState.Loading)
    val repositories: StateFlow<ApiState>
        get() = _repositories

    fun getRepositories(context: Context) {
        if (isNetworkAvailable(context)){
        viewModelScope.launch(Dispatchers.IO) {

            _repositories.emit(ApiState.Loading)

            repo.cashRepos().catch { e ->
                _repositories.emit(ApiState.Failure(e.message ?: ""))
            }.collect {
                _repositories.emit(ApiState.Success(it))
            }

        }}
        else{
            viewModelScope.launch(Dispatchers.IO) {
                _repositories.emit(ApiState.Loading)

                repo.getAllRepos().catch { e ->
                    _repositories.emit(ApiState.Failure(e.message ?: ""))
                }.collect {
                    _repositories.emit(ApiState.Success(it))
                }

            }
        }
    }
    fun getStars(owner: String, repoName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _repositories.emit(ApiState.Loading)

            repo.getStars(owner,repoName).catch { e ->
                _repositories.emit(ApiState.Failure(e.message ?: ""))
            }.collect {
                _repositories.emit(ApiState.Success(it))
            }

        }
    }

}