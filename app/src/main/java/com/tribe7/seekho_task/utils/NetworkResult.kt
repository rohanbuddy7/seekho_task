package com.tribe7.seekho_task.utils

sealed class NetworkResult<T>(data: T?, message: String?) {
    class Loading<T>: NetworkResult<T>(data = null, message = null)
    data class Success<T>(var data: T): NetworkResult<T>(data, "")
    data class Error<T>(var message: String): NetworkResult<T>(null, message = message )
}