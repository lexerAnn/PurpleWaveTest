package com.purlewave.data.network

import com.purlewave.data.models.FileModel
import com.purlewave.data.models.RecordModel
import com.purlewave.data.models.SampleModels
import com.purlewave.data.network.request.RecordRequestModel
import retrofit2.Response
import retrofit2.http.*
import java.io.File

interface NetworkApi {

    @GET("posts")
    suspend fun getSample(): Response<List<SampleModels>>

    @GET("records")
    suspend fun getRecords(): Response<List<RecordModel>>

    @GET("files")
    suspend fun getAllFiles(): Response<List<FileModel>>

    @POST("records")
    suspend fun createRecord(@Body recordRequestModel: RecordRequestModel): Response<RecordModel>

    @GET("records/{id}/files")
    suspend fun getFilesOnRecord(@Path("id") id: String): Response<Any>
}