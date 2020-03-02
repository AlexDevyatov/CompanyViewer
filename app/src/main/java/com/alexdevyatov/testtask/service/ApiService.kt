package com.alexdevyatov.testtask.service

import com.alexdevyatov.testtask.model.Company
import com.alexdevyatov.testtask.model.CompanyInfo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("test.php")
    fun getCompaniesList() : Single<List<Company> >

    @GET("test.php")
    fun getCompanyInfo(
        @Query("id") id: Int
    ) : Single<List<CompanyInfo>>
}