package com.purlewave.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.purlewave.R
import com.purlewave.data.models.FileModel
import com.purlewave.databinding.FragmentFilesBinding
import com.purlewave.databinding.FragmentHomeBinding
import com.purlewave.util.observeLiveData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilesFragment : Fragment() {

    private val args: FilesFragmentArgs by navArgs()

    private lateinit var binding: FragmentFilesBinding
    private val fileViewModel: FilesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFilesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fileViewModel.getFileRecord(args.recordDetails?.id.toString())

        with(binding){
            args.recordDetails?.let {
                nameDetails.text = it.name
                description.text = it.description
            }
            back.setOnClickListener {
                findNavController().popBackStack()
            }
        }
        observeLiveData(fileViewModel.fileRecordResponse){
//            toast(it.toString())
            with(binding){
                val emptyFile = it as ArrayList<*>
                if (emptyFile.isEmpty()){
                    nameFile.visibility = View.GONE
                    fileLink.text = "Empty File"
                } else {
                    val data  = it as FileModel
                    nameFile.text = it.name
                    fileLink.text = it.location
                }
            }
        }


    }


}