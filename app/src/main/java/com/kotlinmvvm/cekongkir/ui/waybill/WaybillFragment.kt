package com.kotlinmvvm.cekongkir.ui.waybill

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.BuildConfig
import com.kotlinmvvm.cekongkir.database.persistence.WaybillEntity
import com.kotlinmvvm.cekongkir.databinding.FragmentWaybillBinding
import com.kotlinmvvm.cekongkir.ui.tracking.TrackingActivity
import com.kotlinmvvm.cekongkir.ui.tracking.TrackingViewModel
import timber.log.Timber

class WaybillFragment : Fragment() {

    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(TrackingViewModel::class.java) }

    private lateinit var binding: FragmentWaybillBinding
    private lateinit var waybillAdapter: WaybillAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentWaybillBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupListener()
        setupObserver()
    }

    private fun setupRecyclerView() {
        waybillAdapter = WaybillAdapter(arrayListOf(), object : WaybillAdapter.OnAdapterListener {
            override fun onDetail(result: WaybillEntity) {
                startActivity(Intent(requireActivity(), TrackingActivity::class.java)
                        .putExtra("is_tracking", true)
                        .putExtra("is_waybill", result.waybill)
                        .putExtra("is_courier", result.courier)
                )
            }

            override fun onDelete(result: WaybillEntity) {
                val builder = AlertDialog.Builder(requireActivity())
                builder.apply {
                    setTitle("HAPUS RESI")
                    setMessage("Hapus No. Resi ${result.waybill}?")
                    setPositiveButton("Hapus") { _, _ ->
                        viewModel.deleteWaybill(result)
                        Toast.makeText(requireActivity(), "Resi dihapus", Toast.LENGTH_LONG).show()
                    }
                    setNegativeButton("Batal") { _, _ ->

                    }
                    show()
                }
            }

        })
        binding.listWaybill.adapter = waybillAdapter
    }

    private fun setupListener() {
        binding.editWaybill.setOnClickListener {
            startActivity(Intent(requireActivity(), TrackingActivity::class.java))
        }
    }

    private fun setupObserver() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        viewModel.waybill.observe(viewLifecycleOwner, Observer {
            Timber.e("waybill : $it")
            waybillAdapter.setData(it)
        })
    }
}