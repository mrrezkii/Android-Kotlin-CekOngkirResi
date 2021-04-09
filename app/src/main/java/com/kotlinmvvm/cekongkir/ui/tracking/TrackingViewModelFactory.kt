package com.kotlinmvvm.cekongkir.ui.tracking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kotlinmvvm.cekongkir.network.RajaOngkirRepository

class TrackingViewModelFactory(
        private val repository: RajaOngkirRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TrackingViewModel(repository) as T
    }
}