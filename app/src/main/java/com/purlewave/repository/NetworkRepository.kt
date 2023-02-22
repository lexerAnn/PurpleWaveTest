package com.purlewave.repository

import com.purlewave.data.models.SampleModels
import com.purlewave.data.network.NetworkApi
import com.kola.basic.util.generics.DataState
import com.purlewave.data.local.room.LocalDao
import com.purlewave.data.local.room.tables.SampleEntity
import com.purlewave.data.models.FileModel
import com.purlewave.data.models.RecordModel
import com.purlewave.data.network.request.RecordRequestModel
import com.purlewave.util.processDataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NetworkRepository @Inject constructor(
    private val networkApi: NetworkApi,
    private val localDao: LocalDao
){

    fun getAllRecord(): Flow<DataState<List<RecordModel>>>{
        return processDataState { networkApi.getRecords() }
    }

    fun createRecord(recordRequestModel: RecordRequestModel): Flow<DataState<RecordModel>>{
        return processDataState { networkApi.createRecord(recordRequestModel) }
    }

    fun getFileOnRecord(id: String): Flow<DataState<Any>>{
        return processDataState { networkApi.getFilesOnRecord(id) }
    }
}