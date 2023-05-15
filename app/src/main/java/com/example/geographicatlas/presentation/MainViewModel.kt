package com.example.geographicatlas.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.geographicatlas.data.GeographicAtlasRepositoryImpl
import com.example.geographicatlas.domain.usecases.GetAllCountriesUseCase
import com.example.geographicatlas.domain.usecases.GetCountryByCca2UseCase
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository = GeographicAtlasRepositoryImpl()
    private val cca2 = "TL"

    private val getAllCountriesUseCase = GetAllCountriesUseCase(repository)
    private val getCountryByCca2UseCase = GetCountryByCca2UseCase(repository)

    init {
        getAllCountries()
        getCountryByCca2()
    }

    private fun getAllCountries() {
        viewModelScope.launch {
            getAllCountriesUseCase()
        }
    }

    private fun getCountryByCca2() {
        viewModelScope.launch {
            getCountryByCca2UseCase(cca2)
        }
    }
}