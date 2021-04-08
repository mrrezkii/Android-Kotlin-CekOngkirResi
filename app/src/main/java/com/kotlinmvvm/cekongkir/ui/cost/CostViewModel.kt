package com.kotlinmvvm.cekongkir.ui.cost

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kotlinmvvm.cekongkir.database.preferences.PreferencesModel
import com.kotlinmvvm.cekongkir.network.RajaOngkirRepository

class CostViewModel(
        val repository: RajaOngkirRepository
) : ViewModel() {

    val preferences: MutableLiveData<List<PreferencesModel>> = MutableLiveData()

    fun getPreferences() {
        preferences.value = repository.getPreferences()
    }


}