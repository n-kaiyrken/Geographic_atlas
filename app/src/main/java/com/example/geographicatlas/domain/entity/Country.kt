package com.example.geographicatlas.domain.entity

data class Country(
    val name: String = "",
    val capital: String = "",
    val cca2: String = "",
    val continents: String = "",
    val latlng: String = "",
    val population: String = "",
    val area: String = "",
    val currencies: String = "",
    val flags: String = "",
    val subregion: String = "",
    val timezones: String = "",
    var isExpanded: Boolean = false,
    val maps: String = "",
)