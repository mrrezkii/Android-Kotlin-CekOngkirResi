package com.kotlinmvvm.cekongkir.ui.city

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kotlinmvvm.cekongkir.network.RajaOngkirEndPoint

class CityViewModel(
        val apiService: RajaOngkirEndPoint
) : ViewModel() {

    val titleBar: MutableLiveData<String> = MutableLiveData("")
}