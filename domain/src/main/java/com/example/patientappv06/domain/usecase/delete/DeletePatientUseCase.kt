package com.example.patientappv06.domain.usecase.delete

import com.example.patientappv06.domain.models.delete.PatientDeleteResponseModel
import com.example.patientappv06.domain.repo.PatientRepo
import javax.inject.Inject

class DeletePatientUseCase @Inject constructor(private val repo: PatientRepo) {
    suspend operator fun invoke(id:String):PatientDeleteResponseModel{
        return repo.deletePatient(id)
    }
}