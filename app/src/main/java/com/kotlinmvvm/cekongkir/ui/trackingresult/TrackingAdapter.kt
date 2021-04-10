package com.kotlinmvvm.cekongkir.ui.trackingresult

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlinmvvm.cekongkir.databinding.AdapterTrackingBinding
import com.kotlinmvvm.cekongkir.network.response.WaybillResponse

class TrackingAdapter(
        val manifests: List<WaybillResponse.Rajaongkir.Result.ManifestItem>)
    : RecyclerView.Adapter<TrackingAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
            AdapterTrackingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val manifest = manifests[position]
        holder.binding.textDate.text = "${manifest.manifestDate} ${manifest.manifestTime}"
        holder.binding.textDescription.text = manifest.manifestDescription
    }

    override fun getItemCount() = manifests.size


    class ViewHolder(val binding: AdapterTrackingBinding) : RecyclerView.ViewHolder(binding.root)

}
