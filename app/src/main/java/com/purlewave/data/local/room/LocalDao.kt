package com.purlewave.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.purlewave.data.local.room.tables.SampleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertSample(sampleEntity: SampleEntity): Long

    @Query("SELECT * FROM sample_entity WHERE name = :name")
    fun getAllSampleEntity(name: String):  Flow<MutableList<SampleEntity>>

}