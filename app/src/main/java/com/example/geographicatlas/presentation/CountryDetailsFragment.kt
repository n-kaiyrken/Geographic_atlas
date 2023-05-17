package com.example.geographicatlas.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.geographicatlas.databinding.FragmentCountryDetailsBinding
import com.squareup.picasso.Picasso

class CountryDetailsFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    private var _binding: FragmentCountryDetailsBinding? = null
    private val binding: FragmentCountryDetailsBinding
        get() = _binding ?: throw RuntimeException("FragmentCountryDetailsBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountryDetailsBinding.inflate(inflater,container, false )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        binding.detailsToolBar.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
            viewModel.clearCountryLivaData()
        }
        binding.textViewDetailsNoConnection.setOnClickListener {
            viewModel.getAllCountries()
        }
        viewModel.noConnectionLivaData.observe(viewLifecycleOwner) {
            if (it) {
                binding.scrollView.visibility = View.GONE
                setChildrenVisibility(binding.scrollView, View.GONE)
                binding.textViewDetailsNoConnection.visibility = View.VISIBLE
            } else {
                binding.scrollView.visibility = View.VISIBLE
                setChildrenVisibility(binding.scrollView, View.VISIBLE)
                binding.textViewDetailsNoConnection.visibility = View.GONE
            }
        }
        viewModel.countryLivaData.observe(viewLifecycleOwner) {
            with(binding) {
                detailsToolBar.title = it.name
                textViewDetailsCapital.text = it.capital
                textViewCoordinates.text = it.latlng
                textViewRegion.text = it.subregion
                textViewTimezones.text = it.timezones
                textViewDetailsArea.text = it.area
                textViewDetailsCurrency.text = it.currencies
                textViewDetailsPopulation.text = it.population
                if (it.flags != "") {
                    Picasso.get().load(it.flags).into(binding.imageViewFlag)
                }
            }
        }
    }

    private fun setChildrenVisibility(viewGroup: ViewGroup, visibility: Int) {
        val count = viewGroup.childCount
        for (i in 0 until count) {
            val child = viewGroup.getChildAt(i)
            child.visibility = visibility

            if (child is ViewGroup) {
                setChildrenVisibility(viewGroup = child, visibility = visibility)
            }
        }
    }

    companion object {
        fun newInstance() = CountryDetailsFragment()
    }
}