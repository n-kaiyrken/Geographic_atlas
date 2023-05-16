package com.example.geographicatlas.data.api.dto

data class CountryDetailsDto(
    val name: NameDto,
    val capital: List<String>?,
    val cca2: String,
    val continents: List<String>?,
    val capitalInfo: CapitalInfoDto,
    val flags: FlagsDto,
    val population: Long?,
    val area: Double?,
    val currencies: Map<String, CurrencyInfoDto>?,
    val subregion: String?,
    val timezones: List<String>?
)