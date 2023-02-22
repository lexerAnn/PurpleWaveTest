package com.purlewave.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class RecordModel(
    val id: Int,
    val key: String,
    val name: String,
    val description: String?,
    val priority: Int = 2,
    val archived: Boolean? = false,
    val updatedAt: String,
    val createdAt: String
): Parcelable
