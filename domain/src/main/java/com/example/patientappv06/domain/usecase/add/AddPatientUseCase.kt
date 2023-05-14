package com.example.patientappv06.domain.usecase.add

import com.example.patientappv06.domain.models.add.AddPatientModel
import com.example.patientappv06.domain.models.add.AddPatientResponseModel
import com.example.patientappv06.domain.repo.PatientRepo
import javax.inject.Inject

class AddPatientUseCase @Inject constructor(private val repo: PatientRepo) {

    suspend operator fun invoke(addPatientModel: AddPatientModel):AddPatientResponseModel{
        return repo.addPatient(addPatientModel)
    }
}