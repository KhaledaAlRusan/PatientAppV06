package com.example.patientappv06.domain.models.get

import com.example.patientappv06.domain.models.patients.PatientDataModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetPatientResponseModel (
    @Json(name = "data")
    val `data`: PatientDataModel,
    @Json(name = "message")
    val message: String,
    @Json(name = "status")
    val status: Int
)