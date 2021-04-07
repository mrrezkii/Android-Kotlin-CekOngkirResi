package com.kotlinmvvm.cekongkir.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.kotlinmvvm.cekongkir.R

class HomeActivity : AppCompatActivity() {

    val tabLayout by lazy { findViewById<TabLayout>(R.id.tab_layout) }
    val viewPager by lazy { findViewById<ViewPager2>(R.id.view_pager) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setupTab()
    }

    private fun setupTab() {
        val tabTitles = arrayListOf("Cek Ongkir", "Cek Resi")
        val tablAdapter = HomeTabAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = tablAdapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }
}