package com.alexdevyatov.testtask.model

import com.google.gson.annotations.SerializedName

data class CompanyInfo (
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name: String,
    @SerializedName("img") val image: String,
    @SerializedName("description") val description: String,
    @SerializedName("lat") val lat: Double,
    @SerializedName("lon") val lon: Double,
    @SerializedName("www") val url: String,
    @SerializedName("phone") val phone: String
)