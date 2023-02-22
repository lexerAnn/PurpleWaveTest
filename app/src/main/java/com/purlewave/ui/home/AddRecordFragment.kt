package com.purlewave.ui.home

import android.os.Bundle
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.purlewave.data.models.RecordModel
import com.purlewave.data.network.request.RecordRequestModel
import com.purlewave.databinding.FragmentAddRecordBinding
import com.purlewave.util.getCurrentDate
import com.purlewave.util.observeLiveData
import com.purlewave.util.toast
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class AddRecordFragment : Fragment() {

    private lateinit var binding: FragmentAddRecordBinding
    private val recordViewModel: RecordViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddRecordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){

            back.setOnClickListener {
                findNavController().popBackStack()
            }
            observeLiveData(recordViewModel.createRecordResponse){
                Toast.makeText(requireContext(), "Record has been saved", Toast.LENGTH_SHORT).show()
            }


            addRecordBtn.setOnClickListener {
                val recordName = recordNameInput.text.toString()
                val recordDescription = recordDescriptionInput.text.toString()

                when{
                    recordName.isEmpty() -> toast("Enter record name")
                    recordDescription.isEmpty() -> toast("Enter a record description")

                    else -> {
                        val recordRequest = RecordRequestModel(key = UUID.randomUUID(), name =  recordName, description = recordDescription, createdAt = getCurrentDate(), updatedAt = getCurrentDate())
                        recordViewModel.createRecord(recordRequest)
                    }
                }
            }
        }

    }

}