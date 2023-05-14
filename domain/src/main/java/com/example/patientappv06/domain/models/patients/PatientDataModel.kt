package com.example.patientappv06.domain.models.patients

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PatientDataModel(
    @Json(name ="address")
    val address: String ,
    @Json(name ="birthdate")
    val birthdate: String ,
    @Json(name = "condition")
    val condition: String ,
    @Json(name = "createdAt")
    val createdAt: String ,
    @Json(name = "email")
    val email: String ,
    @Json(name = "gender")
    val gender: String ,
    @Json(name = "_id")
    val id: String ,
    @Json(name = "mobile")
    val mobile: String ,
    @Json(name = "name")
    val name: String ,
    @Json(name = "photo")
    val photo: String ,
    @Json(name = "tests")
    val tests: List<Any> ,
    @Json(name = "updatedAt")
    val updatedAt: String ,
    @Json(name = "__v")
    val v: Int ,


    //Local var
    var selected:Boolean = false
)
