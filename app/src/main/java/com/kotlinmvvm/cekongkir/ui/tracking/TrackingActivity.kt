package com.kotlinmvvm.cekongkir.ui.tracking

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kotlinmvvm.cekongkir.R

class TrackingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tracking)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}