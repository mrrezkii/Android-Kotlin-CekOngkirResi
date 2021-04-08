package com.kotlinmvvm.cekongkir.ui.city

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlinmvvm.cekongkir.network.RajaOngkirRepository
import com.kotlinmvvm.cekongkir.network.Resource
import com.kotlinmvvm.cekongkir.network.response.CityResponse
import com.kotlinmvvm.cekongkir.network.response.SubdistrictResponse
import kotlinx.coroutines.launch

class CityViewModel(
        val repository: RajaOngkirRepository
) : ViewModel() {

    val titleBar: MutableLiveData<String> = MutableLiveData("")
    val cityResponse: MutableLiveData<Resource<CityResponse>> = MutableLiveData()
    val subdistrictResponse: MutableLiveData<Resource<SubdistrictResponse>> = MutableLiveData()

    init {
        fetchCity()
    }

    fun fetchCity() = viewModelScope.launch {
        cityResponse.value = Resource.Loading()
        try {
            val response = repository.fetchCity()
            cityResponse.value = Resource.Success(response.body()!!)
        } catch (e: Exception) {
            cityResponse.value = Resource.Error(e.message.toString())
        }
    }

    fun fetchSubdistrict(id: String?) = viewModelScope.launch {
        subdistrictResponse.value = Resource.Loading()
        try {
            val response = repository.fetchSubdistrict(id)
            subdistrictResponse.value = Resource.Success(response.body()!!)
        } catch (e: Exception) {
            subdistrictResponse.value = Resource.Error(e.message.toString())
        }
    }
}