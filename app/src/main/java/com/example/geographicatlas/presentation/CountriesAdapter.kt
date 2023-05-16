package com.example.geographicatlas.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.geographicatlas.databinding.ItemContinentBinding
import com.example.geographicatlas.databinding.ItemCountryCollapsedBinding
import com.example.geographicatlas.domain.entity.Country

class CountriesAdapter : ListAdapter<Any, CountriesViewHolder>(CountriesDiffCallback()) {

    var onButtonLearnMoreClickListener: ((cca2: String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {

        Log.d("CountriesAdapter", "onCreateViewHolder")
        return when (viewType) {
            VIEW_TYPE_COUNTRY -> CountriesViewHolder.CountriesViewHolderCountry(
                ItemCountryCollapsedBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            VIEW_TYPE_CONTINENT -> CountriesViewHolder.CountriesViewHolderContinent(
                ItemContinentBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is CountriesViewHolder.CountriesViewHolderContinent -> holder.bind(item.toString())
            is CountriesViewHolder.CountriesViewHolderCountry -> holder.bind(
                item as Country,
                onButtonExpandClickListener = { notifyItemChanged(position) },
                onButtonLearnMoreClickListener = { onButtonLearnMoreClickListener?.invoke(item.cca2) }
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)

        return if (item is Country) {
            VIEW_TYPE_COUNTRY
        } else {
            VIEW_TYPE_CONTINENT
        }
    }


    companion object {
        const val VIEW_TYPE_COUNTRY = 101
        const val VIEW_TYPE_EXPANDED = 100
        const val VIEW_TYPE_CONTINENT = 102
        const val MAX_POOL_SIZE = 15
    }
}