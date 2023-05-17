package com.example.patientappv06.presentation.features.patients

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.patientappv06.domain.models.delete.PatientDeleteResponseModel
import com.example.patientappv06.domain.models.patients.PatientDataModel
import com.example.patientappv06.presentation.R
import com.example.patientappv06.presentation.databinding.FragmentPatientBinding
import com.example.patientappv06.presentation.features.patients.adapter.PatientAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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
        adapter = PatientAdapter(::deletePatient,::onClickItem)
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
            viewModel.patientStateFlow.collect(::onPatientsSuccess)
        }

        lifecycleScope.launch {
            viewModel.loadingStateFlow.collect(::onLoadingSuccess)
        }

        lifecycleScope.launch {
            viewModel.errorStateFlow.collect(::onErrorSuccess)
        }

        lifecycleScope.launch {
            viewModel.deletePatientStateFlow.observe(viewLifecycleOwner,::onDeleteSuccess)
        }
    }
    private fun onPatientsSuccess(response:List<PatientDataModel?>){
        if(response.isNotEmpty()){
            adapter.submitList(response)
        }
    }
    private fun onLoadingSuccess(visible:Boolean?){
        if(visible != null){
            binding.progressLoading.isVisible = visible
        }
    }
    private fun onErrorSuccess(error:Exception?){
        if(error != null){
            Toast.makeText(requireContext(),error.message.toString(),Toast.LENGTH_LONG).show()
        }
    }

    private fun onDeleteSuccess(response:PatientDeleteResponseModel?){
        if(response != null){
            Toast.makeText(requireContext(),response.message,Toast.LENGTH_LONG).show()
            viewModel.getPatients()
        }
    }



    private fun deletePatient(id:String){
        MaterialAlertDialogBuilder(requireContext())
            .setMessage("are yous sure do you want delete this item?")
            .setNegativeButton("no"){dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton("yes"){dialog, _ ->
                viewModel.deletePatient(id)
                dialog.dismiss()
            }.show()
    }


    fun onClickItem(id: String){
        findNavController().navigate(R.id.detailsPatientFragment, bundleOf("id" to id))
    }
}