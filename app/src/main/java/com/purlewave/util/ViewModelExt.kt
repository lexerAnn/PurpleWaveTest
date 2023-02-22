package com.purlewave.util

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kola.basic.util.Event
import com.kola.basic.util.generics.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

fun <T> ViewModel.emitFlowResultsToEvent(
        liveDataObject: MutableLiveData<Event<DataState<T>>>,
        networkRequest: () -> Flow<DataState<T>>
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            networkRequest()
                .onStart { liveDataObject.postValue(Event(DataState.Loading)) }
                .onEach {
                    liveDataObject.postValue(Event(it))
                }
                .catch {
                    liveDataObject.postValue(Event(DataState.Error(Exception(it.localizedMessage))))
                }
                .launchIn(this)
        }
    }

fun <T> ViewModel.emitFlowResults(
    liveDataObject: MutableLiveData<DataState<T>>,
    networkRequest: () -> Flow<DataState<T>>
) {
    viewModelScope.launch(Dispatchers.IO) {
        networkRequest()
            .onStart { liveDataObject.postValue(DataState.Loading) }
            .onEach {
                liveDataObject.postValue(it)
            }
            .catch {
                liveDataObject.postValue(DataState.Error(Exception(it.localizedMessage)))
            }
            .launchIn(this)

    }
}
