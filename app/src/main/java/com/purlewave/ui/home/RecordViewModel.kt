package com.purlewave.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kola.basic.util.generics.DataState
import com.purlewave.data.models.RecordModel
import com.purlewave.data.network.request.RecordRequestModel
import com.purlewave.repository.NetworkRepository
import com.purlewave.util.emitFlowResults
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor(
    private val networkRepository: NetworkRepository
): ViewModel() {

    private val _createRecordResponse: MutableLiveData<DataState<RecordModel>> = MutableLiveData()
    val createRecordResponse: LiveData<DataState<RecordModel>> get() = _createRecordResponse

    private val _allRecordResponse: MutableLiveData<DataState<List<RecordModel>>> = MutableLiveData()
    val allRecordResponse: LiveData<DataState<List<RecordModel>>> get() = _allRecordResponse


    fun createRecord(recordRequestModel: RecordRequestModel) = emitFlowResults(_createRecordResponse) {
        networkRepository.createRecord(recordRequestModel)
    }

    fun getAllRecord() = emitFlowResults(_allRecordResponse) {
        networkRepository.getAllRecord()
    }

}