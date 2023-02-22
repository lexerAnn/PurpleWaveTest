package com.purlewave.util

import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import com.kola.basic.util.generics.DataState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber
import java.io.IOException
import java.net.UnknownHostException

fun <T> processDataState(dispatcher: CoroutineDispatcher = Dispatchers.IO, request: suspend () -> Response<T>): Flow<DataState<T>> {
    return flow {
        try {
            emit(DataState.Loading)
            val apiResponse = request.invoke()
            val data = apiResponse.body()
            data?.let {
                emit(DataState.Success(it))
            }

        } catch (e: HttpException) {
            val body = e.response()?.errorBody()
//            val message = e.response()?.message()
            val rawResponse = e.response()?.raw()
            val code = e.response()?.code()
            val gson = GsonBuilder().setLenient().create()
        }
        catch (e: UnknownHostException){
            Timber.tag("requestException").e("$e")
            emit(DataState.Error(Exception("Please check your internet connection and try again")))
        }
        catch (e: IOException) {
            Timber.tag("requestException").e("$e")
            emit(DataState.Error(Exception("Check your internet and try again.")))
        }
        catch (e: JsonSyntaxException) {
            val errorMessage = "Timber: ${request.javaClass.enclosingMethod?.name} --> ${e.javaClass} --> \n ${e.localizedMessage}"
            Timber.tag("requestException").e(errorMessage)

            emit(DataState.Error(Exception("SOMETHING WENT WRONG")))
        }
    }.flowOn(dispatcher)
}