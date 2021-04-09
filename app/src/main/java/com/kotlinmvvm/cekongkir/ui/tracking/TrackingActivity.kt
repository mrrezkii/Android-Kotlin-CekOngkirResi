package com.kotlinmvvm.cekongkir.ui.tracking

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kotlinmvvm.cekongkir.R
import com.kotlinmvvm.cekongkir.databinding.ActivityTrackingBinding

class TrackingActivity : AppCompatActivity() {

    private val binding by lazy { ActivityTrackingBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tracking)
        setupView()
    }

    private fun setupView() {
        supportActionBar!!.title = "Lacak No. Resi"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}