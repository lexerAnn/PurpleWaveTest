package com.purlewave.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class FileModel(
    val id: Int?,
    val key: String?,
    val name: String?,
    val size: String?,
    val location: String?,
    val uploadedAt: String?,
    val recordId: String?
): Parcelable
