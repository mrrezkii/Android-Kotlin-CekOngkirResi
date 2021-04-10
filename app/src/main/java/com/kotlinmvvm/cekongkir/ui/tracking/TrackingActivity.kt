package com.kotlinmvvm.cekongkir.ui.tracking

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.kotlinmvvm.cekongkir.databinding.ActivityTrackingBinding
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class TrackingActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val trackingViewModelFactory: TrackingViewModelFactory by instance()
    private lateinit var viewModel: TrackingViewModel

    private val binding by lazy { ActivityTrackingBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupView()
        setupViewModel()
    }

    private fun setupView() {
        supportActionBar!!.title = "Lacak No. Resi"
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, trackingViewModelFactory).get(TrackingViewModel::class.java)
    }

    override fun onBackPressed() {
        if (intent.getBooleanExtra("is_tracking", false)) finish()
        else super.onBackPressed()
    }
}