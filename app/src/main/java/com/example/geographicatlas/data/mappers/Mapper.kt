package com.example.geographicatlas.data.mappers

import com.example.geographicatlas.data.api.dto.CountryDetailsDto
import com.example.geographicatlas.data.api.dto.CurrencyInfoDto
import com.example.geographicatlas.domain.entity.Country
import com.example.geographicatlas.domain.entity.CurrencyInfo

class Mapper {

    companion object{
        private fun mapCurrencyInfoDtoToCurrencyInfo(currencyInfoDto: Map<String, CurrencyInfoDto>?): Map<String, CurrencyInfo> {
            val map:MutableMap<String, CurrencyInfo> =  mutableMapOf()
            if (currencyInfoDto != null) {
                for ( key in currencyInfoDto.keys) {
                    map[key] = CurrencyInfo(currencyInfoDto.getValue(key).name, currencyInfoDto.getValue(key).symbol)
                }
            }
            return map
        }

        fun mapCountryDetailsDtoListToCountryList(
            countryDetailsDtoList: List<CountryDetailsDto>
        ): List<Country> {
           return countryDetailsDtoList.map {
                Country(
                    name = it.name.common,
                    capital = it.capital,
                    cca2 = it.cca2,
                    latlng = it.capitalInfo.latlng,
                    population = it.population,
                    area = it.area,
                    currencies = mapCurrencyInfoDtoToCurrencyInfo(it.currencies),
                    flags = it.flags.png,
                    subregion = it.subregion,
                    timezones = it.timezones
                )
            }
        }

        fun mapCountryDetailsDtoToCountry(
            countryDetailsDto: List<CountryDetailsDto>
        ): Country {
            return countryDetailsDto.map {
                Country(
                    name = it.name.common,
                    capital = it.capital,
                    cca2 = it.cca2,
                    latlng = it.capitalInfo.latlng,
                    population = it.population,
                    area = it.area,
                    currencies = mapCurrencyInfoDtoToCurrencyInfo(it.currencies),
                    flags = it.flags.png,
                    subregion = it.subregion,
                    timezones = it.timezones
                )
            }.get(0)
        }
    }

}