package com.alexdevyatov.testtask.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alexdevyatov.testtask.di.AppComponent
import com.alexdevyatov.testtask.model.Company
import com.alexdevyatov.testtask.repository.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CompaniesViewModel(appComponent: AppComponent) : ViewModel() {

    init {
        appComponent.inject(this)
    }

    @Inject
    lateinit var repository: Repository

    private val companies: MutableLiveData<List<Company>> = MutableLiveData()

    fun refresh() {
        loadData()
    }

    fun getCompanies() : LiveData<List<Company>> {
        return companies
    }

    private fun loadData() {
        repository
            .getCompaniesList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { companies ->
                    this.companies.postValue(companies)
                },
                { error -> Log.d("ERROR", error.message) }
            )
    }
}