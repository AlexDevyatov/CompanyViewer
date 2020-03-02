package com.alexdevyatov.testtask.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alexdevyatov.testtask.di.AppComponent
import com.alexdevyatov.testtask.model.CompanyInfo
import com.alexdevyatov.testtask.repository.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CompanyInfoViewModel(appComponent: AppComponent) : ViewModel() {

    init {
        appComponent.inject(this)
    }

    @Inject
    lateinit var repository: Repository

    var companyId = 0
        set(value) {
            loadData(value)
        }

    private val companyInfo: MutableLiveData<CompanyInfo> = MutableLiveData()

    fun getCompanies(): LiveData<CompanyInfo> {
        return companyInfo
    }

    private fun loadData(id: Int) {
        repository
            .getCompanyInfo(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { companyInfoList ->
                    this.companyInfo.postValue(companyInfoList[0])
                },
                { error -> Log.d("ERROR", error.message) }
            )
    }
}