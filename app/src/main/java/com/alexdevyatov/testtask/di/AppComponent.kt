package com.alexdevyatov.testtask.di

import com.alexdevyatov.testtask.viewmodel.CompaniesViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetModule::class])
interface AppComponent {
    fun inject(companiesViewModel: CompaniesViewModel)
}