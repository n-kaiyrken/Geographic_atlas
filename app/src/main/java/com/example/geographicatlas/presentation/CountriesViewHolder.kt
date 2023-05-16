package com.example.geographicatlas.presentation

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.geographicatlas.databinding.ItemContinentBinding
import com.example.geographicatlas.databinding.ItemCountryCollapsedBinding
import com.example.geographicatlas.domain.entity.Country
import com.example.geographicatlas.domain.entity.CurrencyInfo
import com.squareup.picasso.Picasso

sealed class CountriesViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    class CountriesViewHolderCountry(
        val binding: ItemCountryCollapsedBinding
    ) : CountriesViewHolder(binding) {

        fun bind(
            country: Country,
            onButtonExpandClickListener: () -> Unit,
            onButtonLearnMoreClickListener: (cca2: String) -> Unit
        ) {
            binding.textViewCountry.text = country.name
            binding.textViewListCapital.text = country.capital
            Picasso.get().load(country.flags).into(binding.imageViewFlag)

            with(binding) {
                buttonExpand.setOnClickListener {
                    val state = country.isExpanded;
                    country.isExpanded = !state
                    onButtonExpandClickListener()
                }
                texViewButtonLearnMore.setOnClickListener {
                    onButtonLearnMoreClickListener(country.cca2)
                }

                if (country.isExpanded) {
                    binding.additionalItem.visibility = View.VISIBLE
                    setChildrenVisibility(binding.additionalItem, View.VISIBLE)
                } else {
                    binding.additionalItem.visibility = View.GONE
                    setChildrenVisibility(binding.additionalItem, View.GONE)
                }
                textViewCurrencies.text = country.currencies
                textViewArea.text = country.area
                textViewPopulation.text = country.population
            }
        }

        private fun setChildrenVisibility(viewGroup: ViewGroup, visibility: Int) {
            val count = viewGroup.childCount
            for (i in 0 until count) {
                val child = viewGroup.getChildAt(i)
                child.visibility = visibility

                if (child is ViewGroup) {
                    setChildrenVisibility(child, visibility)
                }
            }
        }
    }

    class CountriesViewHolderContinent(
        val binding: ItemContinentBinding
    ) : CountriesViewHolder(binding) {

        fun bind(continent: String) {
            binding.textViewContinent.text = continent
        }
    }
}
