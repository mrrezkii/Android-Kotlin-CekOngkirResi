package com.kotlinmvvm.cekongkir.ui.city

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kotlinmvvm.cekongkir.R
import com.kotlinmvvm.cekongkir.databinding.FragmentCityBinding

class CityFragment : Fragment() {

    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(CityViewModel::class.java) }
    private lateinit var binding: FragmentCityBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentCityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.titleBar.postValue("Pilih Kota")

        binding.container.setOnClickListener {
            findNavController().navigate(R.id.action_cityFragment_to_subdistrictFragment)
        }
    }
}