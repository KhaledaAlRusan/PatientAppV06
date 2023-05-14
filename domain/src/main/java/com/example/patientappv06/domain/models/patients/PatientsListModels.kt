package com.example.patientappv06.domain.models.patients

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PatientsListModels(
    @Json(name = "data")
    val `data`: List<PatientDataModel>,
    @Json(name = "message")
    val message: String,
    @Json(name = "status")
    val status: Int
)
