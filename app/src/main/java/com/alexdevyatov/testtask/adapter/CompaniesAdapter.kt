package com.alexdevyatov.testtask.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.alexdevyatov.testtask.R
import com.alexdevyatov.testtask.di.NetModule
import com.alexdevyatov.testtask.model.Company
import com.alexdevyatov.testtask.view.fragment.CompaniesListFragmentDirections
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_company.view.*

class CompaniesAdapter(val companies: List<Company>,
                       val context: Context) : RecyclerView.Adapter<CompaniesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
        = ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_company, parent, false))

    override fun getItemCount(): Int = companies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val company = companies[position]
        holder.tvCompanyName.text = company.name
        Glide.with(context).load(NetModule.BASE_URL + company.image).centerCrop().into(holder.ivCompanyImage)
        holder.itemView.setOnClickListener {
            it.findNavController().navigate(CompaniesListFragmentDirections.actionCompaniesListFragmentToCompanyCardFragment())
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivCompanyImage = view.ivConpanyImage
        val tvCompanyName = view.tvCompanyName
    }
}