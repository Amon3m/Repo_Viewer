package com.example.repoviewer.repodetails.viewmodel

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
class DetailsViewModel @Inject constructor(private val repo: RepositoryInterface) : ViewModel(){

    private val _details = MutableStateFlow<ApiState>(ApiState.Loading)
    val details: StateFlow<ApiState>
        get() = _details


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