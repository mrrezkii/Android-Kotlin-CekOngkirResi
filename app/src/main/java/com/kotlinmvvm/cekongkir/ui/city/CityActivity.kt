package com.kotlinmvvm.cekongkir.ui.city

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kotlinmvvm.cekongkir.R

class CityActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProvider(this).get(CityViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)

        viewModel.titleBar.observe(this, Observer { title ->
            supportActionBar!!.title = title
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}