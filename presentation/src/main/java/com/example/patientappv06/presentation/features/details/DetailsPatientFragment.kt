package com.example.patientappv06.presentation.features.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.patientappv06.presentation.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsPatientFragment:Fragment() {
    private lateinit var binding:FragmentDetailsBinding
    private val viewModel:DetailsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.detailsStateFlow.collect{response ->
                if(response !=null){
                    binding.model = response
                }
            }
        }


        lifecycleScope.launch {
            viewModel.loadingStateFlow.collect{visible ->
                binding.progressCircular.isVisible = visible
            }
        }

        lifecycleScope.launch {
            viewModel.errorStateFlow.collect{error ->
                if(error != null){
                    Toast.makeText(requireContext(),error.message.toString(), Toast.LENGTH_LONG).show()

                }
            }
        }
    }
}