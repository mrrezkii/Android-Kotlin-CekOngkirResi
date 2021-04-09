package com.kotlinmvvm.cekongkir.ui.cost

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlinmvvm.cekongkir.database.preferences.PreferencesModel
import com.kotlinmvvm.cekongkir.network.RajaOngkirRepository
import com.kotlinmvvm.cekongkir.network.Resource
import com.kotlinmvvm.cekongkir.network.response.CostResponse
import kotlinx.coroutines.launch
import timber.log.Timber

class CostViewModel(
        val repository: RajaOngkirRepository
) : ViewModel() {

    val preferences: MutableLiveData<List<PreferencesModel>> = MutableLiveData()
    val costResponse: MutableLiveData<Resource<CostResponse>> = MutableLiveData()

    fun getPreferences() {
        preferences.value = repository.getPreferences()
    }

    fun fetchCost(
            origin: String,
            originType: String,
            destination: String,
            destinationType: String,
            weight: String,
            courier: String) = viewModelScope.launch {
        costResponse.value = Resource.Loading()
        try {
            val response = repository.fetchCost(origin, originType, destination, destinationType, weight, courier)
            costResponse.value = Resource.Success(response.body()!!)
        } catch (e: Exception) {
            costResponse.value = Resource.Error(e.message.toString())
            Timber.e(e)
        }
    }


}