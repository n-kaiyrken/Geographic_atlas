package com.example.geographicatlas.data

import com.example.geographicatlas.data.api.ApiFactory
import com.example.geographicatlas.data.mappers.Mapper
import com.example.geographicatlas.domain.GeographicAtlasRepository
import com.example.geographicatlas.domain.entity.Country
import kotlinx.coroutines.delay

class GeographicAtlasRepositoryImpl : GeographicAtlasRepository {

    private val apiService = ApiFactory.apiService

    override suspend fun getAllCountries(): List<Country> {
        try {
            val allCountries = apiService.getAllCountries()
            return Mapper.mapCountryDetailsDtoListToCountryList(allCountries)
        } catch (e: Exception) {
            return listOf(Country())
        }

    }

    override suspend fun getCountryByCca2(cca2: String): Country {
        try {
            val countryData = apiService.getCountryByCca2(cca2)
            val country = Mapper.mapCountryDetailsDtoListToCountryList(countryData)
            return country.get(0)
        } catch (e: Exception) {
            delay(10000)
            return Country()
        }
    }
}