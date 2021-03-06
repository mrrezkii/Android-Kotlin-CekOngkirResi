package com.kotlinmvvm.cekongkir.ui.city

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.BuildConfig
import com.kotlinmvvm.cekongkir.R
import com.kotlinmvvm.cekongkir.databinding.FragmentCityBinding
import com.kotlinmvvm.cekongkir.network.Resource
import com.kotlinmvvm.cekongkir.network.response.CityResponse
import com.kotlinmvvm.cekongkir.utils.showToast
import timber.log.Timber

class CityFragment : Fragment() {

    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(CityViewModel::class.java) }
    private lateinit var binding: FragmentCityBinding
    private lateinit var cityAdapter: CityAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentCityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupListener()
        setupRecyclerView()
        setupObserver()

    }

    private fun setupView() {
        viewModel.titleBar.postValue("Pilih Kota")
    }

    private fun setupListener() {
        binding.editSearch.doAfterTextChanged {
            cityAdapter.filter.filter(it.toString())
        }
        binding.refreshCity.setOnRefreshListener {
            viewModel.fetchCity()
        }
    }

    private fun setupRecyclerView() {
        cityAdapter = CityAdapter(arrayListOf(), object : CityAdapter.OnAdapterListener {
            override fun onClick(result: CityResponse.Rajaongkir.ResultsItem) {
                viewModel.fetchSubdistrict(result.cityId)
                findNavController().navigate(
                        R.id.action_cityFragment_to_subdistrictFragment,
                        bundleOf("city_id" to result.cityId, "city_name" to result.cityName)
                )
            }
        })
        binding.listCity.adapter = cityAdapter
    }

    private fun setupObserver() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        viewModel.cityResponse.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    Timber.e("rajaongkir : isLoading")
                    binding.refreshCity.isRefreshing = true
                }
                is Resource.Success -> {
                    binding.refreshCity.isRefreshing = false
                    Timber.e("rajaongkir : ${it.data!!.rajaongkir}")
                    cityAdapter.setData(it.data.rajaongkir!!.results as List<CityResponse.Rajaongkir.ResultsItem>)
                }
                is Resource.Error -> {
                    binding.refreshCity.isRefreshing = false
                    showToast("Lengkapi data pencarian")
                }
            }
        })
    }
}