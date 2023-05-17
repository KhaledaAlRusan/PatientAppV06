package com.example.patientappv06.domain.repo

import com.example.patientappv06.domain.models.add.AddPatientModel
import com.example.patientappv06.domain.models.add.AddPatientResponseModel
import com.example.patientappv06.domain.models.delete.PatientDeleteResponseModel
import com.example.patientappv06.domain.models.patients.PatientsListModels

interface PatientRepo {

    suspend fun getPatients(): PatientsListModels

    suspend fun addPatient(addPatientModel: AddPatientModel): AddPatientResponseModel

    suspend fun deletePatient(id:String):PatientDeleteResponseModel
}