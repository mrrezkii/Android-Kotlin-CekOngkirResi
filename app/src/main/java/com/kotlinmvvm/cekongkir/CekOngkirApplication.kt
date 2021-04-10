package com.kotlinmvvm.cekongkir

import android.app.Application
import com.kotlinmvvm.cekongkir.database.persistence.CekOngkirDatabase
import com.kotlinmvvm.cekongkir.database.preferences.CekOngkirPreference
import com.kotlinmvvm.cekongkir.network.ApiService
import com.kotlinmvvm.cekongkir.network.RajaOngkirEndPoint
import com.kotlinmvvm.cekongkir.network.RajaOngkirRepository
import com.kotlinmvvm.cekongkir.ui.city.CityViewModelFactory
import com.kotlinmvvm.cekongkir.ui.cost.CostViewModelFactory
import com.kotlinmvvm.cekongkir.ui.tracking.TrackingViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import timber.log.Timber

class CekOngkirApplication : Application(), KodeinAware {

    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@CekOngkirApplication))

        bind() from singleton { CekOngkirPreference(instance()) }
        bind() from singleton { CekOngkirDatabase(instance()) }
        bind<RajaOngkirEndPoint>() with singleton { ApiService.getClient() }

        bind() from singleton { RajaOngkirRepository(instance(), instance(), instance()) }
        bind() from provider { CityViewModelFactory(instance()) }
        bind() from provider { CostViewModelFactory(instance()) }
        bind() from provider { TrackingViewModelFactory(instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}