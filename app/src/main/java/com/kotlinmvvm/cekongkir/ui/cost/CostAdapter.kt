package com.kotlinmvvm.cekongkir.ui.cost

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlinmvvm.cekongkir.databinding.AdapterCostBinding
import com.kotlinmvvm.cekongkir.network.response.CostResponse


class CostAdapter(
        val costs: List<CostResponse.Rajaongkir.ResultsItem>)
    : RecyclerView.Adapter<CostAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
            AdapterCostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cost = costs[position]
        holder.binding.textCode.text = cost.code
        holder.binding.textName.text = cost.name
        holder.binding.listService.adapter = ServiceAdapter(cost.costs as List<CostResponse.Rajaongkir.ResultsItem.CostsItem>)

    }

    override fun getItemCount() = costs.size


    class ViewHolder(val binding: AdapterCostBinding) : RecyclerView.ViewHolder(binding.root)
}
