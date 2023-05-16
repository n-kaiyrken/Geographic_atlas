package com.example.geographicatlas.data.mappers

import com.example.geographicatlas.data.api.dto.CountryDetailsDto
import com.example.geographicatlas.data.api.dto.CurrencyInfoDto
import com.example.geographicatlas.domain.entity.Country
import com.example.geographicatlas.domain.entity.CurrencyInfo
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.*

class Mapper {

    companion object {
//        private fun mapCurrencyInfoDtoToCurrencyInfo(currencyInfoDto: Map<String, CurrencyInfoDto>?): Map<String, CurrencyInfo> {
//            val map: MutableMap<String, CurrencyInfo> = mutableMapOf()
//            if (currencyInfoDto != null) {
//                for (key in currencyInfoDto.keys) {
//                    map[key] = CurrencyInfo(
//                        currencyInfoDto.getValue(key).name,
//                        currencyInfoDto.getValue(key).symbol
//                    )
//                }
//            }
//            return map
//        }

        private fun mapCurrencyInfoDtoToString(
            currencyInfoDto: Map<String, CurrencyInfoDto>?
        ): String {
            val currencyInfoList = mutableListOf<CurrencyInfo>()
            if (currencyInfoDto != null) {
                for (key in currencyInfoDto.keys) {
                    currencyInfoList.add(
                        CurrencyInfo(
                            name = currencyInfoDto.getValue(key).name ?: "",
                            symbol = currencyInfoDto.getValue(key).symbol ?: "",
                            code = key ?: ""
                        )
                    )
                }
            }
            return parseCurrenciesToString(currencyInfoList)
        }

        fun mapCountryDetailsDtoListToCountryList(
            countryDetailsDtoList: List<CountryDetailsDto>
        ): List<Country> {
            return countryDetailsDtoList.map {
                mapCountryDetailsDtoToCountry(it)
            }
        }

        private fun mapCountryDetailsDtoToCountry(
            countryDetailsDto: CountryDetailsDto
        ): Country {
            return Country(
                name = countryDetailsDto.name.common,
                capital = countryDetailsDto.capital?.joinToString("\n") ?: "",
                cca2 = countryDetailsDto.cca2,
                continents = countryDetailsDto.continents?.getOrNull(0) ?: "",
                latlng = countryDetailsDto.capitalInfo.latlng?.joinToString(", ") ?: "",
                population = formatPopulation(countryDetailsDto.population ?: 0),
                area = formatArea(countryDetailsDto.area ?: 0.0) ,
                currencies = mapCurrencyInfoDtoToString(countryDetailsDto.currencies),
                flags = countryDetailsDto.flags.png ?: "",
                subregion = countryDetailsDto.subregion ?: "",
                timezones = countryDetailsDto.timezones?.joinToString("\n") ?: ""
            )
        }

        private fun parseCurrenciesToString(currencies: List<CurrencyInfo>): String{
            return if (currencies.isNotEmpty()) {
                val list = currencies.map {
                    "${it.name} (${it.symbol}) (${it.code})"
                }.joinToString("\n")
                list
            } else {
                ""
            }
        }

        private fun formatPopulation(population: Long): String {
            val suffixes = listOf("", "k", "mln", "bln", "trln") // Суффиксы для сокращений

            val magnitude = (Math.log10(population.toDouble()) / 3).toInt() // Определение масштаба числа

            val index = if (magnitude in 0 until suffixes.size) magnitude else suffixes.size - 1 // Проверка диапазона индекса

            val formattedNumber = population / Math.pow(10.0, (index * 3).toDouble()) // Форматирование числа

            return String.format("%.0f %s", formattedNumber, suffixes[index]) // Форматирование строки
        }

        private fun formatArea(area: Double): String {
            val symbols = DecimalFormatSymbols.getInstance(Locale.getDefault())
            symbols.groupingSeparator = ' ' // Определение разделителя групп разрядов

            val numberFormat = DecimalFormat("#,##0")
            numberFormat.decimalFormatSymbols = symbols

            val formattedNumber = numberFormat.format(area)
            return "$formattedNumber km²"
        }
    }

}