package com.example.geographicatlas.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.geographicatlas.domain.entity.Country
import com.example.geographicatlas.domain.usecases.GetAllCountriesUseCase
import com.example.geographicatlas.domain.usecases.GetCountryByCca2UseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getAllCountriesUseCase: GetAllCountriesUseCase,
    private val getCountryByCca2UseCase: GetCountryByCca2UseCase
) : ViewModel() {

    private val _countriesListLivaData = MutableLiveData<List<Any>>()
    val countriesListLivaData: LiveData<List<Any>> = _countriesListLivaData

    private val _countryLivaData = MutableLiveData<Country>()
    val countryLivaData: LiveData<Country> = _countryLivaData

    private val _noConnectionLivaData = MutableLiveData<Boolean>()
    val noConnectionLivaData: LiveData<Boolean> = _noConnectionLivaData

    init {
        getAllCountries()
    }

    fun getAllCountries() {
        viewModelScope.launch {
            val countryList = getAllCountriesUseCase()
            if (isHaveConnection(countryList.get(0))) {
                val modifiedList = modifyList(getAllCountriesUseCase())
                _countriesListLivaData.value = modifiedList
            }
        }
    }
    private fun modifyList(countryList: List<Country>):List<Any> {
        val modifiedList = mutableListOf<Any>()
        val groupedByContinent = countryList.groupBy { it.continents }
        groupedByContinent.forEach { (continent, countries) ->
            modifiedList.add(continent)
            modifiedList.addAll(countries)
        }
        return modifiedList
    }

    fun getCountryByCca2(cca2: String) {
        viewModelScope.launch {
            val country = getCountryByCca2UseCase(cca2)
            if (isHaveConnection(country)) {
                _countryLivaData.value = country
            }
        }
    }

    private fun isHaveConnection(country: Country): Boolean {
        return if (country.cca2 == "") {
            _noConnectionLivaData.value = true
            false
        } else {
            _noConnectionLivaData.value = false
            true
        }
    }

    fun clearCountryLivaData() {
        _countryLivaData.value = Country()
    }
}