package com.purlewave.ui.home

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.purlewave.R
import com.purlewave.adapters.FilesAdapter
import com.purlewave.adapters.RecordsAdapter
import com.purlewave.databinding.FragmentAllFilesBinding
import com.purlewave.databinding.FragmentFilesBinding
import com.purlewave.util.observeLiveData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllFilesFragment : Fragment() {
    private lateinit var binding: FragmentAllFilesBinding
    private val filesViewModel: FilesViewModel by viewModels()


    private lateinit var listAdapterFiles: FilesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAllFilesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initHistoryRecyclerView()

        observeLiveData(filesViewModel.allFileRecordResponse){
            listAdapterFiles.submitList(it)
        }

        filesViewModel.getAllFiles()



    }

    private fun initHistoryRecyclerView() {
        listAdapterFiles = FilesAdapter {
//            val action = AllFilesFragmentDirections.actionAllFilesFragmentToWebViewFragment(it)
//            findNavController().navigate(action)
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(Uri.parse("${it.location}"), "application/pdf")
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                // Handle the exception here if no suitable PDF viewer application is found
            }
        }
        binding.rvRecords.apply {
            adapter = listAdapterFiles
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}