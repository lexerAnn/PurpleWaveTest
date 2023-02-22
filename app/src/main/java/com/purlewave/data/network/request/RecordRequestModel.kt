package com.purlewave.data.network.request

import java.util.UUID

data class RecordRequestModel(
    val key: UUID,
    val name: String,
    val description: String?,
    val priority: Int = 2,
    val archived: Boolean? = false,
    val updatedAt: String,
    val createdAt: String
)
