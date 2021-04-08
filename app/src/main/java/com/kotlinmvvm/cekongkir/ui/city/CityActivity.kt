package com.kotlinmvvm.cekongkir.ui.city

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.BuildConfig
import com.kotlinmvvm.cekongkir.R
import com.kotlinmvvm.cekongkir.network.Resource
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import timber.log.Timber

class CityActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()

    private val viewModelFactory: CityViewModelFactory by instance()
    private lateinit var viewModel: CityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)
        setupViewModel()
        setupObserver()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(CityViewModel::class.java)
    }

    private fun setupObserver() {
        viewModel.titleBar.observe(this, Observer { title ->
            supportActionBar!!.title = title
        })
        viewModel.cityResponse.observe(this, Observer {
            if (BuildConfig.DEBUG) {
                Timber.plant(Timber.DebugTree())
            }
            when (it) {
                is Resource.Loading -> {
                    Timber.e("rajaongkir : isLoading")
                }
                is Resource.Success -> {
                    Timber.e("rajaongkir : ${it.data!!.rajaongkir}")
                }
                is Resource.Error -> {
                    Toast.makeText(applicationContext, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}