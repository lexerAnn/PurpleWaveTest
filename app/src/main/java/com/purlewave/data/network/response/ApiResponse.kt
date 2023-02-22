package com.kola.basic.data.network.response

data class  ApiResponse<T>(
    val status: String,
    val type: String,
    val message: String,
    val data: T,
    val error: Any,
)
