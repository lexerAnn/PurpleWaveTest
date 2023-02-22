package com.purlewave.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.purlewave.data.models.SampleModels
import com.kola.basic.util.generics.DataState
import com.purlewave.data.local.room.tables.SampleEntity
import com.purlewave.data.models.FileModel
import com.purlewave.data.models.RecordModel
import com.purlewave.data.network.request.RecordRequestModel
import com.purlewave.repository.NetworkRepository
import com.purlewave.util.emitFlowResults
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilesViewModel @Inject constructor(
    private val networkRepository: NetworkRepository
): ViewModel() {

    private val _fileRecordResponse: MutableLiveData<DataState<Any>> = MutableLiveData()
    val fileRecordResponse: LiveData<DataState<Any>> get() = _fileRecordResponse

    private val _allFileRecordResponse: MutableLiveData<DataState<List<FileModel>>> = MutableLiveData()
    val allFileRecordResponse: LiveData<DataState<List<FileModel>>> get() = _allFileRecordResponse



    fun getFileRecord(id: String) = emitFlowResults(_fileRecordResponse) {
        networkRepository.getFileOnRecord(id)
    }

    fun getAllFiles() = emitFlowResults(_allFileRecordResponse) {
        networkRepository.getAllFiles()
    }

}