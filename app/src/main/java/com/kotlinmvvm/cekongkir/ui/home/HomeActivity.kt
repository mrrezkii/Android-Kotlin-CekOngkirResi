package com.kotlinmvvm.cekongkir.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.kotlinmvvm.cekongkir.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private val binding by lazy { ActivityHomeBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupTab()
    }

    private fun setupTab() {
        val tabTitles = arrayListOf("Cek Ongkir", "Cek Resi")
        val tablAdapter = HomeTabAdapter(supportFragmentManager, lifecycle)
        binding.viewPager.adapter = tablAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }
}