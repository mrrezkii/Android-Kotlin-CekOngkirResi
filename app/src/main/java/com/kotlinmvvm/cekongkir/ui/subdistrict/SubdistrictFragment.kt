package com.kotlinmvvm.cekongkir.ui.subdistrict

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kotlinmvvm.cekongkir.databinding.FragmentSubdistrictBinding
import com.kotlinmvvm.cekongkir.ui.city.CityViewModel


class SubdistrictFragment : Fragment() {

    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(CityViewModel::class.java) }
    private lateinit var binding: FragmentSubdistrictBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentSubdistrictBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.titleBar.postValue("Pilih Kecamatan")
    }
}