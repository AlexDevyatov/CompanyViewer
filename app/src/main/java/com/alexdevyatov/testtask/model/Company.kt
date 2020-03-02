package com.alexdevyatov.testtask.model

import com.google.gson.annotations.SerializedName

data class Company (
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name: String,
    @SerializedName("img") val image: String
)