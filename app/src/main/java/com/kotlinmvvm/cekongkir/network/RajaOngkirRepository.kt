package com.kotlinmvvm.cekongkir.network

import com.kotlinmvvm.cekongkir.database.persistence.CekOngkirDatabase
import com.kotlinmvvm.cekongkir.database.persistence.WaybillEntity
import com.kotlinmvvm.cekongkir.database.preferences.*

class RajaOngkirRepository(
        private val api: RajaOngkirEndPoint,
        private val pref: CekOngkirPreference,
        private val db: CekOngkirDatabase
) {
    suspend fun fetchCity() = api.city()

    suspend fun fetchSubdistrict(cityId: String?) = api.subdistrict(cityId)

    fun savePreferences(type: String, id: String, name: String) {
        when (type) {
            "origin" -> {
                pref.put(prefOriginId, id)
                pref.put(prefOriginName, name)
            }
            "destination" -> {
                pref.put(prefDestinationId, id)
                pref.put(prefDestinationName, name)
            }
        }
    }

    fun getPreferences(): List<PreferencesModel> {
        return listOf<PreferencesModel>(
                PreferencesModel(type = "origin", id = pref.getString(prefOriginId), name = pref.getString(prefOriginName)),
                PreferencesModel(type = "destination", id = pref.getString(prefDestinationId), name = pref.getString(prefDestinationName))
        )
    }

    suspend fun fetchCost(
            origin: String,
            originType: String,
            destination: String,
            destinationType: String,
            weight: String,
            courier: String
    ) = api.cost(origin, originType, destination, destinationType, weight, courier)

    suspend fun fetchWaybill(
            waybill: String,
            courier: String,
    ) = api.waybill(waybill, courier)

    suspend fun saveWaybill(waybillEntity: WaybillEntity) {
        db.waybillDao().insert(waybillEntity)
    }

    fun getWaybill() = db.waybillDao().select()

    suspend fun deleteWaybill(waybillEntity: WaybillEntity) {
        db.waybillDao().delete(waybillEntity)
    }
}