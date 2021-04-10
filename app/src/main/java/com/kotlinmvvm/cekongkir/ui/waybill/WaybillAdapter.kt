package com.kotlinmvvm.cekongkir.ui.waybill

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlinmvvm.cekongkir.database.persistence.WaybillEntity
import com.kotlinmvvm.cekongkir.databinding.AdapterWaybillBinding

class WaybillAdapter(
        val waybills: ArrayList<WaybillEntity>,
        val listerner: OnAdapterListener)
    : RecyclerView.Adapter<WaybillAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
            AdapterWaybillBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val waybill = waybills[position]
        holder.binding.textWaybill.text = waybill.waybill
        holder.binding.textCourier.text = waybill.courier
        holder.binding.textStatus.text = waybill.status
        holder.binding.container.setOnClickListener {
            listerner.onClick(waybill)
        }

    }

    override fun getItemCount() = waybills.size


    class ViewHolder(val binding: AdapterWaybillBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnAdapterListener {
        fun onClick(result: WaybillEntity)
    }

    fun setData(data: List<WaybillEntity>) {
        waybills.clear()
        waybills.addAll(data)
        notifyDataSetChanged()
    }

}
