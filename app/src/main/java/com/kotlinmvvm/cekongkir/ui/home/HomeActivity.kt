package com.kotlinmvvm.cekongkir.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.kotlinmvvm.cekongkir.databinding.ActivityHomeBinding
import com.kotlinmvvm.cekongkir.ui.cost.CostViewModel
import com.kotlinmvvm.cekongkir.ui.cost.CostViewModelFactory
import com.kotlinmvvm.cekongkir.ui.tracking.TrackingViewModel
import com.kotlinmvvm.cekongkir.ui.tracking.TrackingViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class HomeActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val costViewModelFactory: CostViewModelFactory by instance()
    private val trackingActivity: TrackingViewModelFactory by instance()

    private val binding: ActivityHomeBinding by lazy { ActivityHomeBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupTab()
        setupViewModel()
    }

    private fun setupTab() {
        val tabTitles = arrayListOf("Cek Ongkir", "Cek Resi")
        val tablAdapter = HomeTabAdapter(supportFragmentManager, lifecycle)
        binding.viewPager.adapter = tablAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }

    private fun setupViewModel() {
        ViewModelProvider(this, costViewModelFactory).get(CostViewModel::class.java)
        ViewModelProvider(this, trackingActivity).get(TrackingViewModel::class.java)
    }
}