package com.kotlinmvvm.cekongkir.ui.tracking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlinmvvm.cekongkir.database.persistence.WaybillEntity
import com.kotlinmvvm.cekongkir.network.RajaOngkirRepository
import com.kotlinmvvm.cekongkir.network.Resource
import com.kotlinmvvm.cekongkir.network.response.WaybillResponse
import kotlinx.coroutines.launch

class TrackingViewModel(
        val repository: RajaOngkirRepository
) : ViewModel() {

    val waybillResponse: MutableLiveData<Resource<WaybillResponse>> = MutableLiveData()
    val waybill: LiveData<List<WaybillEntity>> = repository.getWaybill()

    fun fetchWaybill(waybill: String, courier: String) = viewModelScope.launch {
        waybillResponse.value = Resource.Loading()
        try {
            val response = repository.fetchWaybill(waybill, courier)
            waybillResponse.value = Resource.Success(response.body()!!)
            saveWaybill(response.body()!!.rajaongkir!!)
        } catch (e: Exception) {
            waybillResponse.value = Resource.Error(e.message.toString())
        }
    }

    fun saveWaybill(waybill: WaybillResponse.Rajaongkir) = viewModelScope.launch {
        repository.saveWaybill(
                WaybillEntity(
                        waybill = waybill.query!!.waybill!!,
                        courier = waybill.query.courier!!,
                        status = waybill.result!!.deliveryStatus!!.status
                )
        )
    }

    fun deleteWaybill(waybill: WaybillEntity) = viewModelScope.launch {
        repository.deleteWaybill(waybill)
    }
}