package com.example.patientappv06.data.datasource

import com.example.patientappv06.domain.models.add.AddPatientModel
import com.example.patientappv06.domain.models.add.AddPatientResponseModel
import com.example.patientappv06.domain.models.patients.PatientsListModels
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface PatientDataSource {
    @GET("patients")
    suspend fun getPatients(): PatientsListModels


    //I specified here that I'm gonna send body since there all a lot of data that I can send and body is one of them
    @POST("patients")
    suspend fun addPatient(@Body addPatientModel: AddPatientModel): AddPatientResponseModel
}