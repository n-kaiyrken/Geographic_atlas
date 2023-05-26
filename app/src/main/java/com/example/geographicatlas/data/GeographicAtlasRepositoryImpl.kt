package com.example.geographicatlas.data

import com.example.geographicatlas.data.api.ApiService
import com.example.geographicatlas.data.mappers.Mapper
import com.example.geographicatlas.domain.GeographicAtlasRepository
import com.example.geographicatlas.domain.entity.Country
import kotlinx.coroutines.delay
import javax.inject.Inject

class GeographicAtlasRepositoryImpl @Inject constructor (
    private val mapper: Mapper,
    private val apiService: ApiService
) : GeographicAtlasRepository {

    override suspend fun getAllCountries(): List<Country> {
        try {
            val allCountries = apiService.getAllCountries()
            return mapper.mapCountryDetailsDtoListToCountryList(allCountries)
        } catch (e: Exception) {
            return listOf(Country())
        }

    }

    override suspend fun getCountryByCca2(cca2: String): Country {
        try {
            val countryData = apiService.getCountryByCca2(cca2)
            val country = mapper.mapCountryDetailsDtoListToCountryList(countryData)
            return country.get(0)
        } catch (e: Exception) {
            delay(10000)
            return Country()
        }
    }
}