package com.example.patientappv06.data.repo

import com.example.patientappv06.data.datasource.PatientDataSource
import com.example.patientappv06.domain.models.add.AddPatientModel
import com.example.patientappv06.domain.models.add.AddPatientResponseModel
import com.example.patientappv06.domain.models.patients.PatientsListModels
import com.example.patientappv06.domain.repo.PatientRepo
import javax.inject.Inject

class PatientRepoImpl @Inject constructor(private val patientDataSource: PatientDataSource):PatientRepo {

    override suspend fun getPatients(): PatientsListModels {
        return patientDataSource.getPatients()
    }

    override suspend fun addPatient(addPatientModel: AddPatientModel): AddPatientResponseModel {
        return patientDataSource.addPatient(addPatientModel)
    }

}