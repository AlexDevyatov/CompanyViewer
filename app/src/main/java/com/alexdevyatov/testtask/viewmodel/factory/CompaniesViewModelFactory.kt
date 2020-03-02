package com.alexdevyatov.testtask.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alexdevyatov.testtask.di.AppComponent
import com.alexdevyatov.testtask.viewmodel.CompaniesViewModel

class CompaniesViewModelFactory(private val appComponent: AppComponent)  : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == CompaniesViewModel::class.java) {
            return CompaniesViewModel(appComponent) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}