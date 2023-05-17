package com.example.patientappv06.domain.usecase.get

import com.example.patientappv06.domain.models.get.GetPatientResponseModel
import com.example.patientappv06.domain.models.patients.PatientDataModel
import com.example.patientappv06.domain.repo.PatientRepo
import javax.inject.Inject

class GetPatientByIdUseCase @Inject constructor(private val repo: PatientRepo) {
    suspend operator fun invoke(id:String): PatientDataModel {
        return repo.getPatient(id)
    }
}