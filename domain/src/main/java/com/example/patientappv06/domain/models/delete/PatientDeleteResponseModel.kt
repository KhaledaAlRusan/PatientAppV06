package com.example.patientappv06.domain.models.delete

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PatientDeleteResponseModel (
    @Json(name = "status")
    val status:Int,
    @Json(name = "message")
    val message:String,
    )