package com.example.geographicatlas.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.geographicatlas.R
import com.example.geographicatlas.databinding.FragmentCountriesListBinding
import com.example.geographicatlas.presentation.adapters.CountriesAdapter


class CountriesListFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    private var _binding: FragmentCountriesListBinding? = null
    private val binding: FragmentCountriesListBinding
        get() = _binding ?: throw RuntimeException("FragmentCountriesListBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountriesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        val adapter = CountriesAdapter()
        binding.recyclerViewCountries.adapter = adapter
        adapter.onButtonLearnMoreClickListener = {
            viewModel.getCountryByCca2(it)
            launchCountryDetailsFragment(CountryDetailsFragment.newInstance())
        }
        viewModel.countriesListLivaData.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
    }

    private fun launchCountryDetailsFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.popBackStack()
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    companion object {
        fun newInstance() = CountriesListFragment()
    }
}