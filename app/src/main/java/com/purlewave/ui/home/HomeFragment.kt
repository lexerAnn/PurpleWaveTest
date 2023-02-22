package com.purlewave.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.purlewave.adapters.RecordsAdapter
import com.purlewave.data.local.room.tables.SampleEntity
import com.purlewave.databinding.FragmentHomeBinding
import com.purlewave.util.observeLiveData
import com.purlewave.util.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val recordViewModel: RecordViewModel by viewModels()

    private lateinit var listAdapterRecords: RecordsAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initHistoryRecyclerView()
        recordViewModel.getAllRecord()

        with(binding){
            addRecordFloatBtn.setOnClickListener {
                findNavController().navigate(HomeFragmentDirections.navigateToAddRecordFragment())
            }
        }


        observeLiveData(recordViewModel.allRecordResponse){
            listAdapterRecords.submitList(it)
        }
    }

    private fun initHistoryRecyclerView() {
        listAdapterRecords = RecordsAdapter {
            val action = HomeFragmentDirections.actionHomeFragmentToFilesFragment(it)
            findNavController().navigate(action)
        }
        binding.rvRecords.apply {
            adapter = listAdapterRecords
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}