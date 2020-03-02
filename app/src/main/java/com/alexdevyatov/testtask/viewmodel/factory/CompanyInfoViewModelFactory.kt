package com.alexdevyatov.testtask.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alexdevyatov.testtask.di.AppComponent
import com.alexdevyatov.testtask.viewmodel.CompanyInfoViewModel

class CompanyInfoViewModelFactory(private val appComponent: AppComponent): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == CompanyInfoViewModel::class.java) {
            return CompanyInfoViewModel(appComponent) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}