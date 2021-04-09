package com.kotlinmvvm.cekongkir.ui.waybill

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kotlinmvvm.cekongkir.databinding.FragmentWaybillBinding
import com.kotlinmvvm.cekongkir.ui.tracking.TrackingActivity

class WaybillFragment : Fragment() {

    private lateinit var binding: FragmentWaybillBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentWaybillBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
    }

    private fun setupListener() {
        binding.editWaybill.setOnClickListener {
            startActivity(Intent(requireActivity(), TrackingActivity::class.java))
        }
    }
}