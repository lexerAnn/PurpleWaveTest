package com.purlewave.util

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.kola.basic.util.generics.DataState
import timber.log.Timber

fun <T> Fragment.observeLiveData(
    liveData: LiveData<DataState<T>>,
    enableProgressBar: Boolean = true,
    onError: (() -> Unit)? = null,
    onSuccess: (T) -> Unit
) {
    liveData.observe(viewLifecycleOwner) { dataState ->
        when (dataState) {
            is DataState.Loading -> {
                Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
            }
            is DataState.Success -> {
                dataState.data?.let { onSuccess(it) }
            }
            is DataState.Error -> {
                Toast.makeText(requireContext(), "${dataState.exception.localizedMessage}}", Toast.LENGTH_SHORT).show()
                Timber.tag("LoginResponse").d(dataState.exception.localizedMessage)
            }
        }

    }
}

fun Fragment.toast(msg: String){
    Toast.makeText(this.requireContext(), msg, Toast.LENGTH_SHORT).show()

}
