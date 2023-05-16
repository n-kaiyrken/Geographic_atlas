package com.example.geographicatlas.domain.usecases

import com.example.geographicatlas.domain.GeographicAtlasRepository
import com.example.geographicatlas.domain.entity.Country

class GetAllCountriesUseCase(val repository: GeographicAtlasRepository) {

    suspend operator fun invoke(): List<Country> {
        return repository.getAllCountries()
    }
}