package com.kotlinmvvm.cekongkir.ui.cost

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kotlinmvvm.cekongkir.BuildConfig
import com.kotlinmvvm.cekongkir.databinding.FragmentCostBinding
import com.kotlinmvvm.cekongkir.network.Resource
import com.kotlinmvvm.cekongkir.network.response.CostResponse
import com.kotlinmvvm.cekongkir.ui.city.CityActivity
import timber.log.Timber

class CostFragment : Fragment() {

    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(CostViewModel::class.java) }
    private lateinit var binding: FragmentCostBinding

    private var originSubdistricId: String? = ""
    private var destinationSubdistricId: String? = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentCostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.getPreferences()
        loadingCost(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListerner()
        setupObserver()
    }

    private fun setupListerner() {
        binding.editOrigin.setOnClickListener {
            startActivity(
                    Intent(requireActivity(), CityActivity::class.java)
                            .putExtra("intent_type", "origin"))
        }
        binding.editDestination.setOnClickListener {
            startActivity(
                    Intent(requireActivity(), CityActivity::class.java)
                            .putExtra("intent_type", "destination"))
        }
        binding.buttonCost.setOnClickListener {
            if (originSubdistricId.isNullOrEmpty() || destinationSubdistricId.isNullOrEmpty()) {
                Toast.makeText(requireActivity(), "Lengkapi data pencarian", Toast.LENGTH_LONG).show()
            } else {
                viewModel.fetchCost(
                        origin = originSubdistricId!!,
                        originType = "subdistrict",
                        destination = destinationSubdistricId!!,
                        destinationType = "subdistrict",
                        weight = "1000",
                        courier = "sicepat:jnt:pos"
                )
            }
        }
    }

    private fun setupObserver() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        viewModel.preferences.observe(viewLifecycleOwner, { preferences ->
            preferences.forEach {
                Timber.d("preferencesList: $it")
                when (it.type) {
                    "origin" -> {
                        originSubdistricId = it.id
                        binding.editOrigin.setText(it.name)
                    }
                    "destination" -> {
                        destinationSubdistricId = it.id
                        binding.editDestination.setText(it.name)
                    }
                }
            }
        })
        viewModel.costResponse.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    Timber.e("costResponse : isLoading")
                    loadingCost(true)
                }
                is Resource.Success -> {
                    loadingCost(false)
                    Timber.e("costResponse : ${it.data!!.rajaongkir?.results}")
                    binding.listCost.adapter = CostAdapter(it.data.rajaongkir?.results as List<CostResponse.Rajaongkir.ResultsItem>)
//                    cityAdapter.setData(it.data.rajaongkir!!.results as List<CityResponse.Rajaongkir.ResultsItem>)
                }
                is Resource.Error -> {
                    loadingCost(false)
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun loadingCost(loading: Boolean) {
        if (loading) binding.progressCost.visibility = View.VISIBLE
        else binding.progressCost.visibility = View.GONE
    }


}