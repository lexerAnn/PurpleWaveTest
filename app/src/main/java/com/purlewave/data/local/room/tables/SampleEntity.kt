package com.purlewave.data.local.room.tables

import androidx.room.*

@Entity(
    tableName = "sample_entity"
)
data class SampleEntity(
    @PrimaryKey
    val id: Long,
    val name: String
)
