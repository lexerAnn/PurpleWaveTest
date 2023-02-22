package com.purlewave.ui.home

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.purlewave.R
import com.purlewave.databinding.FragmentAllFilesBinding
import com.purlewave.databinding.FragmentWebViewBinding
import com.purlewave.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebViewFragment : Fragment() {
    private lateinit var binding: FragmentWebViewBinding
    private val args: WebViewFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWebViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadPDF()
    }

    fun loadPDF() {

        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(Uri.parse("${args.fileDetails?.location}"), "application/pdf")
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            // Handle the exception here if no suitable PDF viewer application is found
        }
    }


}