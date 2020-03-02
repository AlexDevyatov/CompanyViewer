package com.alexdevyatov.testtask.view.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexdevyatov.testtask.App
import com.alexdevyatov.testtask.R
import com.alexdevyatov.testtask.adapter.CompaniesAdapter
import com.alexdevyatov.testtask.model.Company
import com.alexdevyatov.testtask.viewmodel.CompaniesViewModel
import com.alexdevyatov.testtask.viewmodel.factory.CompaniesViewModelFactory
import kotlinx.android.synthetic.main.fragment_companies_list.*


class CompaniesListFragment : Fragment() {

    private var companiesViewModel: CompaniesViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appComponent = (activity!!.application as App).appComponent
        companiesViewModel = ViewModelProviders.of(
            this,
            CompaniesViewModelFactory(appComponent!!)
        ).get(CompaniesViewModel::class.java)

        companiesViewModel!!.getCompanies().observe(this,
            Observer<List<Company>> { companies -> showCompanies(companies) })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_companies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
        companiesViewModel!!.refresh()
    }

    private fun showCompanies(companies: List<Company>) {
        recyclerView.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = CompaniesAdapter(companies, activity!!)
    }
}
