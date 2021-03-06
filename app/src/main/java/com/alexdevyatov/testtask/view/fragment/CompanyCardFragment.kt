package com.alexdevyatov.testtask.view.fragment


import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.alexdevyatov.testtask.App
import com.alexdevyatov.testtask.R
import com.alexdevyatov.testtask.di.NetModule
import com.alexdevyatov.testtask.model.CompanyInfo
import com.alexdevyatov.testtask.viewmodel.CompanyInfoViewModel
import com.alexdevyatov.testtask.viewmodel.factory.CompanyInfoViewModelFactory
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_company_card.*

class CompanyCardFragment : Fragment() {

    private val args: CompanyCardFragmentArgs by navArgs()
    private var companyInfoViewModel: CompanyInfoViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appComponent = (activity!!.application as App).appComponent
        companyInfoViewModel = ViewModelProviders.of(
            this,
            CompanyInfoViewModelFactory(appComponent!!)
        ).get(CompanyInfoViewModel::class.java)
        companyInfoViewModel!!.getCompanies().observe(this,
            Observer<CompanyInfo> { companyInfo -> showCompanyInfo(companyInfo) })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_company_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvCompanyDesc.movementMethod = ScrollingMovementMethod()
        showProgressBar()
        companyInfoViewModel!!.companyId = args.companyId
    }

    private fun showCompanyInfo(companyInfo: CompanyInfo?) {
        hideProgressBar()
        if (companyInfo != null) {
            tvCompanyDesc.movementMethod = ScrollingMovementMethod()
            tvCompanyName.text = companyInfo.name
            tvCompanyDesc.text = companyInfo.description
            tvPhone.text = companyInfo.phone
            tvWebsite.text = companyInfo.url
            Glide.with(this).load(NetModule.BASE_URL + companyInfo.image)
                .centerCrop().into(ivCompanyImage)
        } else {
            showErrorMessage()
        }
    }

    private fun showErrorMessage() {
        tvCompanyName.visibility = View.GONE
        tvCompanyDesc.visibility = View.GONE
        tvWebsite.visibility = View.GONE
        tvPhone.visibility = View.GONE
        ivCompanyImage.visibility = View.GONE
        tvErrorMessage.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
    }

    private fun showProgressBar() {
        tvCompanyName.visibility = View.GONE
        tvCompanyDesc.visibility = View.GONE
        tvWebsite.visibility = View.GONE
        tvPhone.visibility = View.GONE
        ivCompanyImage.visibility = View.GONE
        tvErrorMessage.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        tvCompanyName.visibility = View.VISIBLE
        tvCompanyDesc.visibility = View.VISIBLE
        tvWebsite.visibility = View.VISIBLE
        tvPhone.visibility = View.VISIBLE
        ivCompanyImage.visibility = View.VISIBLE
        tvErrorMessage.visibility = View.GONE
        progressBar.visibility = View.GONE
    }
}
