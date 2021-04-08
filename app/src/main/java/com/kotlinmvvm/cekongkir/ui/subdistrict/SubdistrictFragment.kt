package com.kotlinmvvm.cekongkir.ui.subdistrict

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.BuildConfig
import com.kotlinmvvm.cekongkir.databinding.FragmentSubdistrictBinding
import com.kotlinmvvm.cekongkir.network.Resource
import com.kotlinmvvm.cekongkir.network.response.SubdistrictResponse
import com.kotlinmvvm.cekongkir.ui.city.CityViewModel
import timber.log.Timber


class SubdistrictFragment : Fragment() {

    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(CityViewModel::class.java) }
    private lateinit var binding: FragmentSubdistrictBinding
    private lateinit var subdistrictAdapter: SubdistrictAdapter
    private val type by lazy { requireActivity().intent.getStringExtra("intent_type")!! }
    private val cityId by lazy { requireArguments().getString("city_id") }
    private val cityName by lazy { requireArguments().getString("city_name") }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentSubdistrictBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupRecyclerView()
        setupListener()
        setupObserver()
    }

    private fun setupView() {
        viewModel.titleBar.postValue("Pilih Kecamatan")
    }

    private fun setupRecyclerView() {
        subdistrictAdapter = SubdistrictAdapter(
            arrayListOf(),
            object : SubdistrictAdapter.OnAdapterListener {
                override fun onClick(result: SubdistrictResponse.Rajaongkir.ResultsItem) {
                    viewModel.savePreferences(
                        type = type,
                        id = result.subdistrictId,
                        name = "$cityName, ${result.subdistrictName}"
                    )
                }

            })
        binding.listSubdistrict.adapter = subdistrictAdapter
    }


    private fun setupListener() {
        binding.refreshSubdistrict.setOnRefreshListener {
            viewModel.fetchSubdistrict(cityId!!)
        }
    }

    private fun setupObserver() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        viewModel.subdistrictResponse.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    Timber.e("subdistrictResponse : isLoading")
                    binding.refreshSubdistrict.isRefreshing = true
                }
                is Resource.Success -> {
                    binding.refreshSubdistrict.isRefreshing = false
                    Timber.e("subdistrictResponse : ${it.data!!.rajaongkir!!.results}")
                    subdistrictAdapter.setData(it.data.rajaongkir!!.results as List<SubdistrictResponse.Rajaongkir.ResultsItem>)
                }
                is Resource.Error -> {
                    binding.refreshSubdistrict.isRefreshing = false
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })

    }
}