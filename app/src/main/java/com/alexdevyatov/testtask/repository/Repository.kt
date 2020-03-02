package com.alexdevyatov.testtask.repository

import com.alexdevyatov.testtask.model.Company
import com.alexdevyatov.testtask.model.CompanyInfo
import com.alexdevyatov.testtask.service.ApiService
import io.reactivex.Single

class Repository(private val service: ApiService) {

    fun getCompaniesList(): Single<List<Company>> = service.getCompaniesList()

    fun getCompanyInfo(id: Int): Single<CompanyInfo> = service.getCompanyInfo(id)
}