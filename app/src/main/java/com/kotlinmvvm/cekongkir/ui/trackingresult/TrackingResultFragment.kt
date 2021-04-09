package com.kotlinmvvm.cekongkir.ui.trackingresult

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.BuildConfig
import com.kotlinmvvm.cekongkir.databinding.FragmentTrackingResultBinding
import com.kotlinmvvm.cekongkir.network.Resource
import com.kotlinmvvm.cekongkir.ui.tracking.TrackingViewModel
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
    }

    override fun onStart() {
        super.onStart()
        viewModel.fetchWaybill(waybill!!, courier!!)
    }

    private fun setupObserver() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        viewModel.waybillResponse.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    Timber.e("waybill : isLoading")
                }
                is Resource.Success -> {
                    Timber.e("waybill : ${it.data!!.rajaongkir!!.result}")
                }
                is Resource.Error -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}