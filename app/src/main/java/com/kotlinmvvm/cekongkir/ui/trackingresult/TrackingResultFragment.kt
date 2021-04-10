package com.kotlinmvvm.cekongkir.ui.trackingresult

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.BuildConfig
import com.kotlinmvvm.cekongkir.databinding.FragmentTrackingResultBinding
import com.kotlinmvvm.cekongkir.network.Resource
import com.kotlinmvvm.cekongkir.network.response.WaybillResponse
import com.kotlinmvvm.cekongkir.ui.tracking.TrackingViewModel
import com.kotlinmvvm.cekongkir.utils.swipeHide
import com.kotlinmvvm.cekongkir.utils.swipeShow
import com.kotlinmvvm.cekongkir.utils.viewHide
import com.kotlinmvvm.cekongkir.utils.viewShow
import timber.log.Timber

class TrackingResultFragment : Fragment() {

    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(TrackingViewModel::class.java) }

    private lateinit var binding: FragmentTrackingResultBinding
    private val waybill by lazy { requireArguments().getString("waybill") }
    private val courier by lazy { requireArguments().getString("courier") }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentTrackingResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        setupListener()
    }

    override fun onStart() {
        super.onStart()
        viewModel.fetchWaybill(waybill!!, courier!!)
    }

    private fun setupListener() {
        binding.refreshWaybill.setOnRefreshListener {
            viewModel.fetchWaybill(waybill!!, courier!!)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupObserver() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        viewModel.waybillResponse.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    binding.refreshWaybill.swipeShow()
                    binding.container.viewHide()
                }
                is Resource.Success -> {
                    binding.refreshWaybill.swipeHide()
                    binding.container.viewShow()
                    Timber.e("waybill : ${it.data!!.rajaongkir!!.result}")
                    val data = it.data.rajaongkir!!.result
                    binding.textStatus.text = data!!.deliveryStatus!!.status
                    binding.textReceiver.text = data.deliveryStatus!!.podReceiver
                    binding.textDate.text = "${data.deliveryStatus.podDate} ${data.deliveryStatus.podTime}"
                    binding.listManifest.adapter = TrackingAdapter(data.manifest as List<WaybillResponse.Rajaongkir.Result.ManifestItem>)
                }
                is Resource.Error -> {
                    binding.refreshWaybill.swipeHide()
                    binding.container.viewShow()
                }
            }
        })
    }
}