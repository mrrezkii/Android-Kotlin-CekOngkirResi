package com.kotlinmvvm.cekongkir.ui.city

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kotlinmvvm.cekongkir.R

class CityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}