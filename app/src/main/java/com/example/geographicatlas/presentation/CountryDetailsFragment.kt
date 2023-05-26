package com.example.geographicatlas.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.geographicatlas.App
import com.example.geographicatlas.databinding.FragmentCountryDetailsBinding
import com.squareup.picasso.Picasso
import javax.inject.Inject

class CountryDetailsFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as App).component
    }

    private var _binding: FragmentCountryDetailsBinding? = null
    private val binding: FragmentCountryDetailsBinding
        get() = _binding ?: throw RuntimeException("FragmentCountryDetailsBinding is null")

    var onCoordinatesClickListener: ((url: String) -> Unit)? = null
    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountryDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(MainViewModel::class.java)
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
        viewModel.countryLivaData.observe(viewLifecycleOwner) {country ->
            with(binding) {
                detailsToolBar.title = country.name
                textViewDetailsCapital.text = country.capital
                textViewCoordinates.text = country.latlng
                textViewRegion.text = country.subregion
                textViewTimezones.text = country.timezones
                textViewDetailsArea.text = country.area
                textViewDetailsCurrency.text = country.currencies
                textViewDetailsPopulation.text = country.population
                if (country.flags != "") {
                    Picasso.get().load(country.flags).into(binding.imageViewFlag)
                }
                if (country.maps != "") {
                    binding.textViewCoordinates.setOnClickListener {
                        openMaps(country.maps)
                    }
                }
            }
        }

    }

    private fun openMaps(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        val packageManager = context?.packageManager
        if (packageManager?.let { it1 -> intent.resolveActivity(it1) } != null) {
            context?.startActivity(intent)
        } else {

        }
        startActivity(intent)
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