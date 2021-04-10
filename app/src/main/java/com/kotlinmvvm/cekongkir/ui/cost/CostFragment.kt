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
import com.kotlinmvvm.cekongkir.utils.viewHide
import com.kotlinmvvm.cekongkir.utils.viewShow
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
                    binding.progressCost.viewShow()
                }
                is Resource.Success -> {
                    binding.progressCost.viewHide()
                    Timber.e("costResponse : ${it.data!!.rajaongkir?.results}")
                    binding.listCost.adapter = CostAdapter(it.data.rajaongkir?.results as List<CostResponse.Rajaongkir.ResultsItem>)
                }
                is Resource.Error -> {
                    binding.progressCost.viewHide()
                }
            }
        })
    }

    private fun loadingCost(loading: Boolean) {
        if (loading) binding.progressCost.viewShow()
        else binding.progressCost.viewHide()
    }


}