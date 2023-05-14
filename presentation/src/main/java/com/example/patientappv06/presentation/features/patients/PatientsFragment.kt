package com.example.patientappv06.presentation.features.patients

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.patientappv06.presentation.R
import com.example.patientappv06.presentation.databinding.FragmentPatientBinding
import com.example.patientappv06.presentation.features.patients.adapter.PatientAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PatientsFragment : Fragment() {

    private val viewModel: PatientViewModel by viewModels()
    private lateinit var binding: FragmentPatientBinding
    private lateinit var adapter: PatientAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPatientBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initObserver()
        initListener()
    }

    private fun initAdapter() {
        adapter = PatientAdapter()
        binding.recycleView.adapter = adapter
    }

    private fun initListener() {
        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.addPatientFragment)
        }
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getPatients()
            lifecycleScope.launch {
                delay(3000)
                binding.swipeRefresh.isRefreshing = false
            }
        }
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.patientStateFlow.collect{
                if (it.isNotEmpty()){
                    adapter.submitList(it)
                }
            }
        }

        lifecycleScope.launch {
            viewModel.loadingStateFlow.collect{
                binding.progressLoading.isVisible = it
            }
        }

        lifecycleScope.launch {
            viewModel.errorStateFlow.collect{
                if(it != null){
                    Toast.makeText(requireContext(),it.message.toString(),Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}