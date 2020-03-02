package com.alexdevyatov.testtask.di

import com.alexdevyatov.testtask.viewmodel.CompaniesViewModel
import com.alexdevyatov.testtask.viewmodel.CompanyInfoViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetModule::class])
interface AppComponent {
    fun inject(companiesViewModel: CompaniesViewModel)

    fun inject(companyInfoViewModel: CompanyInfoViewModel)
}