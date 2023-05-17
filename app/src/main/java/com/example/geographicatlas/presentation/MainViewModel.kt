package com.example.geographicatlas.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.geographicatlas.data.GeographicAtlasRepositoryImpl
import com.example.geographicatlas.domain.entity.Country
import com.example.geographicatlas.domain.usecases.GetAllCountriesUseCase
import com.example.geographicatlas.domain.usecases.GetCountryByCca2UseCase
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository = GeographicAtlasRepositoryImpl()

    private val getAllCountriesUseCase = GetAllCountriesUseCase(repository)
    private val getCountryByCca2UseCase = GetCountryByCca2UseCase(repository)

    private val _countriesListLivaData = MutableLiveData<List<Any>>()
    val countriesListLivaData: LiveData<List<Any>> = _countriesListLivaData

    private val _countryLivaData = MutableLiveData<Country>()
    val countryLivaData: LiveData<Country> = _countryLivaData

    init {
        getAllCountries()
    }

    private fun getAllCountries() {
        viewModelScope.launch {
            val countryList = modifyList(getAllCountriesUseCase())
            _countriesListLivaData.value = countryList
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
            _countryLivaData.value = country
        }
    }
}