package com.kotlinmvvm.cekongkir.ui.city

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlinmvvm.cekongkir.databinding.AdapterCityBinding
import com.kotlinmvvm.cekongkir.network.response.CityResponse

class CityAdapter(
        val cities: ArrayList<CityResponse.Rajaongkir.ResultsItem>,
        val listerner: OnAdapterListener)
    : RecyclerView.Adapter<CityAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
            AdapterCityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val city = cities[position]
        holder.binding.textName.text = city.cityName
        holder.binding.container.setOnClickListener {
            listerner.onClick(city)
        }
    }

    override fun getItemCount() = cities.size


    class ViewHolder(val binding: AdapterCityBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnAdapterListener {
        fun onClick(result: CityResponse.Rajaongkir.ResultsItem)
    }

    fun setData(data: List<CityResponse.Rajaongkir.ResultsItem>) {
        cities.clear()
        cities.addAll(data)
        notifyDataSetChanged()
    }

}
