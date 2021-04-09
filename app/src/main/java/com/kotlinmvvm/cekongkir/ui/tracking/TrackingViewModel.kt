package com.kotlinmvvm.cekongkir.ui.tracking

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlinmvvm.cekongkir.network.RajaOngkirRepository
import com.kotlinmvvm.cekongkir.network.Resource
import com.kotlinmvvm.cekongkir.network.response.WaybillResponse
import kotlinx.coroutines.launch

class TrackingViewModel(
        val repository: RajaOngkirRepository
) : ViewModel() {

    val waybillResponse: MutableLiveData<Resource<WaybillResponse>> = MutableLiveData()

    fun fetchWaybill(waybill: String, courier: String) = viewModelScope.launch {
        waybillResponse.value = Resource.Loading()
        try {
            val response = repository.fetchWaybill(waybill, courier)
            waybillResponse.value = Resource.Success(response.body()!!)
        } catch (e: Exception) {
            waybillResponse.value = Resource.Error(e.message.toString())
        }
    }
}