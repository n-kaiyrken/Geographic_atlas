package com.example.geographicatlas.domain

import com.example.geographicatlas.domain.entity.Country

interface GeographicAtlasRepository {

    suspend fun getAllCountries(): List<Country>

    suspend fun getCountryByCca2(cca2: String): Country
}