package com.kotlinmvvm.cekongkir.ui.cost

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kotlinmvvm.cekongkir.BuildConfig
import com.kotlinmvvm.cekongkir.databinding.FragmentCostBinding
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListerner()
        setupObserver()
    }

    private fun setupListerner() {
        binding.editOrigin.setOnClickListener {
            startActivity(
                    Intent(context, CityActivity::class.java)
                            .putExtra("intent_type", "origin"))
        }
        binding.editDestination.setOnClickListener {
            startActivity(
                    Intent(context, CityActivity::class.java)
                            .putExtra("intent_type", "destination"))
        }
    }

    private fun setupObserver() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        viewModel.preferences.observe(viewLifecycleOwner, Observer { preferences ->
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
    }


}