package com.kotlinmvvm.cekongkir.ui.subdistrict

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlinmvvm.cekongkir.databinding.AdapterSubdistrictBinding
import com.kotlinmvvm.cekongkir.network.response.SubdistrictResponse

class SubdistrictAdapter(
        val subdistricts: ArrayList<SubdistrictResponse.Rajaongkir.ResultsItem>,
        val listerner: OnAdapterListener)
    : RecyclerView.Adapter<SubdistrictAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
            AdapterSubdistrictBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val subdistric = subdistricts[position]
        holder.binding.textName.text = subdistric.subdistrictName
        holder.binding.container.setOnClickListener {
            listerner.onClick(subdistric)
        }
    }

    override fun getItemCount() = subdistricts.size


    class ViewHolder(val binding: AdapterSubdistrictBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnAdapterListener {
        fun onClick(result: SubdistrictResponse.Rajaongkir.ResultsItem)
    }

    fun setData(data: List<SubdistrictResponse.Rajaongkir.ResultsItem>) {
        subdistricts.clear()
        subdistricts.addAll(data)
        notifyDataSetChanged()
    }

}
