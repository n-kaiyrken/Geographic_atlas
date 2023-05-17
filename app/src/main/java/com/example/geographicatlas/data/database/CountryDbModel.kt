package com.example.geographicatlas.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "country_list")
data class CountryDbModel(
    val name: String,
    val capital: String,
    @PrimaryKey
    val cca2: String,
    val continents: String,
    val latlng: String,
    val population: String,
    val area: String,
    val currencies: String,
    val flags: String,
    val subregion: String,
    val timezones: String,
    var isExpanded: Boolean = false
)
