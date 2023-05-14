package com.example.patientappv06.domain.usecase.patients

import com.example.patientappv06.domain.models.patients.PatientDataModel
import com.example.patientappv06.domain.repo.PatientRepo
import javax.inject.Inject

class getPatientsSortedByNameUseCase @Inject constructor(
    //Here when I called PatientRepo it's giving me PatientRepoImpl(patientDataSource) since I already provided it
    private val repo: PatientRepo
) {
    suspend operator fun invoke(): List<PatientDataModel> {
        return repo.getPatients().data.sortedBy{ it.name }
    }
}