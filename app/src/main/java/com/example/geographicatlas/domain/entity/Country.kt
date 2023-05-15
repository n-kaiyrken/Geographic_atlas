package com.example.geographicatlas.domain.entity

data class Country(
    val name: String,
    val capital: List<String>?,
    val cca2: String,
    val latlng: List<Double>?,
    val population: Long?,
    val area: Double?,
    val currencies: Map<String, CurrencyInfo>?,
    val flags: String?,
    val subregion: String?,
    val timezones: List<String?>
)