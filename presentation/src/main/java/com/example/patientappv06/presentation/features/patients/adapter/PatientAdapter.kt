package com.example.patientappv06.presentation.features.patients.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.patientappv06.domain.models.patients.PatientDataModel
import com.example.patientappv06.presentation.databinding.RowPatientBinding

class PatientAdapter(private val onDeletePatient:(id:String) ->Unit) :
    ListAdapter< PatientDataModel, PatientAdapter.PatientViewHolder>(DiffCallback) {

    var lastSelected = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        val binding = RowPatientBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PatientViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        val patient =getItem(position)
            holder.bind(patient,position)
    }

    inner class PatientViewHolder(private val binding: RowPatientBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(model: PatientDataModel, position: Int){
            binding.model = model

            binding.cardView.setOnClickListener {
                if(lastSelected != position){

                    if(lastSelected != -1){
                        getItem(lastSelected).selected = false
                        notifyItemChanged(lastSelected)
                    }

                    lastSelected = position
                    getItem(position).selected = true
                    notifyItemChanged(position)
                }
            }
            binding.ivDelete.setOnClickListener {
                onDeletePatient(model.id)
            }
        }

    }


    private object DiffCallback:DiffUtil.ItemCallback<PatientDataModel>(){
        override fun areItemsTheSame(
            oldItem: PatientDataModel,
            newItem: PatientDataModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PatientDataModel,
            newItem: PatientDataModel
        ): Boolean {
            return  oldItem == newItem
        }

    }


}