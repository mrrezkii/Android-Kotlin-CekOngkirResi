package com.kotlinmvvm.cekongkir.ui.cost

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlinmvvm.cekongkir.databinding.AdapterServiceBinding
import com.kotlinmvvm.cekongkir.network.response.CostResponse


class ServiceAdapter(
        val costs: List<CostResponse.Rajaongkir.ResultsItem.CostsItem>)
    : RecyclerView.Adapter<ServiceAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
            AdapterServiceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cost = costs[position]
        holder.binding.textService.text = cost.service
        holder.binding.textDescription.text = cost.description
        holder.binding.textValue.text = cost.cost?.get(0)?.value.toString()
        holder.binding.textEtd.text = cost.cost?.get(0)?.etd
//        holder.binding.listService.adapter =

    }

    override fun getItemCount() = costs.size


    class ViewHolder(val binding: AdapterServiceBinding) : RecyclerView.ViewHolder(binding.root)
}
