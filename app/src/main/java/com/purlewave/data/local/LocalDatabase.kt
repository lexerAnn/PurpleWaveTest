package com.purlewave.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.purlewave.data.local.room.LocalDao
import com.purlewave.data.local.room.tables.SampleEntity


@Database(entities = [SampleEntity::class], version = 2)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun localDao(): LocalDao

    companion object {
        const val DATABASE_NAME: String = "kola_market_db"
    }

}