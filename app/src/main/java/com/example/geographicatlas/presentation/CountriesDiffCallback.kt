package com.example.geographicatlas.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.geographicatlas.domain.entity.Country

class CountriesDiffCallback: DiffUtil.ItemCallback<Any>() {
    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        return if(oldItem is Country && newItem is Country) {
            oldItem.cca2 == newItem.cca2
        } else {
            oldItem == newItem
        }
    }

    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        return if(oldItem is Country && newItem is Country) {
            oldItem == newItem
        } else {
            oldItem == newItem
        }
    }
}