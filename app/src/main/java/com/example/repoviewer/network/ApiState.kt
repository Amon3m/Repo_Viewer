package com.example.repoviewer.network

sealed class ApiState{
    class Success<T>(val data : T) : ApiState()
    class Failure(val msg: String) : ApiState()
    object Loading : ApiState()
}
