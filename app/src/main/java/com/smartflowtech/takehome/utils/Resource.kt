package com.smartflowtech.takehome.utils

sealed class Resource<T> (var data: T? = null, var message: String? = null) {
    class Loading<T>() : Resource<T>()
    class Success<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(data: T?, message: String?) : Resource<T>(data, message)
}
