package com.example.geographicatlas.domain.usecases

import com.example.geographicatlas.domain.GeographicAtlasRepository

class GetAllCountriesUseCase(val repository: GeographicAtlasRepository) {

    suspend operator fun invoke() {
        repository.getAllCountries()
    }
}